package com.ponpongi.pongicounter;

import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by chyikwei on 8/15/2016.
 */
public class ListItemAdapter extends BaseItemAdapter {

    public static final String TAG = "ListItemAdapter";

    public ListItemAdapter(List<CounterItem> items) {
        this.items = items;
    }

    private void setClickListener(final CounterViewHolder viewHolder, final CounterItem item) {
        ImageButton btn = (ImageButton) viewHolder.itemView.findViewById(R.id.edit_img_btn);

        btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //start new activity
                        Context context = view.getContext();
                        Intent intent = new Intent(context, ItemEditActivity.class);
                        Log.d(TAG, "start_context");
                        context.startActivity(intent);
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
        ImageView imageView = (ImageView) viewHolder.itemView.findViewById(R.id.color_icon);
        imageView.setBackgroundColor(item.getColor());
        Log.d("onBindViewHolder", "list view: " + i);
        setClickListener(viewHolder, item);
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
