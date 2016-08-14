package com.ponpongi.pongicounter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by chyikwei on 8/13/2016.
 */
public class CardItemTouchHelperCallback extends SimpleItemTouchHelperCallback {

    public CardItemTouchHelperCallback(ItemTouchHelperAdapter adapter) {
        super(adapter);
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN |
                        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        int swipeFlags = 0;
        return makeMovementFlags(dragFlags, swipeFlags);
    }


}
