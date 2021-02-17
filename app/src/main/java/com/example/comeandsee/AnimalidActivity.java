package com.example.comeandsee;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AnimalidActivity extends AppCompatActivity implements View.OnClickListener {
    ArrayList<String> arrayList;
    ListView lv;
    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> picked;
    Button btn_list;
    TextView text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animalid);
        btn_list = findViewById(R.id.btn_list);
        text = findViewById(R.id.text);
        btn_list.setOnClickListener(this);
        picked = new ArrayList<String>();
        arrayList = new ArrayList<String>();
        arrayList.add("נחשים");arrayList.add("עקרבאים");arrayList.add("עכבישאים");
        arrayList.add("דגים");arrayList.add("דבוראים");arrayList.add("אחר");
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_checked,arrayList);
        lv = findViewById(R.id.lv);
        lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        lv.setAdapter(arrayAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CheckedTextView textView = (CheckedTextView) view;
                if (textView.isChecked()){
                    picked.add(textView.getText().toString());
                }
                if (!textView.isChecked()&& picked.contains(textView.getText().toString())){
                    picked.remove(textView.getText().toString());
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v==btn_list){
            text.setText("");
            for (String s:picked){
                text.setText(text.getText().toString()+" "+s+" ");
            }
        }
    }
}