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
public class ListItemAdapter extends BaseItemAdapter {

    public ListItemAdapter(List<CounterItem> items) {
        this.items = items;
    }

    @Override
    public void onBindViewHolder(final CounterViewHolder viewHolder, int i) {
        final CounterItem item = items.get(i);
        viewHolder.title.setText(item.getName());
        viewHolder.count.setText(item.getStrCount());
        // set background color
        ImageView imageView = (ImageView) viewHolder.itemView.findViewById(R.id.color_icon);
        imageView.setBackgroundColor(item.getColor());
        Log.d("onBindViewHolder", "list view: " + i);

        viewHolder.minusBtn.setEnabled(false);
    }

    @Override
    public CounterViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        final CounterItem item = items.get(i);
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.list_item, viewGroup, false);
        return new CounterViewHolder(itemView, item);
    }


}
