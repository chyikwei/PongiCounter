package com.ponpongi.pongicounter;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.ponpongi.pongicounter.utils.ColorUtils;
import com.ponpongi.pongicounter.utils.Constants;

import java.util.List;

/**
 * Created by chyikwei on 8/15/2016.
 */
public class ListItemAdapter extends BaseItemAdapter {

    public static final String TAG = "ListItemAdapter";

    public ListItemAdapter(List<CounterItem> items) {
        this.items = items;
    }

    private Activity getActivity(Context context) {
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity)context;
            }
            context = ((ContextWrapper)context).getBaseContext();
        }
        return null;
    }

    private void setClickListener(final CounterViewHolder viewHolder, final CounterItem item) {
        ImageButton btn = (ImageButton) viewHolder.itemView.findViewById(R.id.edit_img_btn);
        final int index = this.items.indexOf(item);

        btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //start new activity
                        Context context = view.getContext();
                        Activity activity = getActivity(context);
                        Intent intent = new Intent(context, ItemEditActivity.class);
                        intent.putExtra(Constants.EDIT_ITEM_INDEX, index);
                        intent.putExtra(Constants.EDIT_ITEM_NAME, item.getName());
                        intent.putExtra(Constants.EDIT_ITEM_COUNT, item.getCount());
                        intent.putExtra(Constants.EDIT_ITEM_COLOR, ColorUtils.colorToStr(item.getColor()));
                        Log.d(TAG, "start_context");
                        activity.startActivityForResult(intent, Constants.EDIT_ITEM_CODE);
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
