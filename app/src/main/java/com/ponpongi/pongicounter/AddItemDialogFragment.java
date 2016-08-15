package com.ponpongi.pongicounter;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

/**
 * Created by chyikwei on 8/2/2016.
 */
public class AddItemDialogFragment extends DialogFragment {

    public interface EditNewItemDialogListener {
        void onFinishEditDialog(String inputText, int color);
    }

    private EditText mEditText;
    private Button okButton;
    private Button cancelButton;
    private RadioGroup colorGroup;

    public AddItemDialogFragment() {
        //empty
    }

    //TODO: retire this later. should parse color from button directly
    private int getColorFromButton(int selected) {
        int r_color_id;
        if (selected == R.id.color_syan) {
            r_color_id = R.color.colorSyan;
        } else if (selected == R.id.color_yellow) {
            r_color_id = R.color.colorYellow;
        } else if (selected == R.id.color_light_green) {
            r_color_id = R.color.colorLightGreen;
        } else if (selected == R.id.color_light_pink) {
            r_color_id = R.color.colorLigihtPink;
        } else {
            r_color_id = R.color.colorLightGray;
        }
        return ResourcesCompat.getColor(getResources(), r_color_id, null);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_add_item, container);
        mEditText = (EditText) view.findViewById(R.id.fragment_title_name);
        okButton = (Button) view.findViewById(R.id.fragment_ok);
        cancelButton = (Button) view.findViewById(R.id.fragment_cancel);
        colorGroup = (RadioGroup) view.findViewById(R.id.color_selector);

        getDialog().setTitle("New Item");
        getDialog().setCanceledOnTouchOutside(true);

        okButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditNewItemDialogListener activity = (EditNewItemDialogListener) getActivity();

                //get title
                String itemName = mEditText.getText().toString();
                //get color
                int checkedButtonId = colorGroup.getCheckedRadioButtonId();
                int color = getColorFromButton(checkedButtonId);

                activity.onFinishEditDialog(itemName, color);
                getDialog().dismiss();
                return;
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getDialog().dismiss();
                return;
            }
        });

        // Show soft keyboard automatically
        mEditText.requestFocus();
        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        return view;
    }
    /*
    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (EditorInfo.IME_ACTION_DONE == actionId) {
            // Return input text to activity
            EditNewItemDialogListener activity = (EditNewItemDialogListener) getActivity();
            activity.onFinishEditDialog(mEditText.getText().toString());
            this.dismiss();
            return true;
        }
        return false;
    }
    */
}
