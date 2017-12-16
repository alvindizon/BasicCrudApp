package com.example.android.basiccrudapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alvin Dizon on 03/12/2017.
 */

public class TableControllerStudent extends DatabaseHandler {
    public TableControllerStudent(Context context) {
        super(context);
    }

    /**
     *
     * @param objectStudent is the object that contains the firstname and email of a student
     * @return true if values were successfully added, false if not
     */
    public boolean create(ObjectStudent objectStudent) {
        ContentValues values = new ContentValues();
        values.put("firstname", objectStudent.mFirstName);
        values.put("email", objectStudent.mEmail);

        SQLiteDatabase db = this.getWritableDatabase();

        boolean createSuccessful = db.insert("students", null, values) > 0;
        db.close();

        return createSuccessful;
    }

    /**
     *  This function returns the number of existing records in the database
     * @return recordCount is the number of existing records in the database
     */
    public int count(){
        SQLiteDatabase db = this.getWritableDatabase();

        String sql = "SELECT * FROM students";
        int recordCount = db.rawQuery(sql, null).getCount();
        db.close();

        return recordCount;
    }

    public List<ObjectStudent> read(){
        List<ObjectStudent> recordsList = new ArrayList<ObjectStudent>();

        String sql = "SELECT * FROM students ORDER BY id DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if(cursor.moveToFirst()){
            do{
                int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
                String studentFirstname = cursor.getString(cursor.getColumnIndex("firstname"));
                String studentEmail = cursor.getString(cursor.getColumnIndex("email"));

                ObjectStudent objectStudent = new ObjectStudent();
                objectStudent.mId = id;
                objectStudent.mFirstName = studentFirstname;
                objectStudent.mEmail = studentEmail;

                recordsList.add(objectStudent);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return recordsList;
    }

    /**
     * Read single record
     * @param studentId
     * @return
     */
    public ObjectStudent readSingleRecord(int studentId){
        ObjectStudent objectStudent = null;
        String sql = "SELECT * FROM students WHERE id = " + studentId;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if(cursor.moveToFirst()){
            int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
            String firstName = cursor.getString(cursor.getColumnIndex("firstname"));
            String email = cursor.getString(cursor.getColumnIndex("email"));

            objectStudent = new ObjectStudent();
            objectStudent.mId = id;
            objectStudent.mFirstName = firstName;
            objectStudent.mEmail = email;
        }

        cursor.close();
        db.close();

        return objectStudent;
    }

    /**
     * Update record
     * @param objectStudent is the object that contains the firstname and email of a student
     * @return
     */
    public boolean update(ObjectStudent objectStudent){
        ContentValues values = new ContentValues();

        values.put("firstname", objectStudent.mFirstName);
        values.put("email", objectStudent.mEmail);

        String where = "id = ?";
        String[] whereArgs = { Integer.toString(objectStudent.mId)};
        SQLiteDatabase db = this.getWritableDatabase();

        boolean updateSuccessful = db.update("students", values, where, whereArgs) > 0;
        db.close();

        return updateSuccessful;
    }
}



