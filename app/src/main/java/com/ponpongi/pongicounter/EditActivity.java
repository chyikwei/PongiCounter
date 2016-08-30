package com.ponpongi.pongicounter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.ponpongi.pongicounter.notifier.DataUpdateNotifier;
import com.ponpongi.pongicounter.utils.PreferenceUtils;

import java.util.List;

public class EditActivity extends AppCompatActivity {

    private static final String PREF_NAME = "com.ponpongi.pongicounter.perference";
    private static final String TAG = "EditActivity";

    private DataUpdateNotifier dataUpdateNotifier;
    private List<CounterItem> data_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        //load data from preference
        Log.d(TAG, "onCreate");
        SharedPreferences pref = getApplicationContext().getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        data_list = PreferenceUtils.loadCOunterData(pref);

        showListView();
    }

    private void showListView() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        ListViewFragment fragment = ListViewFragment.newInstance(data_list);
        transaction.replace(R.id.edit_fragment, fragment);
        transaction.commit();
        dataUpdateNotifier = fragment;
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause");
        Log.d(TAG, "list size: " + data_list.size());
        //store data
        SharedPreferences pref = getApplicationContext().getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        PreferenceUtils.dumpCounterData(pref, data_list);
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        Log.d(TAG, "onBackPressed");
        //store data
        SharedPreferences pref = getApplicationContext().getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        PreferenceUtils.dumpCounterData(pref, data_list);
        this.finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        // included to allow fragment to receive onActivityResult
        Log.d(TAG, "onActivityResult");
        super.onActivityResult(requestCode, resultCode, intent);

        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.edit_fragment);
        fragment.onActivityResult(requestCode, resultCode, intent);

    }

}
