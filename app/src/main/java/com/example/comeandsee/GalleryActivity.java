package com.example.comeandsee;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class GalleryActivity extends AppCompatActivity {

    private ImageButton add_to_gallery;
    private ImageView iv_gallery;
    private LinearLayout lhs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery);

        add_to_gallery = findViewById(R.id.add_to_gallery);
        iv_gallery = findViewById(R.id.iv_gallery);
        lhs = findViewById(R.id.lhs);
        add_to_gallery.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (intent.resolveActivity(getPackageManager()) != null){
                    startActivityForResult(intent,1);
                }else {
                    Toast.makeText(GalleryActivity.this,"There's no app that support this action",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK && data != null){
            Bundle bundle = data.getExtras();
            Bitmap finalPhoto = (Bitmap) bundle.get("data");
            iv_gallery.setImageBitmap(finalPhoto);
        }
    }
}