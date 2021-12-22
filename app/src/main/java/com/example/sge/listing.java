package com.example.sge;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class listing extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,getList());
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
    }

    private ArrayList<String> getList() {
        ArrayList<String> list = new ArrayList<String>();

        DatabaseManager databaseManager = new DatabaseManager(getApplicationContext());
        try {
            databaseManager.open();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        Cursor cursor = databaseManager.fetch();
        if (cursor.moveToFirst()) {
            do {

                list.add(cursor.getString((int) cursor.getColumnIndex(DatabaseHelper.ETUDIANT_PRENOM)) + " " + cursor.getString((int) cursor.getColumnIndex(DatabaseHelper.ETUDIANT_NOM)));
            }
            while (cursor.moveToNext());
        }

        return list;
    }
}