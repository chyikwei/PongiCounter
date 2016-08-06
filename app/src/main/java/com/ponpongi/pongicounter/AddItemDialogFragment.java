package com.ponpongi.pongicounter;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by chyikwei on 8/2/2016.
 */
public class AddItemDialogFragment extends DialogFragment {

    public interface EditNewItemDialogListener {
        void onFinishEditDialog(String inputText);
    }

    private EditText mEditText;
    private Button okButton;
    private Button cancelButton;

    public AddItemDialogFragment() {
        //empty
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_item, container);
        mEditText = (EditText) view.findViewById(R.id.fragment_title_name);
        okButton = (Button) view.findViewById(R.id.fragment_ok);
        cancelButton = (Button) view.findViewById(R.id.fragment_cancel);
        getDialog().setTitle("New Item");
        getDialog().setCanceledOnTouchOutside(true);

        okButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditNewItemDialogListener activity = (EditNewItemDialogListener) getActivity();
                activity.onFinishEditDialog(mEditText.getText().toString());
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
