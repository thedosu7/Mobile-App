package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.Date;

     class MyDatabaseHelper extends SQLiteOpenHelper {
     private Context context;
     MyDatabaseHelper(@Nullable Context context) {
        super(context, "ExpenseApp.db", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE ExpenseApp (ID INTEGER PRIMARY KEY AUTOINCREMENT ," +
                "name TEXT, destination TEXT, date TEXT, require TEXT, description TEXT)");
        db.execSQL("CREATE TABLE ExpenseTable (ID1 INTEGER PRIMARY KEY AUTOINCREMENT ," +
                "type TEXT, amount TEXT, date TEXT, trip_id INTEGER, FOREIGN KEY (trip_id) REFERENCES ExpenseApp (ID))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS ExpenseApp");
        db.execSQL("DROP TABLE IF EXISTS ExpenseTable");
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



    void updateData(String row_id, String name,String  destination, String date, String require, String description  ){
         SQLiteDatabase database = this.getWritableDatabase();
         ContentValues contentValues = new ContentValues();
         contentValues.put("name",name);
         contentValues.put("destination",destination);
         contentValues.put("date",date);
         contentValues.put("require",require);
         contentValues.put("description",description);

         long result = database.update("ExpenseApp",contentValues,"ID=?",new String[]{row_id});
         if(result == -1){
             Toast.makeText(context,"Update failed",Toast.LENGTH_SHORT).show();
         }else {
             Toast.makeText(context,"Update success",Toast.LENGTH_SHORT).show();
         }
    }
    void deleteData(String row_id){
         SQLiteDatabase database = this.getWritableDatabase();
         long result = database.delete("ExpenseApp","ID=?",new String[]{row_id});
        if(result == -1){
            Toast.makeText(context,"Delete failed",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context,"Delete success",Toast.LENGTH_SHORT).show();
        }
    }
    void deleteAllData(){
         SQLiteDatabase database = this.getWritableDatabase();
         database.execSQL("DELETE FROM  ExpenseApp");
    }

     void addExpense(String trip_id, String type, String amount, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("trip_id",trip_id);
        cv.put("type",type);
        cv.put("amount",amount);
        cv.put("date",date);

        long result = db.insert("ExpenseTable",null,cv);
        if(result == -1){
            Toast.makeText(context,"Failed", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(context,"Create success", Toast.LENGTH_LONG).show();
        }
     }
         Cursor readExpenseData(){
             String query1 = "SELECT * FROM ExpenseTable";
             SQLiteDatabase database = this.getWritableDatabase();

             Cursor cursor = null;
             if(database!=null){
                 cursor = database.rawQuery(query1, null);
             }
             return cursor;
         }
     }
