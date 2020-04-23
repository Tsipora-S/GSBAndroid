package com.example.gsb_appliandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.EditText;

import java.sql.Connection;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class DatabaseHelperHF extends SQLiteOpenHelper {
    private static final String DB_NAME = "gsb-android.db";
    private static final String DB_TABLE = "fraisHorsForfait";
    private static final String ID= "_id";
    private static final String LIBELLE= "libelle";
    private static final String MONTANT= "montant";
    private static final String DATE ="date";
    //private static final String CREATE_TABLE= "CREATE TABLE IF NOT EXISTS" + DB_TABLE +" ("+ ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + LIBELLE + " TEXT, " + MONTANT+ "TEXT, " + DATE + "TEXT"+")";
    private Connection connection;
    public DatabaseHelperHF(Context context){

      super(context,DB_NAME,null,1);

    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    //sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(EditText libelle){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(LIBELLE, String.valueOf(libelle));
        long result = db.insert(DB_TABLE, null, contentValues);
        return result != -1;

    }
    public Cursor viewData(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select * from "+DB_TABLE;
        Cursor cursor = db.rawQuery(query, null);
        return cursor;

    }
    public ArrayList<String> fetch_Edittext() {
        ArrayList<String> arr_ = new ArrayList<String>();
        String selectQuery = "Select * FROM fraisHorsForfait ORDER BY id";

        SQLiteDatabase bd = this.getWritableDatabase();
        Cursor cursor = bd.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                arr_.add(cursor.getString(0));

            } while (cursor.moveToNext());
        }
        bd.close();
        return arr_;
    }
    public fraisHF getFHF(int id) {
        Log.i(TAG, "MyDatabaseHelper.getNote ... " + id);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DB_TABLE, new String[] { ID,
                        LIBELLE, MONTANT }, id + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        fraisHF fraisHF = new fraisHF(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2),cursor.getString(3));
        // return note
        return fraisHF;
    }

    public int getFHFCount() {
        Log.i(TAG, "MyDatabaseHelper.getNotesCount ... " );

        String countQuery = "SELECT  * FROM " + DB_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();

        cursor.close();

        // return count
        return count;
    }


    public int updateHF(fraisHF frais) {
        Log.i(TAG, "MyDatabaseHelper.updateFHF ... "  + frais.getLibelle());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(LIBELLE, frais.getLibelle());
        values.put(MONTANT, frais.getMontant());
        values.put(DATE, frais.getDate());

        // updating row
        String id;
        int update = db.update(DB_TABLE, values, ID + " = ?",
                new String[]{String.valueOf(frais.getId())});
        return update;
    }

    public Cursor listeFHF(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select * from "+DB_TABLE ;
        Cursor cursor = db.rawQuery(query, null);
        return cursor;

    }

}
