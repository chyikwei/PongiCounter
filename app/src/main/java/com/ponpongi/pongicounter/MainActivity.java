package com.ponpongi.pongicounter;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private StaggeredGridLayoutManager sg_manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Context context = getApplicationContext();

        // references to our images
        List<CounterItem> data_list = new ArrayList<CounterItem>();
        for (int i=1; i <= 20; i++) {
            data_list.add(new CounterItem("Item " + i));
        }
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.card_grid);
        recyclerView.setHasFixedSize(true);
        sg_manager = new StaggeredGridLayoutManager(2, 1);
        recyclerView.setLayoutManager(sg_manager);

        ItemAdapter adapter = new ItemAdapter(data_list);
        recyclerView.setAdapter(adapter);
        /*
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
                CounterItem item = items[pos];
                item.increment();
                TextView it = (TextView) view.findViewById(R.id.item_title);
                TextView ic = (TextView) view.findViewById(R.id.item_count);
                it.setText(item.getName());
                ic.setText((Integer.toString(item.getCount())));
                Toast.makeText(context, item.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
