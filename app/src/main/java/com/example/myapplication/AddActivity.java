package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

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
        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        date_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog dialog = new DatePickerDialog(AddActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;
                        String date = day+"/"+month+"/"+year;
                        date_input.setText(date);
                    }
                },year,month,day);
                dialog.show();
            }
        });
        add_trip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(trip_input.getText().toString().trim())){
                    trip_input.setError("You must fill the trip name");
                    return;
                }

                if(TextUtils.isEmpty(destination_input.getText().toString().trim())){
                    destination_input.setError("You must fill the destination");
                    return;
                }
                if(TextUtils.isEmpty(require_input.getText().toString().trim())){
                    require_input.setError("You must fill the require accessed");
                    return;
                }
                if(TextUtils.isEmpty(date_input.getText().toString().trim())){
                    date_input.setError("You must fill the date");
                    return;
                }
                if(TextUtils.isEmpty(description_input.getText().toString().trim())){
                    description_input.setError("You must fill the description");
                    return;
                }
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