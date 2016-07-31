package com.ponpongi.pongicounter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

/**
 * Created by chyikwei on 7/30/2016.
 *
 * Adapter for grid view
 */
public class ItemAdapter extends BaseAdapter{

    private Context mContext;
    private CounterItem[] items;

    public ItemAdapter(Context c, CounterItem[] items) {
        this.mContext = c;
        this.items = items;
    }

    public int getCount() {
        return items.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            textView = new TextView(mContext);
            textView.setLayoutParams(new GridView.LayoutParams(240, 240));
            textView.setPadding(1, 1, 1, 1);
        } else {
            textView = (TextView) convertView;
        }

        textView.setText(items[position].toString());
        return textView;
    }
}
