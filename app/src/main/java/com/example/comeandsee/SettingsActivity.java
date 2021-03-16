package com.example.comeandsee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class SettingsActivity extends AppCompatActivity {
    private Button camera;
    private Button contacts;
    private Button storage;
    private Button all_permissions;
    private Button btn_go_back;
    private Switch switch_dark_mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        camera = findViewById(R.id.camera);
        contacts = findViewById(R.id.contacts);
        storage = findViewById(R.id.storage);
        all_permissions = findViewById(R.id.all_permissions);
        btn_go_back = findViewById(R.id.btn_go_back);
        switch_dark_mode = findViewById(R.id.switch_dark_mode);


    }
}
