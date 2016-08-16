package com.ponpongi.pongicounter;

import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by chyikwei on 8/15/2016.
 */
public class CardItemAdapter extends BaseItemAdapter {

    public CardItemAdapter(List<CounterItem> items) {
        this.items = items;
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
        ((CardView) viewHolder.itemView).setCardBackgroundColor(item.getColor());
        Log.d("onBindViewHolder", "card view: " + i);

        //listener
        setClickListener(viewHolder, item);
    }

    @Override
    public CounterViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        final CounterItem item = items.get(i);
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.card_item, viewGroup, false);

        return new CounterViewHolder(itemView, item);
    }

}
