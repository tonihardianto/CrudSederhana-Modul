package com.hrdapr.crudandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class CrudDao extends DBHelper {
    public CrudDao(Context context) {
        super(context);
    }
    public boolean createData(String data){
        ContentValues values = new ContentValues();
        values.put("INPUT", data);

        SQLiteDatabase db = this.getWritableDatabase();
        boolean create = db.insert(TABLE_NAME, null, values) > 0;
        db.close();
        return create;
    }
    public boolean updateData(String data, String whereData){
        ContentValues values = new ContentValues();
        values.put("INPUT", data);

        SQLiteDatabase db = this.getReadableDatabase();
        boolean update = db.update(TABLE_NAME, values, " INPUT = ?", new
        String[]{whereData}) > 0;
        db.close();
        return update;
    }
    public boolean deleteData(String whereData){
        SQLiteDatabase db = this.getWritableDatabase();
        boolean delete = db.delete(TABLE_NAME, "INPUT = ? ", new
        String[]{whereData}) > 0;
        db.close();
        return delete;
    }
    public String getData(){
        Cursor cursor = null;
        String empName = "";
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            cursor = db.rawQuery("SELECT INPUT FROM " + TABLE_NAME, null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                empName = cursor.getString(cursor.getColumnIndex("INPUT"));
            }
            return empName;
        } finally {
            cursor.close();
        }
    }
}
