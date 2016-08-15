package com.ponpongi.pongicounter;

import android.graphics.Color;

/**
 * Created by chyikwei on 7/30/2016.
 *
 * Data Structure to store each counter
 */
public class CounterItem {
    private String name;
    private int  color;
    private int count;

    public CounterItem(String name, int color) {
        this.name = name;
        this.color = color;
        this.count = 0;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public int getColor() {
        return this.color;
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

    public String toString() {
        return name + ":" + count;
    }
}
