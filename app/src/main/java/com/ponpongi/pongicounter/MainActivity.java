package com.ponpongi.pongicounter;

import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import com.ponpongi.pongicounter.AddItemDialogFragment.EditNewItemDialogListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements EditNewItemDialogListener {

    private StaggeredGridLayoutManager sg_manager;
    private ItemAdapter adapter;
    List<CounterItem> data_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Context context = getApplicationContext();

        // references to our images
        data_list = new ArrayList<CounterItem>();
        //TODO: load real data
        for (int i=1; i <= 2; i++) {
            data_list.add(new CounterItem("Item " + i));
        }
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.card_grid);
        recyclerView.setHasFixedSize(true);
        sg_manager = new StaggeredGridLayoutManager(2, 1);
        recyclerView.setLayoutManager(sg_manager);

        adapter = new ItemAdapter(data_list);
        recyclerView.setAdapter(adapter);
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
        data_list.add(new CounterItem(inputText));
        adapter.notifyItemInserted(data_list.size() - 1);

        Toast.makeText(this, "Add item " + inputText, Toast.LENGTH_SHORT).show();
    }
}
