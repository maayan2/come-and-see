package com.example.comeandsee;

import android.graphics.Bitmap;

import java.io.File;

public class InfoImage {
    private File file;
    private Bitmap bitmap;

    public InfoImage(File file, Bitmap bitmap){
        this.file = file;
        this.bitmap = bitmap;
    }

    public File getFile(){
        return file;
    }

    public void setFile(File file){
        this.file = file;
    }

    public Bitmap getBitmap(){
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap){
        this.bitmap = bitmap;
    }
    }



