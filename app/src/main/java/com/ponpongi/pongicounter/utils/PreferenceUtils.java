package com.ponpongi.pongicounter.utils;

import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ponpongi.pongicounter.CounterItem;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chyikwei on 8/15/2016.
 */
public class PreferenceUtils {

    private static final String PREF_KEY = "com.ponpongi.pongicounter.data_list";
    private static final String TAG = "PreferenceUtils";

    public static List<CounterItem> loadCOunterData(SharedPreferences pref) {
        List<CounterItem> data;
        String dataString = pref.getString(PREF_KEY, "");
        Log.d(TAG, "load data:" + dataString);
        return loadCounterData(dataString);
    }

    public static List<CounterItem> loadCounterData(String dataString) {
        List<CounterItem> data;
        if (dataString.length() > 0) {
            Type listType = new TypeToken<ArrayList<CounterItem>>() {
            }.getType();
            data = new Gson().fromJson(dataString, listType);
        } else {
            data = new ArrayList<CounterItem>();
        }
        return data;
    }

    public static String dumpCounterData(List<CounterItem> data) {
        Gson gson = new Gson();
        return gson.toJson(data);
    }

    public static void dumpCounterData(SharedPreferences pref, List<CounterItem> data) {
        SharedPreferences.Editor editor = pref.edit();
        String jsonInString = dumpCounterData(data);
        Log.d(TAG, "Save data: " + jsonInString);
        editor.putString(PREF_KEY, jsonInString);
        editor.commit();
    }

}
