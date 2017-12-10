package com.example.android.basiccrudapp;

import android.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;


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
        TextView textViewRecordCount = (TextView) findViewById(R.id.record_count_text_view);
        textViewRecordCount.setText(recordCount + " records found");
    }

    /**
     *  Read and display records
     */
    public void readRecords(){
        LinearLayout linearLayoutRecords = (LinearLayout) findViewById(R.id.records_linear_layout);
        linearLayoutRecords.removeAllViews();

        List<ObjectStudent> students = new TableControllerStudent(this).read();
        if(students.size() > 0){
            for(ObjectStudent obj: students){
                int id = obj.mId;
                String studentFirstname = obj.mFirstName;
                String studentEmail = obj.mEmail;

                String textViewContents = studentFirstname + " - " + studentEmail;

                TextView textViewStudentItem = new TextView(this);
                textViewStudentItem.setPadding(0, 10, 0, 10);
                textViewStudentItem.setText(textViewContents);
                textViewStudentItem.setTag(Integer.toString(id));

                linearLayoutRecords.addView(textViewStudentItem);
            }
        } else{
            TextView locationItem = new TextView(this);
            locationItem.setPadding(8,8,8,8);
            locationItem.setText("No records yet.");

            linearLayoutRecords.addView(locationItem);
        }
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

        // display number of records
        countRecords();
        // read and display if records > 0
        readRecords();
    }
}
