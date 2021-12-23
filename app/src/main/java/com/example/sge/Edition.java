package com.example.sge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;

public class Edition extends AppCompatActivity implements Serializable {

    String id;
    EditText prenom,nom,email,motdepasse,sexe,age,phone;
    DatabaseManager dbManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edition);

        dbManager = new DatabaseManager(this);
        try {
            dbManager.open();
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        Intent i = getIntent();
        id = i.getSerializableExtra("Student_id").toString();

        prenom = findViewById(R.id.ePrenom);
        nom = findViewById(R.id.eNom);
        email = findViewById(R.id.eEmail);
        motdepasse = findViewById(R.id.eMp);
        sexe = findViewById(R.id.eSexe);
        age = findViewById(R.id.eAge);
        phone = findViewById(R.id.ePhone);

        fill();

    }

    private void fill()
    {
        Cursor cursor = dbManager.getOneEtudiant(this.id);
        cursor.moveToFirst();
        prenom.setText(cursor.getString((int)cursor.getColumnIndex(DatabaseHelper.ETUDIANT_PRENOM)));
        nom.setText(cursor.getString((int)cursor.getColumnIndex(DatabaseHelper.ETUDIANT_NOM)));
        email.setText(cursor.getString((int)cursor.getColumnIndex(DatabaseHelper.ETUDIANT_EMAIL)));
        motdepasse.setText(cursor.getString((int)cursor.getColumnIndex(DatabaseHelper.ETUDIANT_MP)));
        sexe.setText(cursor.getString((int)cursor.getColumnIndex(DatabaseHelper.ETUDIANT_SEXE)));
        age.setText(cursor.getString((int)cursor.getColumnIndex(DatabaseHelper.ETUDIANT_DATE)));
        phone.setText(cursor.getString((int)cursor.getColumnIndex(DatabaseHelper.ETUDIANT_TELEPHONE)));
    }

    public void ret(View view) {
        finish();
    }

    public void editer(View view) {
        String txtnom = nom.getText().toString();
        String txtprenom = prenom.getText().toString();
        String txtemail = email.getText().toString();
        String txtMp = motdepasse.getText().toString();
        String txtsexe = sexe.getText().toString();
        String txtage = age.getText().toString();
        String txtTelephone = phone.getText().toString();


        boolean checkInsert = dbManager.updateEtudiant(id,txtnom,txtprenom,txtemail,txtMp,txtTelephone,txtage,txtsexe);
        if(checkInsert){
            Toast.makeText(getApplicationContext(),"Edition effectuer", Toast.LENGTH_SHORT).show();
            //Intent  i = new Intent(getApplicationContext(),MainActivity.class);
            //startActivity(i);
            finish();
        }
        else
            Toast.makeText(getApplicationContext(),"Edition echoué", Toast.LENGTH_SHORT).show();


    }
}