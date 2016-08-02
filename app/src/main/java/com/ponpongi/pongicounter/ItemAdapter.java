package com.ponpongi.pongicounter;

import android.content.Context;
import android.view.LayoutInflater;
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
    private LayoutInflater myInflater;
    private CounterItem[] items;

    private class ViewHolder {
        TextView txtTitle;
        TextView txtCount;
        public ViewHolder(TextView title, TextView count) {
            this.txtTitle = title;
            this.txtCount = count;
        }
    }

    public ItemAdapter(Context c, CounterItem[] items) {
        this.mContext = c;
        this.myInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.items = items;
    }

    public int getCount() {
        return items.length;
    }

    public Object getItem(int position) {
        if (position >= items.length) {
            return null;
        } else {
            return items[position];
        }
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vd;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            convertView = myInflater.inflate(R.layout.grid_item, null);
            vd = new ViewHolder(
                    (TextView) convertView.findViewById(R.id.item_title),
                    (TextView) convertView.findViewById(R.id.item_count)
            );
            convertView.setTag(vd);
        } else {
            vd = (ViewHolder) convertView.getTag();
        }
        // set data
        CounterItem item = items[position];
        vd.txtTitle.setText(item.getName());
        vd.txtCount.setText(Integer.toString(item.getCount()));
        return convertView;
    }
}
