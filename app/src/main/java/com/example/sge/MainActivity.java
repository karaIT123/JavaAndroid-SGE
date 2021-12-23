package com.example.sge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText email,password;
    DatabaseManager dbManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //https://www.youtube.com/watch?v=gjBKvpS7LsM
        
        dbManager = new DatabaseManager(this);
        try {
            dbManager.open();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        email = findViewById(R.id.txtUsername);
        password = findViewById(R.id.txtMp);
    }

    public void insc(View view) {
        Intent i = new Intent(this,Inscription.class);
        startActivity(i);
    }

    public void connextion(View view) {
        Cursor cursor = dbManager.getEtudiant(email.getText().toString(),password.getText().toString());
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            Intent i = new Intent(this,Logged.class);
            int index = cursor.getColumnIndex("_ID");
            String id = cursor.getString(index);
            i.putExtra("etudiant_id",id);
            startActivity(i);

            Toast.makeText(getApplicationContext(),"Connection reussie",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Connection echouer",Toast.LENGTH_SHORT).show();
        }


    }
}