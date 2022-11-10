package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ExpenseActivity extends AppCompatActivity {
    FloatingActionButton add_expense;
    String id_trip;
    RecyclerView recyclerView;
    MyDatabaseHelper myDatabaseHelper;
    ArrayList<String> expense_id, expense_type, expense_amount, expense_date;
    ExpenseAdapter expenseAdapter;
    ImageView empty_image;
    TextView no_data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);
        recyclerView = findViewById(R.id.recyclerViewExpense);
        add_expense = findViewById(R.id.add_expense_trip);
        id_trip = getIntent().getStringExtra("id_trip");
        empty_image = findViewById(R.id.empty_expense);
        no_data = findViewById(R.id.no_data_expense);
        add_expense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExpenseActivity.this, AddExpense.class);
                intent.putExtra("get_id", id_trip);
                startActivity(intent);
            }
        });
        myDatabaseHelper = new MyDatabaseHelper(ExpenseActivity.this);
        expense_id = new ArrayList<>();
        expense_type = new ArrayList<>();
        expense_amount = new ArrayList<>();
        expense_date = new ArrayList<>();
        DisplayExpense();
        expenseAdapter = new ExpenseAdapter(ExpenseActivity.this, expense_id,expense_type,expense_amount,expense_date);
        recyclerView.setAdapter(expenseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ExpenseActivity.this));
    }
    void DisplayExpense(){
        Cursor cursor = myDatabaseHelper.readExpenseData();
        if(cursor.getCount() == 0){
            empty_image.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        }else{
            while (cursor.moveToNext()){
                expense_id.add(cursor.getString(0));
                expense_date.add(cursor.getString(1));
                expense_type.add(cursor.getString(2));
                expense_amount.add(cursor.getString(3));
            }
            empty_image.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
        }
    }
}