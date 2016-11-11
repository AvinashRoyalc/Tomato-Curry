package com.tomato.curry.Data;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.tomato.curry.R;

/**
 * Created by Chandana on 12/22/2015.
 */

public class CustomProgressDialog extends ProgressDialog {

    Context context;

    public CustomProgressDialog(Context context, Boolean isLocalText) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_custom_progress_dialog);
    }


    @Override
    public void show() {
        super.show();
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }


}
