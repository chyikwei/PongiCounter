package com.ponpongi.pongicounter;

import android.graphics.Color;

import com.ponpongi.pongicounter.utils.ColorUtils;

/**
 * Created by chyikwei on 7/30/2016.
 *
 * Data Structure to store each counter
 */
public class CounterItem {
    private String name;
    private String colorStr;
    private int count;

    public CounterItem(String name, String colorStr) {
        this.name = name;
        this.colorStr = colorStr;
        this.count = 0;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public int getColor() {
        return ColorUtils.parseColor(this.colorStr);
    }

    public String getStrCount() {
        return Integer.toString(count);
    }

    public void increment() {
        count += 1;
    }

    public void decrement() {
        if (count > 0) {
            count -= 1;
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColorStr(String colorStr) {
        this.colorStr = colorStr;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String toString() {
        return name + ":" + count;
    }
}
