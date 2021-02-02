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

public class GalleryActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {

    private ImageButton atg;
    private Button go_back_fg;
    private LinearLayout linearLayout;
    private ImageView ivgallery;
    private InfoImage im;
    private static int REQUEST_STORAGE = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery);

        atg = findViewById(R.id.atg);
        atg.setOnClickListener(this);
        ivgallery = findViewById(R.id.ivgallery);
        linearLayout = findViewById(R.id.gallery);
        go_back_fg = findViewById(R.id.go_back_fg);
        go_back_fg.setOnClickListener(this);
        getAllfiles();
    }

    public void getAllfiles() {
        linearLayout.removeAllViews();
        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/images" );

        if (myDir.isDirectory()){
            String[] dirList = myDir.list();
            for (int i=0;i<dirList.length;i++){
                File file = new File(myDir,dirList[i]);
                Bitmap bit = BitmapFactory.decodeFile(file.getAbsolutePath());
                ImageButton ib = new ImageButton(this);
                InfoImage infoImage = new InfoImage(file,bit);
                ib.setTag(infoImage);
                ib.setOnLongClickListener(this);
                ib.setOnLongClickListener(this);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(200,200);
                ib.setLayoutParams(layoutParams);
                ib.setImageBitmap(bit);
                linearLayout.addView(ib);

            }

        }
    }

    @Override
    public void onClick(View v) {
        if(v==atg){
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(Intent.createChooser(intent,"Select Picture"),0);
        }
        else {
            im = (InfoImage)v.getTag();
            ivgallery.setImageBitmap(im.getBitmap());
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == Activity.RESULT_OK){
            if (data != null){
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(GalleryActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_STORAGE);
                }
                else{
                    saveImageToExternalStorage(bitmap);
                    ivgallery.setImageBitmap(bitmap);
                }
            }
        }
    }

    private void saveImageToExternalStorage(Bitmap bitmap) {
        String root = Environment.getExternalStorageDirectory().toString();
        Toast.makeText(this,root,Toast.LENGTH_SHORT).show();
        File myDIR = new File(root + "/images");
        myDIR.mkdirs();
        Random r = new Random();
        int n = r.nextInt(1000);
        String name = "Image-" + n + ".jpg";
        File file = new File(myDIR, name);
        boolean flag = true;

        if(file.exists()){
            while (flag){
                int temp = r.nextInt(1000);
                name = "Image-" + temp + ".jpg";
                file = new File(myDIR, name);
                if (!file.exists()){
                    flag = false;
                }
            }
        }
        try {
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG,100, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            {
                getAllfiles();
            }
        }
    }

    @Override
    public boolean onLongClick(View v) {
       im = (InfoImage)v.getTag();
       creatDialog();
       return true;
    }

    private void creatDialog() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Êtes-vous sûr de vouloir supprimer cette image?");
        builder1.setCancelable(false);
        builder1.setPositiveButton("Oui",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        deleteFromExternalStorage(im.getFile());
                        dialog.dismiss();
                    }
                });
        builder1.setNegativeButton("Non",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert2 = builder1.create();
        alert2.show();
    }

    public void deleteFromExternalStorage(File file) {
        try {
            if (file.exists()) {
                if (file.delete()) {
                    Toast.makeText(this, "file" + file.getAbsolutePath() + "deleted",Toast.LENGTH_SHORT);
                }
            }
            getAllfiles();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}