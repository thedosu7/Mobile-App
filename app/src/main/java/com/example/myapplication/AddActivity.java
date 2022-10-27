package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {
    EditText trip_input, destination_input, require_input, date_input, description_input;
    Button add_trip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        trip_input = findViewById(R.id.name_of_trip);
        destination_input = findViewById(R.id.destination);
        require_input = findViewById(R.id.require);
        date_input = findViewById(R.id.date_of_the_trip);
        description_input = findViewById(R.id.description);
        add_trip = findViewById(R.id.add_trip);
        add_trip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(AddActivity.this);
                myDatabaseHelper.addTrip(trip_input.getText().toString().trim(),
                        destination_input.getText().toString().trim(),
                        require_input.getText().toString().trim(),
                        date_input.getText().toString().trim(),
                        description_input.getText().toString().trim());
            }
        });




    }
}