package com.example.gsb_appliandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;



public class DatabaseHelperFF extends SQLiteOpenHelper {
    private static final String DB_NAME = "gsb-android.db";
    private static final String DB_TABLE = "fraisForfait";
    private static final String ID = "_id";
    private static final String LIBELLE = "libelle";
    private static final String MONTANT = "montant";
    private static final String CREATE_TABLE = "CREATE TABLE " + DB_TABLE + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + LIBELLE + " TEXT, " + MONTANT + "TEXT " + ")";

    public DatabaseHelperFF(Context context) {

        super(context, DB_NAME, null, 1);

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

    public boolean insertData(EditText libelle, EditText montant, byte[] _id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID, _id);
        contentValues.put(LIBELLE, String.valueOf(libelle));
        contentValues.put(MONTANT, String.valueOf(montant));
        long result = db.insert(DB_TABLE, null, contentValues);
        return result != -1;

    }

    public Cursor listeFF() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select * from " + DB_TABLE;
        Cursor cursor = db.rawQuery(query, null);
        return cursor;

    }
    public Cursor typeFF() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select * from typeFraisForfait";
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }
}