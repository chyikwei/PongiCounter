package com.ponpongi.pongicounter.touchHelper;

/**
 * Created by chyikwei on 8/4/2016.
 */
public interface ItemTouchHelperAdapter {
    void onItemMove(int fromPosition, int toPosition);
    void onItemDismiss(int position);
}

