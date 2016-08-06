package com.ponpongi.pongicounter;

import java.util.List;

/**
 * Created by chyikwei on 8/5/2016.
 */
public interface DataUpdateNotifier {

    void notifyDataUpdate(List<CounterItem> items);
}
