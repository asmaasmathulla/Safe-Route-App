package com.s23010605.saferoute;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Contacts.db";
    public static final String TABLE_NAME = "contacts_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "MOBILE_NO";
    public static final String COL_4 = "EMAIL";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,"+
                " NAME TEXT, MOBILE_NO TEXT, EMAIL TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String name, String mobile_no, String email){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, mobile_no);
        contentValues.put(COL_4, email);
        long results = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        if(results==-1){
            return false;
        }else{
            return true;
        }
    }

    public Cursor getAllData(){
         SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
         Cursor results = sqLiteDatabase.rawQuery("select * from " + TABLE_NAME, null);
         return results;
     }

    public boolean updateData(String id, String name, String mobile_no, String email) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, mobile_no);
        contentValues.put(COL_4, email);

        int result = sqLiteDatabase.update(TABLE_NAME, contentValues, "ID = ?", new String[]{id});
        return result > 0;
    }

    public Integer deleteData(String id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME, "ID = ?", new String[] {id});
    }

    public ArrayList<ContactModel> getAllContactModels() {
        ArrayList<ContactModel> contactList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                String id = cursor.getString(cursor.getColumnIndexOrThrow("ID"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("NAME"));
                String mobileNo = cursor.getString(cursor.getColumnIndexOrThrow("MOBILE_NO"));
                String email = cursor.getString(cursor.getColumnIndexOrThrow("EMAIL"));

                ContactModel contact = new ContactModel(id, name, mobileNo, email);
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }

}
