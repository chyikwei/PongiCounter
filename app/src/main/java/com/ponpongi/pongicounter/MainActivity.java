package com.ponpongi.pongicounter;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ponpongi.pongicounter.AddItemDialogFragment.EditNewItemDialogListener;
import com.ponpongi.pongicounter.notifier.DataUpdateNotifier;

import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Type;

public class MainActivity extends AppCompatActivity implements EditNewItemDialogListener {

    private static final String PREF_NAME = "com.ponpongi.pongicounter.perference";
    private static final String PREF_KEY = "com.ponpongi.pongicounter.data_list";
    private static final String PREF_ISCARDVIEW_KEY = "com.ponpongi.pongicounter.is_card_view";
    private static final String TAG = "MainActivity";
    private boolean isCardView;
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
        SharedPreferences pref = getApplicationContext().getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        data_list = load_data_list(pref);
        isCardView = load_is_cardview(pref);

        Log.i(TAG, "load cardView: " + isCardView);
        if (isCardView) {
            showCardView();
        } else {
            showListView();
        }

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private List<CounterItem> load_data_list(SharedPreferences pref) {
        List<CounterItem> data;
        String dataString = pref.getString(PREF_KEY, "");
        Log.v(TAG, "load data:" + dataString);
        if (dataString.length() > 0) {
            Type listType = new TypeToken<ArrayList<CounterItem>>() {
            }.getType();
            data = new Gson().fromJson(dataString, listType);
        } else {
            data = new ArrayList<CounterItem>();
        }
        return data;
    }

    private boolean load_is_cardview(SharedPreferences pref) {
        return pref.getBoolean(PREF_ISCARDVIEW_KEY, true);
    }

    @Override
    protected void onStop() {
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

        //store data
        SharedPreferences pref = getApplicationContext().getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        Gson gson = new Gson();
        String jsonInString = gson.toJson(data_list);
        Log.i(TAG, "Save data: " + jsonInString);
        editor.putString(PREF_KEY, jsonInString);
        editor.putBoolean(PREF_ISCARDVIEW_KEY, isCardView);
        Log.i(TAG, "Save cardView: " + isCardView);
        editor.commit();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.disconnect();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.add_item:
                showAddDialog();
                return true;
            case R.id.grid_item:
                if (!isCardView) {
                    isCardView = true;
                    showCardView();
                }
                return true;
            case R.id.list_item:
                if (isCardView) {
                    isCardView = false;
                    showListView();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showCardView() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        CardViewFragment fragment = CardViewFragment.newInstance(data_list);
        transaction.replace(R.id.main_fragment, fragment);
        transaction.commit();
        dataUpdateNotifier = fragment;
    }

    private void showListView() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        ListViewFragment fragment = ListViewFragment.newInstance(data_list);
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
    public void onFinishEditDialog(String inputText, int color) {
        data_list.add(0, new CounterItem(inputText, color));
        //adapter.notifyDataSetChanged();
        dataUpdateNotifier.notifyDataUpdate(data_list);
        Toast.makeText(this, "Add item " + inputText, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStart() {
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
