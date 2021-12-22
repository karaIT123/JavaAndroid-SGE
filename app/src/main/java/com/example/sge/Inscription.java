package com.example.sge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Inscription extends AppCompatActivity {

    EditText prenom, nom, email, mp, sexe, age, telephone;
    DBHelper DB;
    DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        prenom = findViewById(R.id.txtPrenom);
        nom = findViewById(R.id.txtNom);
        email = findViewById(R.id.txtEmail);
        mp = findViewById(R.id.txtMdp);
        sexe = findViewById(R.id.txtSexe);
        age = findViewById(R.id.txtAge);
        telephone = findViewById(R.id.txtPhone);

        DB = new DBHelper(this);

        dbManager = new DatabaseManager(this);
        try {
            dbManager.open();
        }catch (Exception e)
        {
            e.printStackTrace();
        }


    }

    public void seconn(View view) {
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }

    public void inscr(View view) {
        String txtnom = nom.getText().toString();
        String txtprenom = prenom.getText().toString();
        String txtemail = email.getText().toString();
        String txtMp = mp.getText().toString();
        String txtsexe = sexe.getText().toString();
        String txtage = age.getText().toString();
        String txtTelephone = telephone.getText().toString();

        boolean checkInsert = dbManager.insertEtudiant(txtnom,txtprenom,txtemail,txtMp,txtTelephone,txtage,txtsexe);
        if(checkInsert){
            Toast.makeText(getApplicationContext(),"Inscription effectuer", Toast.LENGTH_SHORT).show();
            Intent  i = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);
        }
        else
            Toast.makeText(getApplicationContext(),"Inscription echoué", Toast.LENGTH_SHORT).show();

    }
}