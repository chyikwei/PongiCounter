package com.ponpongi.pongicounter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ponpongi.pongicounter.AddItemDialogFragment.EditNewItemDialogListener;
import com.ponpongi.pongicounter.notifier.DataUpdateNotifier;
import com.ponpongi.pongicounter.utils.PreferenceUtils;

import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Type;

public class MainActivity extends AppCompatActivity implements EditNewItemDialogListener {

    private static final String PREF_NAME = "com.ponpongi.pongicounter.perference";
    private static final String TAG = "MainActivity";
    private DataUpdateNotifier dataUpdateNotifier;
    List<CounterItem> data_list;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //load data from preference
        Log.d(TAG, "onCreate");
        SharedPreferences pref = getApplicationContext().getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        data_list = PreferenceUtils.loadCOunterData(pref);
        showCardView();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop");
        //store data
        SharedPreferences pref = getApplicationContext().getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        PreferenceUtils.dumpCounterData(pref, data_list);

        super.onStop();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.ponpongi.pongicounter/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.disconnect();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(TAG, "onCreateOptionsMenu");
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public  void onResume() {
        Log.d(TAG, "onResume");
        super.onResume();
        SharedPreferences pref = getApplicationContext().getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        data_list = PreferenceUtils.loadCOunterData(pref);
        showCardView();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.edit_item:
                //store data
                SharedPreferences pref = getApplicationContext().getSharedPreferences(PREF_NAME, MODE_PRIVATE);
                PreferenceUtils.dumpCounterData(pref, data_list);
                //start new activity
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                startActivityForResult(intent, 0);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void addItem(View view) {
        showAddDialog();
    }

    private void showCardView() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        CardViewFragment fragment = CardViewFragment.newInstance(data_list);
        transaction.replace(R.id.main_fragment, fragment);
        transaction.commit();
        dataUpdateNotifier = fragment;
    }

    private void showAddDialog() {
        FragmentManager fm = getSupportFragmentManager();
        AddItemDialogFragment dialogFragment = new AddItemDialogFragment();
        dialogFragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.CustomDialog);
        dialogFragment.show(fm, "fragment_add_item");
    }

    @Override
    public void onFinishEditDialog(String inputText, String colorStr) {
        Log.d(TAG, "onFinishEditDialog");
        data_list.add(0, new CounterItem(inputText, colorStr));
        dataUpdateNotifier.notifyDataUpdate(data_list);
        Toast.makeText(this, "Add item " + inputText, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStart() {
        Log.d(TAG, "onStart");

        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.ponpongi.pongicounter/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

}
