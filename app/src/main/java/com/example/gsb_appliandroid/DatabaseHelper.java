package com.example.gsb_appliandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "users.db";
    private static final String DB_TABLE = "Users_Table";
    private static final String ID = "ID";
    private static final String NAME = "NOM";
    private static final String AGE = "AGE";
    private static final String CREATE_TABLE = "CREATE TABLE " + DB_TABLE +" ("+ ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + NAME + " TEXT, " + AGE + "INTEGER " + ")";

    public DatabaseHelper(Context context){

      super(context,DB_NAME,null,1);

    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME,name);
        long result = db.insert(DB_TABLE, null, contentValues);
        return result != -1;

    }
    public Cursor viewData(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select * from "+DB_TABLE ;
        Cursor cursor = db.rawQuery(query, null);
        return cursor;

    }
}
