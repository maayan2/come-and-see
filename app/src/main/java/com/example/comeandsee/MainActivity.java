package com.example.comeandsee;

import androidx.annotation.NonNull;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btncr;
    private Button btnin;
    private EditText userName, password;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnin = findViewById(R.id.btnin);
        btncr = findViewById(R.id.btncr);
        userName = findViewById(R.id.userName);
        password = findViewById(R.id.Password);
        btncr.setOnClickListener(this);
        btnin.setOnClickListener(this);
        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser()!=null){
            Intent intent = new Intent(this, HomePageActivity.class);
            startActivity(intent);
        }
    }


    @Override
    public void onClick(View v) {
        if (v == btncr) {
            Intent intent = new Intent(this, UserProfileActivity.class);
            startActivity(intent);
        }
        if (v == btnin) {
            Intent intent = new Intent(this, HomePageActivity.class);
            firebaseAuth.signInWithEmailAndPassword(userName.getText().toString(),password.getText().toString() ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        startActivity(intent);
                    }
                }
            });
        }
    }
}





