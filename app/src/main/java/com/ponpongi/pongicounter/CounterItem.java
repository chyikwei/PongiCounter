package com.ponpongi.pongicounter;

/**
 * Created by chyikwei on 7/30/2016.
 *
 * Data Structure to store each counter
 */
public class CounterItem {
    private String name;
    private int count;

    public CounterItem(String name) {
        this.name = name;
        this.count = 0;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public String getStrCount() {
        return Integer.toString(count);
    }

    public void increment() {
        count += 1;
    }

    public void decrement() {
        count -= 1;
    }

    public String toString() {
        return name + ":" + count;
    }
}
