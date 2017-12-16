package com.example.android.basiccrudapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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
                        if(i == 0){
                            editRecord(Integer.parseInt(mId));
                        }
                        dialogInterface.dismiss();
                    }
                }).show();
        return false;
    }

    public void editRecord(final int studentId){
        final TableControllerStudent tableControllerStudent = new TableControllerStudent(mContext);
        final ObjectStudent objectStudent = tableControllerStudent.readSingleRecord(studentId);
        // inflate record_form.xml for record updating
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.record_form, null, false);
        // form elements here
        final EditText editTextStudentFirstName = (EditText) formElementsView.findViewById(R.id.edit_first_name);
        final EditText editTextStudentEmail = (EditText) formElementsView.findViewById(R.id.edit_email);
        // show a dialog with the form for editing a single record
        new AlertDialog.Builder(mContext)
                .setView(formElementsView)
                .setTitle("Edit Record")
                .setPositiveButton("Save Changes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ObjectStudent objectStudent = new ObjectStudent();
                                objectStudent.mId = studentId;
                                objectStudent.mFirstName = editTextStudentFirstName.getText().toString();
                                objectStudent.mEmail = editTextStudentEmail.getText().toString();
                                // update the record and tell the user if the update was successful
                                boolean updateSuccessful = tableControllerStudent.update(objectStudent);

                                if(updateSuccessful){
                                    Toast.makeText(mContext, "Student record was updated.", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(mContext, "Unable to update student record.", Toast.LENGTH_SHORT).show();
                                }
                                // refresh record count and list after update
                                ((MainActivity) mContext).countRecords();
                                ((MainActivity) mContext).readRecords();
                                dialogInterface.cancel();
                            }
                        }).show();
    }
}
