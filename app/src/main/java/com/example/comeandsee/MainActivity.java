package com.example.comeandsee;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btncr;
    private Button btnin;
    private EditText userName, Password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnin = findViewById(R.id.btnin);
        btncr = findViewById(R.id.btncr);
        userName = findViewById(R.id.userName);
        Password = findViewById(R.id.Password);
        btncr.setOnClickListener(this);
        btnin.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v == btncr) {
            Intent intent = new Intent(this, UserProfileActivity.class);
            startActivity(intent);
        }
        if (v == btnin) {
            Intent intent = new Intent(this, HomePageActivity.class);
            intent.putExtra("userName", userName.getText().toString());
            intent.putExtra("Password", Password.getText().toString());
            startActivity(intent);
        }
    }
}





