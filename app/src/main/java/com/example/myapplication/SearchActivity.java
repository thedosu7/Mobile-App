package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class SearchActivity extends AppCompatActivity {
    EditText search;
    Button search_trip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        search = findViewById(R.id.search_trips);
        search_trip = findViewById(R.id.search);

        search_trip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = search.getText().toString();
                SQLiteDatabase sqLiteDatabase = getApplicationContext().openOrCreateDatabase("ExpenseApp.db", Context.MODE_PRIVATE,null);
                Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM ExpenseApp WHERE name='"+s+"'OR destination='"+s+"'",null);
                if(cursor.getCount()==0){
                    Toast.makeText(getApplicationContext(),"No record",Toast.LENGTH_LONG).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (cursor.moveToNext()){
                    buffer.append("trip id: "+cursor.getString(0)+"\n");
                    buffer.append("trip name: "+cursor.getString(1)+"\n");
                    buffer.append("destination: "+cursor.getString(2)+"\n");
                    buffer.append("date: "+cursor.getString(3)+"\n");
                    buffer.append("require :"+cursor.getString(4)+"\n");
                    buffer.append("description: "+cursor.getString(5)+"\n");

                }
                Toast.makeText(getApplicationContext(),"Result \n"+ buffer.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}