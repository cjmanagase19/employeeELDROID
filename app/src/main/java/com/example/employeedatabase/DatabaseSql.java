package com.example.employeedatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.EditText;

import androidx.annotation.Nullable;

public class DatabaseSql extends SQLiteOpenHelper {

    public DatabaseSql(Context context) {
        super(context, "employee1.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqlDB)
    {
        sqlDB.execSQL("create Table employees(empID TEXT primary key, empName TEXT,empPosition TEXT,empAddress TEXT,empGender TEXT)");
        Log.d("DATABASE","created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqlDB, int u, int u1)
    {
        Log.d("DATABASE","deleted");
        sqlDB.execSQL("drop Table if exists employees");
    }
    public void drop()
    {
        SQLiteDatabase sqlDb = this.getWritableDatabase();
        Cursor cursor = sqlDb.rawQuery("Select * from employees", null);
        sqlDb.delete("employees",null,null);


    }
    public Cursor getdata()
    {
        SQLiteDatabase sqlDb = this.getWritableDatabase();
        Cursor cursor = sqlDb.rawQuery("Select * from employees", null);
        return cursor;
    }
    /*RetrieveId*/
    public Cursor retrieve(String empID) {
        String id = empID;
        SQLiteDatabase sqlDb = this.getWritableDatabase();
        Cursor cursor = sqlDb.rawQuery("Select * from employees where empID ='" + id + "'", null);
        return cursor;
    }

    public boolean create(String empID, String empName,String empPosition, String empAddress, String empGender)
    {

        SQLiteDatabase sqlDb = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("empID", empID);
        values.put("empName",empName);
        values.put("empPosition",empPosition);
        values.put("empAddress",empAddress);
        values.put("empGender",empGender);
        long result = sqlDb.insert("employees", null, values);

        if (result == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    public boolean update(String empID, String empName,String empPosition, String empAddress, String empGender)
    {
        SQLiteDatabase sqlDb = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("empName", empName);
        values.put("empPosition", empPosition);
        values.put("empAddress", empAddress);
        values.put("empGender", empGender);
        Cursor cursor = sqlDb.rawQuery("Select * from employees where empID = ?", new String[]{
                empID});
        if (cursor.getCount() > 0)
        {
            long result = sqlDb.update("employees", values, "empID=?", new String[]{
                    empID
            });

            if (result == -1)
            {
                return false;
            }
            else
            {
                return true;
            }
        }
        else
        {
            return false;
        }
    }
    public boolean delete(String empID) {
        SQLiteDatabase sqlDb = this.getWritableDatabase();
        Cursor cursor = sqlDb.rawQuery("Select * from employees where empID = ?", new String[]{empID});
        if (cursor.getCount() > 0)
        {
            long result = sqlDb.delete("employees", "empID=?", new String[]{empID});
            if (result == -1)
            {
                return false;
            }
            else
            {
                return true;
            }
        }
        else
        {
            return false;
        }
    }
}




