package com.ponpongi.pongicounter;

import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView.Adapter;
import android.util.Log;

import com.ponpongi.pongicounter.touchHelper.ItemTouchHelperAdapter;

import java.util.Collections;
import java.util.List;

/**
 * Created by chyikwei on 7/30/2016.
 *
 * Adapter for grid view
 */
public class ItemAdapter extends Adapter<CounterViewHolder> implements ItemTouchHelperAdapter {

    private List<CounterItem> items;
    private boolean isCardView;
    private int item_layout;
    public ItemAdapter(List<CounterItem> items, boolean isCardView)
    {
        this.items = items;
        this.isCardView = isCardView;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private void setClickListener(final CounterViewHolder viewHolder, final CounterItem item) {
        viewHolder.itemView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        item.increment();
                        viewHolder.count.setText(item.getStrCount());
                    }
                }
        );

        viewHolder.minusBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        item.decrement();
                        viewHolder.count.setText(item.getStrCount());
                    }
                }
        );
    }

    @Override
    public void onBindViewHolder(final CounterViewHolder viewHolder, int i) {
        final CounterItem item = items.get(i);
        viewHolder.title.setText(item.getName());
        viewHolder.count.setText(item.getStrCount());
        // set background color
        if (viewHolder.itemView instanceof CardView) {
            ((CardView) viewHolder.itemView).setCardBackgroundColor(item.getColor());
            Log.d("onBindViewHolder", "card view: " + i);
        } else {
            viewHolder.itemView.setBackgroundColor(item.getColor());
            Log.d("onBindViewHolder", "list view: " + i);
        }

        //TODO: update this part
        if (isCardView) {
            setClickListener(viewHolder, item);
        } else {
            //list view
            viewHolder.minusBtn.setEnabled(false);
        }
    }

    @Override
    public CounterViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        final CounterItem item = items.get(i);
        if(isCardView) {
            item_layout = R.layout.card_item;
        } else {
            item_layout = R.layout.list_item;
        }

        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(item_layout, viewGroup, false);

        return new CounterViewHolder(itemView, item);
    }

    @Override
    public void onItemDismiss(int position){
        Log.i("ITEM_REMOVE", "position: " + position);
        items.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        Log.i("ITEM_MOVE", "from: " + fromPosition + ", to: " + toPosition);
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(items, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(items, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
    }
}
