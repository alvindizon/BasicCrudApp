package com.example.android.basiccrudapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;

/**
 * Created by Alvin Dizon on 16/12/2017.
 */

public class OnLongClickListenerStudentRecord implements View.OnLongClickListener {

    Context mContext;
    String mId;
    @Override
    public boolean onLongClick(View view) {
        mContext = view.getContext();
        mId = view.getTag().toString();

        final CharSequence[] items = {"Edit", "Delete"};

        new AlertDialog.Builder(mContext).setTitle("Student Record")
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).show();
        return false;
    }
}
