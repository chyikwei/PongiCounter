package com.ponpongi.pongicounter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.widget.Toast;

import java.util.List;

/**
 * Created by chyikwei on 7/30/2016.
 *
 * Adapter for grid view
 */
public class ItemAdapter extends Adapter<CounterViewHolder>{

    private List<CounterItem> items;

    public ItemAdapter(List<CounterItem> items) {
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onBindViewHolder(final CounterViewHolder viewHolder, int i) {
        final CounterItem item = items.get(i);
        viewHolder.title.setText(item.getName());
        viewHolder.count.setText(item.getStrCount());

        viewHolder.itemView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        item.increment();
                        viewHolder.count.setText(item.getStrCount());
                    }
                }
        );
    }

    @Override
    public CounterViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.card_item, viewGroup, false);

        return new CounterViewHolder(itemView);
    }
}
