package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_trip;
    MyDatabaseHelper myDatabaseHelper;
    ArrayList<String> trip_id, trip_name, trip_destination, trip_date, trip_require, trip_description;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        add_trip = findViewById(R.id.add_trip);
        add_trip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);

            }
        });
        myDatabaseHelper = new MyDatabaseHelper(MainActivity.this);
        trip_id = new ArrayList<>();
        trip_name = new ArrayList<>();
        trip_destination = new ArrayList<>();
        trip_date = new ArrayList<>();
        trip_require = new ArrayList<>();
        trip_description = new ArrayList<>();

        storeData();
        customAdapter = new CustomAdapter(MainActivity.this, trip_id, trip_name,trip_destination,trip_date,trip_require,trip_description);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    void storeData(){
        Cursor cursor = myDatabaseHelper.readData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No Database", Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
                trip_id.add(cursor.getString(0)); //0 mean first column
                trip_name.add(cursor.getString(1));
                trip_destination.add(cursor.getString(2));
                trip_date.add(cursor.getString(3));
                trip_require.add(cursor.getString(4));
                trip_description.add(cursor.getString(5));
            }
        }
    }
}