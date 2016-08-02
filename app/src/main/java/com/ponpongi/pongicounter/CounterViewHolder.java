package com.ponpongi.pongicounter;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.TextView;

/**
 * Created by chyikwei on 8/1/2016.
 */
public class CounterViewHolder extends ViewHolder{
    protected TextView title;
    protected TextView count;

    public CounterViewHolder(View v) {
        super(v);
        title =  (TextView) v.findViewById(R.id.text_title);
        count = (TextView) v.findViewById(R.id.text_count);
    }

}
