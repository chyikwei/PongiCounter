package com.ponpongi.pongicounter;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ponpongi.pongicounter.touchHelper.ItemTouchHelperViewHolder;

/**
 * Created by chyikwei on 8/1/2016.
 */
public class CounterViewHolder extends ViewHolder implements ItemTouchHelperViewHolder{
    protected TextView title;
    protected TextView count;
    final private CounterItem item;

    public CounterViewHolder(View v, CounterItem item) {
        super(v);
        title =  (TextView) v.findViewById(R.id.text_title);
        count = (TextView) v.findViewById(R.id.text_count);
        this.item = item;
    }

    @Override
    public void onItemSelected() {
        /*
        if (itemView instanceof CardView) {
            ((CardView) itemView).setCardBackgroundColor(Color.LTGRAY);
            Log.d("onItemSelected: card",  item.getName() + ": " + item.getColor());
        } else {
            itemView.setBackgroundColor(Color.LTGRAY);
            Log.d("onItemSelected: list",  item.getName() + ": " + item.getColor());
        }
        */
    }

    @Override
    public void onItemClear() {
        /*
        if (itemView instanceof CardView) {
            ((CardView) itemView).setCardBackgroundColor(item.getColor());
            Log.d("onItemClear: card ", item.getName() + ": " + item.getColor());

        } else {
            itemView.setBackgroundColor(item.getColor());
            Log.d("onItemClear: list", item.getName() + ": " + item.getColor());

        }
        */
    }
}
