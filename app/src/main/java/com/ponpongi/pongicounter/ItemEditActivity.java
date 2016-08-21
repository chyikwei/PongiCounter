package com.ponpongi.pongicounter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.ponpongi.pongicounter.utils.Constants;
import com.ponpongi.pongicounter.utils.SelectorColors;

public class ItemEditActivity extends AppCompatActivity {

    private String colorStr;
    private static String TAG = "ItemEditActivity";
    private int count;
    private int index;

    EditText editName;
    EditText editCount;
    RadioGroup colorSelector;
    ImageButton resetButton;
    MenuItem menuSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_edit);

        editName = (EditText) findViewById(R.id.edit_name);
        editCount = (EditText) findViewById(R.id.edit_number);
        colorSelector = (RadioGroup) findViewById(R.id.edit_color_selector);
        resetButton = (ImageButton) findViewById(R.id.reset_btn);
        Intent intent = getIntent();
        String name = intent.getStringExtra(Constants.EDIT_ITEM_NAME);
        Log.d(TAG, "load name: " + name);
        editName.setText(name);
        editName.setSelection(name.length());

        count = intent.getIntExtra(Constants.EDIT_ITEM_COUNT, -1);
        Log.d(TAG, "load count: " + name);
        assert count >= 0;
        editCount.setText(Integer.toString(count));
        this.index = intent.getIntExtra(Constants.EDIT_ITEM_INDEX, -1);
        Log.d(TAG, "load index: " + this.index);
        assert index >= 0;
        this.colorStr = intent.getStringExtra(Constants.EDIT_ITEM_COLOR);
        Log.d(TAG, "load colorStr: " + this.colorStr);
        setColorButton(this.colorStr);

        //back icon
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_close_white_24dp);

        Log.d(TAG, "setListener");
        setListener();
    }

    private void setListener() {
        //editName
        // input text
        editName.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                editName.setSelection(editName.getText().length());
                return false;
            }
        });

        //reset
        resetButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //reset editCount
                        editCount.setText("0");
                        editCount.setSelection(1);
                    }
                }
        );

    }

    private void setColorButton(String colorStr) {
        if (colorSelector == null) {
            return;
        }

        SelectorColors color = SelectorColors.getByColorStr(colorStr);
        RadioButton btn;
        Log.d(TAG, "switch by: " + color.toString());
        switch (color) {
            case SYAN:
                Log.d(TAG, "SYAN selected");
                btn = (RadioButton) colorSelector.findViewById(R.id.edit_color_syan);
                btn.setChecked(true);
                break;

            case YELLOW:
                Log.d(TAG, "YELLOW selected");
                btn = (RadioButton) colorSelector.findViewById(R.id.edit_color_yellow);
                btn.setChecked(true);
                break;

            case LIGHTGREEN:
                Log.d(TAG, "LIGHTGREEN selected");
                btn = (RadioButton) colorSelector.findViewById(R.id.edit_color_light_green);
                btn.setChecked(true);
                break;

            case LIGHTPINK:
                Log.d(TAG, "LIGHTPINK selected");
                btn = (RadioButton) colorSelector.findViewById(R.id.edit_color_light_pink);
                btn.setChecked(true);
                break;

            default:
                Log.d(TAG, "default selected");
                btn = (RadioButton) colorSelector.findViewById(R.id.edit_color_syan);
                btn.setChecked(true);
        }

    }

    //TODO: retire this later. should parse color from button directly
    private String getColorFromButton(int selected) {
        SelectorColors color;
        switch (selected) {
            case  R.id.edit_color_syan:
                color = SelectorColors.SYAN;
                break;
            case  R.id.edit_color_yellow:
                color = SelectorColors.YELLOW;
                break;
            case  R.id.edit_color_light_green:
                color = SelectorColors.LIGHTGREEN;
                break;
            case  R.id.edit_color_light_pink:
                color = SelectorColors.LIGHTPINK;
                break;
            default:
                color = SelectorColors.SYAN;
        }
        return color.getCode();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(TAG, "onCreateOptionsMenu");
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return true;
    }

    private int getCount(String countStr) {
        if (countStr.length() == 0) {
            return 0;
        } else {
            return Integer.parseInt(countStr);
        }
    }

    private Intent wrapItemData() {
        Intent intent = new Intent();
        intent.putExtra(Constants.EDIT_ITEM_INDEX, this.index);
        intent.putExtra(Constants.EDIT_ITEM_NAME, editName.getText().toString());
        intent.putExtra(Constants.EDIT_ITEM_COUNT, getCount(editCount.getText().toString()));
        intent.putExtra(Constants.EDIT_ITEM_COLOR, getColorFromButton(colorSelector.getCheckedRadioButtonId()));
        return intent;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_save:
                Log.d(TAG, "save clicked");
                Context context = getApplicationContext();
                String name = editName.getText().toString();
                if (name.length() == 0) {
                    Toast.makeText(context, "Name cannot be empty!", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = wrapItemData();
                    setResult(Constants.EDIT_ITEM_DONE, intent);
                    finish();
                    Toast.makeText(context, "item saved", Toast.LENGTH_SHORT).show();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
