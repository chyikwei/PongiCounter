package com.ponpongi.pongicounter;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView.ViewHolder;
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
    protected Button minusBtn;

    public CounterViewHolder(View v) {
        super(v);
        title =  (TextView) v.findViewById(R.id.text_title);
        count = (TextView) v.findViewById(R.id.text_count);
        minusBtn = (Button) v.findViewById(R.id.minus_btn);
    }

    @Override
    public void onItemSelected() {
        if (itemView instanceof CardView) {
            ((CardView) itemView).setCardBackgroundColor(Color.LTGRAY);
        } else {
            itemView.setBackgroundColor(Color.LTGRAY);
        }
    }

    @Override
    public void onItemClear() {
        if (itemView instanceof CardView) {
            ((CardView) itemView).setCardBackgroundColor(Color.WHITE);
        } else {
            itemView.setBackgroundColor(Color.WHITE);
        }
    }
}
