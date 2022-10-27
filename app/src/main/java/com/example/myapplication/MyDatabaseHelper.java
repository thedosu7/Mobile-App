package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.Date;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    public MyDatabaseHelper(@Nullable Context context) {
        super(context, "ExpenseApp.db", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE ExpenseApp (ID INTEGER PRIMARY KEY AUTOINCREMENT ," +
                "name TEXT, destination TEXT, date TEXT, require TEXT, description TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS ExpenseApp");
        onCreate(db);
    }

    void addTrip(String name, String destination, String require, String date, String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("name", name);
        cv.put("destination", destination);
        cv.put("date", date);
        cv.put("require", require);
        cv.put("description", description);

        long rs = db.insert("ExpenseApp", null, cv);
        if (rs == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
        }


    }

    Cursor readData(){
        String query = "SELECT * FROM  ExpenseApp";
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor c = null;
        if(db!=null){
           c = db.rawQuery(query, null);
        }
        return c;


    }

}
