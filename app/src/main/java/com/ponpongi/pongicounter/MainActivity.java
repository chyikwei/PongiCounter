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
        gv.setAdapter(new ItemAdapter(this, items));

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
                items[pos].increment();
                TextView tv = (TextView) view;
                tv.setText(items[pos].toString());
                Toast.makeText(context, items[pos].toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
