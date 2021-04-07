package com.example.comeandsee;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthSettings;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

public class UserProfileActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_plus, add_picture;
    private DatabaseReference databaseReference;
    private EditText email, create_username, create_password, verify_password;
    private ImageView iv_profile;
    private FirebaseDatabase firebaseDatabase;
    private Dialog dialog;
    private TextView tvReg;
    private StorageReference storagereference;
    private FirebaseAuth firebaseAuth;
    private Profile_data user;
    private Uri imageUri;
    private String imageString;
    private ProgressBar pb;
    private  Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser()!=null){
            Intent intent = new Intent(this,HomePageActivity.class);
            startActivity(intent);
        }
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Users");
        storagereference = FirebaseStorage.getInstance().getReference("Images");
        btn_plus = findViewById(R.id.btn_plus);
        add_picture = findViewById(R.id.add_picture);
        add_picture.setOnClickListener(this);
        btn_plus.setOnClickListener(this);
        dialog = new Dialog(this);
        email = findViewById(R.id.email);
        create_username = findViewById(R.id.create_username);
        create_password = findViewById(R.id.create_password);
        verify_password = findViewById(R.id.verify_password);
        iv_profile = findViewById(R.id.iv_profile);
        pb = findViewById(R.id.pb);
        if(checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{
                    Manifest.permission.CAMERA}, 100);
        }
        if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE}, 200);
        }
    }

    @Override
    public void onClick(View v) {
        if(v==add_picture){
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivityForResult(intent, 1);
                    } else {
                        Toast.makeText(UserProfileActivity.this, "There's no app that support this action", Toast.LENGTH_SHORT).show();
                    }
                }
        if (v==btn_plus){
           register();
        }
    }

    public void register(){
        String Email = email.getText().toString().trim();
        String password = create_password.getText().toString().trim();
        String vPassword= verify_password.getText().toString().trim();

        if (!password.equals(vPassword)){
            tvReg.setText("The passwords don't match. Try again.");
            Snackbar.make(findViewById(android.R.id.content),"The passwords don't match. Try again.",Snackbar.LENGTH_SHORT).show();
            create_password.setText("");
            verify_password.setText("");
            return;
        }
        if(password.length() < 5){
            tvReg.setText("Password must contains at least 5 characters");
            create_password.setText("");
            verify_password.setText("");
        }
        firebaseAuth.createUserWithEmailAndPassword(Email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    imageUri = getImageUri();
                    if (imageUri == null){
                        Toast.makeText(UserProfileActivity.this,"is null",Toast.LENGTH_SHORT).show();
                    }
                        user = new Profile_data(create_username.getText().toString(),
                            create_password.getText().toString(), verify_password.getText().toString(),email.getText().toString(),imageUri.toString());
                    databaseReference.child(firebaseAuth.getCurrentUser().getUid()).setValue(user);
                    Intent intent = new Intent(UserProfileActivity.this, HomePageActivity.class);
                    upload();
                    startActivity(intent);
                    finish();
                }else {
                    tvReg.setText("Something went wrong, pleas try again");
                    Snackbar.make(findViewById(android.R.id.content),"Something went wrong, pleas try again",Snackbar.LENGTH_SHORT).show();
                    return;
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            bitmap = (Bitmap)data.getExtras().get("data");
            iv_profile.setImageURI(getImageUri());

        }
    }
    public Uri getImageUri(){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);
        String s = MediaStore.Images.Media.insertImage(getApplicationContext().getContentResolver(), bitmap,"title",null);
        return Uri.parse(s);
    }
    public void upload() {
        pb.setVisibility(View.VISIBLE);
        if (imageUri == null){
            Toast.makeText(this,"is null",Toast.LENGTH_SHORT).show();
        }
        String key = System.currentTimeMillis() +"."+ getFileExtension(getImageUri());
        storagereference.child(key).putFile(getImageUri())
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        Log.println(Log.DEBUG, "TAG","onSuccess");
                        Handler h = new Handler();
                        h.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                pb.setProgress(0);
                            }
                        },1000);
                        imageString = taskSnapshot.getUploadSessionUri().toString();
                        user.setImageUri(imageString);
                        databaseReference.child(firebaseAuth.getCurrentUser().getUid()).setValue(user);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                double progress = (100.0*taskSnapshot.getBytesTransferred())/taskSnapshot.getTotalByteCount();
                pb.setProgress((int)progress);
            }
        });
    }

    public String getFileExtension(Uri uri) {
        String cr = getContentResolver().getType(uri);
        MimeTypeMap mtm = MimeTypeMap.getSingleton();
        return mtm.getExtensionFromMimeType(cr);
    }
}





