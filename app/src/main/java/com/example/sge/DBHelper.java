package com.example.sge;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "sge.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE etudiant(id INT PRIMARY KEY AUTOINCREMENT," +
                "prenom TEXT," +
                "nom TEXT," +
                "email TEXT," +
                "motdepasse TEXT," +
                "telephone TEXT," +
                "age TEXT," +
                "sexe TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists etudiant ");
    }

    public boolean insertEtudiant(String nom, String prenom, String email,String motdepasse,String telephone,String age,String sexe){

        SQLiteDatabase DB = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("prenom",prenom);
        cv.put("nom",nom);
        cv.put("email",email);
        cv.put("motdepasse",motdepasse);
        cv.put("telephone",telephone);
        cv.put("age",age);
        cv.put("sexe",sexe);

        long result = DB.insert("etudiant",null,cv);
        return result != -1;

    }

    public boolean updateEtudiant(String nom, String prenom, String email,String motdepasse,String telephone,String age,String sexe){
        SQLiteDatabase DB = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("prenom",prenom);
        cv.put("nom",nom);
        cv.put("motdepasse",motdepasse);
        cv.put("telephone",telephone);
        cv.put("age",age);
        cv.put("sexe",sexe);

        Cursor cursor = DB.rawQuery("SELECT * FROM etudiant WHERE email = ?", new String[] {email});
        if(cursor.getCount() > 0)
        {
            long result = DB.update("etudiant",cv,"email=?", new String[] {email});
            return result != -1;
        }
        else{
            return false;
        }
    }

    public boolean deleteEtudiant(String email){
        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("SELECT * FROM etudiant WHERE email = ?", new String[] {email});
        if(cursor.getCount() > 0)
        {
            long result = DB.delete("etudiant","email=?", new String[] {email});
            return result != -1;
        }
        else{
            return false;
        }
    }

    public Cursor getEtudiant(String email){
        SQLiteDatabase DB = this.getWritableDatabase();

        return DB.rawQuery("SELECT * FROM etudiant", null);
    }


}
