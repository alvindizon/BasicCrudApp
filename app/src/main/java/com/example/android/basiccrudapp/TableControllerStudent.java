package com.example.android.basiccrudapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Alvin Dizon on 03/12/2017.
 */

public class TableControllerStudent extends DatabaseHandler {
    public TableControllerStudent(Context context) {
        super(context);
    }

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
}



