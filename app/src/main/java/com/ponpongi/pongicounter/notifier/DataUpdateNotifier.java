package com.ponpongi.pongicounter.notifier;

import com.ponpongi.pongicounter.CounterItem;

import java.util.List;

/**
 * Created by chyikwei on 8/5/2016.
 */
public interface DataUpdateNotifier {

    void notifyDataUpdate(List<CounterItem> items);
}
