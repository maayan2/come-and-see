package com.example.comeandsee;

import android.graphics.Bitmap;
import android.widget.EditText;

public class Profile_data {
    private String username;
    private String password;
    private String vpassword;
    private String email;
    private Bitmap picture;


    public Bitmap getPicture() {
        return picture;
    }

    public Profile_data(String username, String password, String vpassword, String email, Bitmap picture)
 {
    this.username = username;
    this.password = password;
    this.vpassword = vpassword;
    this.email = email;
    this.picture = picture;
 }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVpassword() {
        return vpassword;
    }

    public void setVpassword(String vpassword) {
        this.vpassword = vpassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}