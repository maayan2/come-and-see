package com.example.comeandsee;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

public class UserProfileActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnplus;
    private EditText email, cruserName, crpass, conpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        btnplus = findViewById(R.id.btnplus);
        email = findViewById(R.id.email);
        cruserName = findViewById(R.id.cruserName);
        crpass = findViewById(R.id.crpass);
        conpass = findViewById(R.id.conpass);
        btnplus.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == btnplus) {
            Intent intent = new Intent(this, HomePageActivity.class);
            intent.putExtra("email", email.getText().toString());
            intent.putExtra("cruserName", cruserName.getText().toString());
            intent.putExtra("crpass", crpass.getText().toString());
            intent.putExtra("conpass", conpass.getText().toString());
            startActivity(intent);
        }


    }
}
