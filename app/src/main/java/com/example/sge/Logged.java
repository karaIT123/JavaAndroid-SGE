package com.example.sge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.io.Serializable;

public class Logged extends AppCompatActivity implements Serializable {

    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged);

        Intent i = getIntent();
        id = i.getSerializableExtra("etudiant_id").toString();

    }

    public void moncompte(View view) {
        Intent i = new Intent(getApplicationContext(),Edition.class);
        i.putExtra("Student_id",this.id);
        startActivity(i);
    }

    public void listes(View view) {
        startActivity(new Intent(getApplicationContext(),listing.class));
    }

    public void deconnection(View view) {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }
}