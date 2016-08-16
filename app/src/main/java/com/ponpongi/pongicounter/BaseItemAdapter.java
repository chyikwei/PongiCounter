package com.ponpongi.pongicounter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.ponpongi.pongicounter.touchHelper.ItemTouchHelperAdapter;

import java.util.Collections;
import java.util.List;

/**
 * Created by chyikwei on 8/15/2016.
 */
public abstract class BaseItemAdapter extends RecyclerView.Adapter<CounterViewHolder> implements ItemTouchHelperAdapter {

    protected List<CounterItem> items;

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onBindViewHolder(final CounterViewHolder viewHolder, int i) {
        final CounterItem item = items.get(i);
        viewHolder.title.setText(item.getName());
        viewHolder.count.setText(item.getStrCount());
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
