package com.ponpongi.pongicounter;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.res.ResourcesCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.ponpongi.pongicounter.utils.ColorUtils;
import com.ponpongi.pongicounter.utils.SelectorColors;

/**
 * Created by chyikwei on 8/2/2016.
 */
public class AddItemDialogFragment extends DialogFragment {

    public interface EditNewItemDialogListener {
        void onFinishEditDialog(String inputText, String colorStr);
    }

    private EditText mEditText;
    private Button okButton;
    private Button cancelButton;
    private RadioGroup colorGroup;

    public AddItemDialogFragment() {
        //empty
    }

    //TODO: retire this later. should parse color from button directly
    private String getColorFromButton(int selected) {
        SelectorColors color;
        switch (selected) {
            case  R.id.color_syan:
                color = SelectorColors.SYAN;
                break;
            case  R.id.color_red:
                color = SelectorColors.RED;
                break;
            case  R.id.color_yellow:
                color = SelectorColors.YELLOW;
                break;
            case  R.id.color_light_green:
                color = SelectorColors.LIGHTGREEN;
                break;
            case  R.id.color_light_pink:
                color = SelectorColors.LIGHTPINK;
                break;
            case  R.id.color_purple:
                color = SelectorColors.PURPLE;
                break;
            default:
                color = SelectorColors.SYAN;
        }
        return color.getCode();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setTitle("Add a counter");
        dialog.setCanceledOnTouchOutside(true);
        //setStyle(DialogFragment.STYLE_NORMAL, R.style.CustomDialog);
        // request a window without the title
        //dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_add_item, container);
        mEditText = (EditText) view.findViewById(R.id.fragment_title_name);
        okButton = (Button) view.findViewById(R.id.fragment_ok);
        cancelButton = (Button) view.findViewById(R.id.fragment_cancel);
        colorGroup = (RadioGroup) view.findViewById(R.id.color_selector);

        // input text
        mEditText.addTextChangedListener(new InputTextLengthWatcher(14) {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                super.onTextChanged(s, start, before, count);
                if(s.toString().trim().length()==0){
                    okButton.setEnabled(false);
                } else {
                    okButton.setEnabled(true);
                }
            }
        });

        //ok button
        //disable by default
        okButton.setEnabled(false);
        okButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditNewItemDialogListener activity = (EditNewItemDialogListener) getActivity();

                //get title
                String itemName = mEditText.getText().toString();
                //get color
                int checkedButtonId = colorGroup.getCheckedRadioButtonId();
                String colorStr = getColorFromButton(checkedButtonId);

                activity.onFinishEditDialog(itemName, colorStr);
                getDialog().dismiss();
                return;
            }
        });

        //cancel button
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
