package com.ponpongi.pongicounter;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //ViewHolder vd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Context context = getApplicationContext();

        // references to our images
        final CounterItem items[]  = {
                new CounterItem("Item 1"),
                new CounterItem("Item 2"),
                new CounterItem("Item 3")
        };
        GridView gv = (GridView) findViewById(R.id.main_grid);
        gv.setAdapter(new ItemAdapter(MainActivity.this, items));

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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
