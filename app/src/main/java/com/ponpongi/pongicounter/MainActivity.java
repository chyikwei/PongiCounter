package com.ponpongi.pongicounter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ponpongi.pongicounter.AddItemDialogFragment.EditNewItemDialogListener;

import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Type;

public class MainActivity extends AppCompatActivity implements EditNewItemDialogListener {

    private static final String PREF_NAME = "com.ponpongi.pongicounter.perference";
    private static final String PREF_KEY = "com.ponpongi.pongicounter.data_list";
    private static final String TAG = "MainActivity";
    private StaggeredGridLayoutManager sg_manager;
    private ItemAdapter adapter;
    List<CounterItem> data_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Context context = getApplicationContext();

        //load data
        SharedPreferences pref = getApplicationContext().getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        String dataString = pref.getString(PREF_KEY, "");
        Log.v(TAG, "load data:" + dataString);
        if (dataString.length() > 0) {
            Type listType = new TypeToken<ArrayList<CounterItem>>(){}.getType();
            data_list = new Gson().fromJson(dataString, listType);
        } else {
            data_list = new ArrayList<CounterItem>();
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.card_grid);
        recyclerView.setHasFixedSize(true);
        sg_manager = new StaggeredGridLayoutManager(2, 1);
        recyclerView.setLayoutManager(sg_manager);

        adapter = new ItemAdapter(data_list);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStop() {
        super.onStop();

        //store data
        SharedPreferences pref = getApplicationContext().getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        Gson gson = new Gson();
        String jsonInString = gson.toJson(data_list);
        Log.i(TAG, "Save data: " + jsonInString);
        editor.putString(PREF_KEY, jsonInString);
        editor.commit();
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
            case R.id.list_item:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showAddDialog() {
        FragmentManager fm = getSupportFragmentManager();
        AddItemDialogFragment dialogFragment = new AddItemDialogFragment();
        dialogFragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.CustomDialog);
        dialogFragment.show(fm, "fragment_add_item");
    }

    @Override
    public void onFinishEditDialog(String inputText) {
        data_list.add(0, new CounterItem(inputText));
        adapter.notifyDataSetChanged();

        Toast.makeText(this, "Add item " + inputText, Toast.LENGTH_SHORT).show();
    }
}
