package com.example.comeandsee;

import androidx.annotation.NonNull;
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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthSettings;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

public class UserProfileActivity extends AppCompatActivity {
  private   Button btn_plus, add_picture;
  private EditText email, create_username, create_password, verify_password;
  private ImageView iv_profile;
  private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        add_picture = findViewById(R.id.add_picture);
        email = findViewById(R.id.email);
        btn_plus = findViewById(R.id.btn_plus);
        create_username = findViewById(R.id.create_username);
        create_password = findViewById(R.id.create_password);
        verify_password = findViewById(R.id.verify_password);
        iv_profile = findViewById(R.id.iv_profile);
        firebaseAuth = FirebaseAuth.getInstance();
        add_picture.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, 1);
                } else {
                    Toast.makeText(UserProfileActivity.this, "There's no app that support this action", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(create_password.getText().toString().equals(verify_password.getText().toString()))){
                    create_password.setText("");
                    verify_password.setText("");
                    Toast.makeText(UserProfileActivity.this, "try again", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(UserProfileActivity.this, HomePageActivity.class);
                firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(),create_password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            startActivity(intent);
                        }
                    }
                });

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            Bundle bundle = data.getExtras();
            Bitmap finalPhoto = (Bitmap) bundle.get("data");
            iv_profile.setImageBitmap(finalPhoto);
        }
    }

}




