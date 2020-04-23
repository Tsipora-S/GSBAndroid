package com.example.gsb_appliandroid;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Connection;

public class DatabaseHelperCnx extends SQLiteOpenHelper {
        private static final String DB_NAME = "gsb-android.db";
        private static final String DB_TABLE = "utilisateur";
        private static final String ID= "_id";
        private static final String NOM= "nom";
        private static final String PRENOM= "prenom";
        private static final String LOGIN ="login";
        private static final String MDP= "mdp";
        private static final String ADRESSE= "adresse";
        private static final String CP ="cp";
        private static final String VILLE= "ville";
        private static final String DATEEMBAUCHE= "dateembauche";
        private Connection connection;
        public DatabaseHelperCnx(Context context){

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

    }
