package com.example.sge;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLDataException;

public class DatabaseManager {
    private DatabaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public DatabaseManager(Context ctx){
        this.context = ctx;
    }

    public DatabaseManager open() throws SQLDataException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close()
    {
        dbHelper.close();
    }

    public boolean insertEtudiant(String nom, String prenom, String email,String motdepasse,String telephone,String date,String sexe){
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.ETUDIANT_PRENOM,prenom);
        cv.put(DatabaseHelper.ETUDIANT_NOM,nom);
        cv.put(DatabaseHelper.ETUDIANT_EMAIL,email);
        cv.put(DatabaseHelper.ETUDIANT_MP,motdepasse);
        cv.put(DatabaseHelper.ETUDIANT_TELEPHONE,telephone);
        cv.put(DatabaseHelper.ETUDIANT_DATE,date);
        cv.put(DatabaseHelper.ETUDIANT_SEXE,sexe);

        long result = database.insert(DatabaseHelper.DATABASE_TABLE,null,cv);
        return result != -1;
    }

    public Cursor fetch(){
        String [] columns = new String[] {DatabaseHelper.ETUDIANT_ID,
                DatabaseHelper.ETUDIANT_PRENOM,
                DatabaseHelper.ETUDIANT_NOM,
                DatabaseHelper.ETUDIANT_EMAIL,
                DatabaseHelper.ETUDIANT_MP,
                DatabaseHelper.ETUDIANT_SEXE,
                DatabaseHelper.ETUDIANT_DATE,
                DatabaseHelper.ETUDIANT_TELEPHONE};

        Cursor cursor = database.query(DatabaseHelper.DATABASE_TABLE,columns,null,null,null,null,null);
        if(cursor != null)
        {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor getALLEtudiant(){
        return database.rawQuery("SELECT * FROM etudiant",null);

    }

    public Cursor getEtudiant(String email,String motdepasse){
        return database.rawQuery("SELECT * FROM etudiant WHERE " +  DatabaseHelper.ETUDIANT_EMAIL + " = ? AND " + DatabaseHelper.ETUDIANT_MP + " = ?", new String[] {email,motdepasse});

    }

    public Cursor getOneEtudiant(String id){
        return database.rawQuery("SELECT * FROM etudiant WHERE " +  DatabaseHelper.ETUDIANT_ID + " = ?" , new String[] {id});

    }

    public boolean updateEtudiant(String id,String nom, String prenom, String email,String motdepasse,String telephone,String date,String sexe){
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.ETUDIANT_PRENOM,prenom);
        cv.put(DatabaseHelper.ETUDIANT_NOM,nom);
        cv.put(DatabaseHelper.ETUDIANT_EMAIL,email);
        cv.put(DatabaseHelper.ETUDIANT_MP,motdepasse);
        cv.put(DatabaseHelper.ETUDIANT_TELEPHONE,telephone);
        cv.put(DatabaseHelper.ETUDIANT_DATE,date);
        cv.put(DatabaseHelper.ETUDIANT_SEXE,sexe);


        long result = database.update(DatabaseHelper.DATABASE_TABLE,cv,DatabaseHelper.ETUDIANT_ID + "=" + id,null);
        return result != -1;
    }

    public boolean deleteEtudiant(long id){
        try {
            database.delete(DatabaseHelper.DATABASE_NAME,DatabaseHelper.ETUDIANT_ID + "=" + id,null);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }

    }


}
