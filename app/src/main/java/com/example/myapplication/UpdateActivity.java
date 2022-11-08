package com.example.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Locale;

public class UpdateActivity extends AppCompatActivity {
    EditText trip_input, destination_input, require_input, date_input, description_input;
    Button update_button, delete_button, allExpenses_button;
    String id,name,destination,require, date, description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        trip_input = findViewById(R.id.name_of_trip_update);
        destination_input = findViewById(R.id.destination_update);
        require_input = findViewById(R.id.require_update);
        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        date_input = findViewById(R.id.date_of_the_trip_update);
        date_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog dialog = new DatePickerDialog(UpdateActivity.this, new DatePickerDialog.OnDateSetListener() {
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
        description_input = findViewById(R.id.description_update);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);
        allExpenses_button = findViewById(R.id.seeAllExpense);
        allExpenses_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpdateActivity.this,ExpenseActivity.class);
                intent.putExtra("id_trip",id);
                startActivity(intent);
            }
        });


        getIntentData();

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(name);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
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
                MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(UpdateActivity.this);
                   name = trip_input.getText().toString().trim();
                   destination = destination_input.getText().toString().trim();
                   require = require_input.getText().toString().trim();
                   date = date_input.getText().toString().trim();
                   description = description_input.getText().toString().trim();
                myDatabaseHelper.updateData(id,name,destination,date,require,description);
            }
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });


    }

    void getIntentData() {
        if(getIntent().hasExtra("id") && getIntent().hasExtra("name")
                && getIntent().hasExtra("destination")
                && getIntent().hasExtra("require")
                && getIntent().hasExtra("date")
                && getIntent().hasExtra("description")){
            //get intent data
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            destination = getIntent().getStringExtra("destination");
            require = getIntent().getStringExtra("require");
            date = getIntent().getStringExtra("date");
            description = getIntent().getStringExtra("description");

            //set intent data
            trip_input.setText(name);
            destination_input.setText(destination);
            require_input.setText(require);
            date_input.setText(date);
            description_input.setText(description);


    }else {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
    }
    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete" + name + "?");
        builder.setMessage("Are you sure you want to delete " + name + "?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(UpdateActivity.this);
                myDatabaseHelper.deleteData(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }
}