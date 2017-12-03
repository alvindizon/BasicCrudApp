package com.example.android.basiccrudapp;

import android.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends FragmentActivity
        implements NoticeDialogFragment.NoticeDialogListener {

    public void showNoticeDialog() {
            // Create an instance of the dialog fragment and show it
            DialogFragment dialog = new NoticeDialogFragment();
            dialog.show(getFragmentManager(), "NoticeDialogFragment");
    }

    // The dialog fragment receives a reference to this Activity through the
    // Fragment.onAttach() callback, which it uses to call the following methods
    // defined by the NoticeDialogFragment.NoticeDialogListener interface
    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
            // User touched the dialog's positive button
    }

    /**
     * Count existing records
     */
    public void countRecords(){
        int recordCount = new TableControllerStudent(this).count();
        TextView textViewRecordCount = (TextView) findViewById(R.id.record_count_text);
        textViewRecordCount.setText(recordCount + " records found");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button createRecordButton = findViewById(R.id.create_record_button);
        createRecordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNoticeDialog();
            }
        });
    }
}
