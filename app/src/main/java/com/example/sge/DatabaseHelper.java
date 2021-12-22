package com.example.sge;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    static final String DATABASE_NAME = "MYSGE.DB";
    static final int DATABASE_VERSION = 1;

    static final String DATABASE_TABLE = "ETUDIANT";
    static final String ETUDIANT_ID = "_ID";
    static final String ETUDIANT_NOM = "nom";
    static final String ETUDIANT_PRENOM = "prenom";
    static final String ETUDIANT_EMAIL = "email";
    static final String ETUDIANT_MP = "motdepasse";
    static final String ETUDIANT_SEXE = "sexe";
    static final String ETUDIANT_TELEPHONE = "telephone";
    static final String ETUDIANT_DATE = "age";

    static final String CREATE_DB_QUERY = " CREATE TABLE " + DATABASE_TABLE
            + " ( " + ETUDIANT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            ETUDIANT_NOM + " TEXT NOT NULL, " +
            ETUDIANT_PRENOM + " TEXT NOT NULL, " +
            ETUDIANT_EMAIL + " TEXT NOT NULL, " +
            ETUDIANT_MP + " TEXT NOT NULL, " +
            ETUDIANT_DATE + " TEXT NOT NULL, " +
            ETUDIANT_SEXE + " TEXT NOT NULL, " +
            ETUDIANT_TELEPHONE + " TEXT NOT NULL" +
            " );";



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DB_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
    }
}
