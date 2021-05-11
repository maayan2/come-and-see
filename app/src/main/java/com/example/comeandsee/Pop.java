package com.example.comeandsee;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Pop extends Activity {
    private CheckBox c;
    private EditText et;
    private EditText area;
    private EditText name;
    private EditText d;
    private Button btnc;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recommend_layout);
        c = findViewById(R.id.cb);
        et = findViewById(R.id.et);
        area = findViewById(R.id.area);
        name = findViewById(R.id.name);
        d = findViewById(R.id.d);
        btnc = findViewById(R.id.btnc);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Recommend");
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*0.8),(int)(height*0.6));
        Recommend r = new Recommend(c.isSelected(),et.getText().toString(),area.getText().toString(),name.getText().toString(),d.getText().toString());
        databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(r);
    }
}
