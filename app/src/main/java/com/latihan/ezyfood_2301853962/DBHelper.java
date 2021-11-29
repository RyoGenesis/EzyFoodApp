package com.latihan.ezyfood_2301853962;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "Order", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create Table itemOrder(" +
                "id INTEGER primary key AUTOINCREMENT," +
                "name TEXT," +
                "price NUMERIC," +
                "qty INTEGER" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists itemOrder");
    }

    public boolean insertData(String name, double price, int qty){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("price",price);
        contentValues.put("qty",qty);
        long result = db.insert("itemOrder",null, contentValues);
        if (result == -1) return false;
        return true;

    }

    public Cursor getDataItemOrder(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from itemOrder",null);
        return cursor;
    }

    public Cursor searchDataFromItemOrder(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from itemOrder where name=?",new String[]{name});
        return cursor;
    }

    public boolean updateData(String name, int qty){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("qty",qty);
        Cursor cursor = searchDataFromItemOrder(name);

        if (cursor.getCount()>0){
            long result = db.update("itemOrder", contentValues,
                    "name=?",new String[]{name});
            cursor.close();
            if (result <=0) return false;
            return true;
        }
        cursor.close();
        return false;
    }

    public boolean deleteData(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from itemOrder where id=?",
                new String[]{Integer.toString(id)});

        if (cursor.getCount()>0){
            long result = db.delete("itemOrder",
                    "id=?",new String[]{Integer.toString(id)});
            if (result <=0) return false;
            return true;
        }
        return false;
    }

    public boolean cleanOrder(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("itemOrder",null,null);
        //delete all order data untuk new order
        return true;
    }

}
