package com.example.comeandsee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomePageActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnout;
    private Button btnpro;
    private Button btnor;
    private Button btv;
    private Button btns;
    private Button btnf;
    private Button btnaniid;
    private Button btng;
    private Profile_data profile;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
        firebaseAuth = FirebaseAuth.getInstance();
        btnout = findViewById(R.id.btnout);
        btnout.setOnClickListener(this);
        btnpro = findViewById(R.id.btnpro);
        btnpro.setOnClickListener(this);
        btnor = findViewById(R.id.btnor);
        btnor.setOnClickListener(this);
        btv = findViewById(R.id.btv);
        btv.setOnClickListener(this);
        btns = findViewById(R.id.btns);
        btns.setOnClickListener(this);
        btnf = findViewById(R.id.btnf);
        btnf.setOnClickListener(this);
        btnaniid = findViewById(R.id.btnaniid);
        btnaniid.setOnClickListener(this);
        btng = findViewById(R.id.btng);
        btng.setOnClickListener(this);
        Intent intent = getIntent();
    }

    public void onClick(View v) {
        if (v==btnout){
            firebaseAuth.signOut();
            Intent i = new Intent(this,MainActivity.class);
            startActivity(i);
            }
        if (v == btnpro) {
            Intent intent = new Intent(this, ProfileActivity.class);
            startActivity(intent);
        }
        if (v == btnor) {
            Intent intent = new Intent(this, OrnithologyActivity.class);
            startActivity(intent);
        }
        if (v == btv) {
            Intent intent = new Intent(this, VolunteeringActivity.class);
            startActivity(intent);
        }
        if (v == btns) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }
        if (v == btnf) {
            Intent intent = new Intent(this, FirstaidActivity.class);
            startActivity(intent);
        }
        if (v == btnaniid) {
            Intent intent = new Intent(this, AnimalidActivity.class);
            startActivity(intent);
        }
        if (v == btng) {
            Intent intent = new Intent(this, GalleryActivity.class);
            startActivity(intent);
        }
    }
}