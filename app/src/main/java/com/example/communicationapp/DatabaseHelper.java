package com.example.communicationapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DBNAME = "multi_db";
    private static final String TABLE_NAME = "user";
    private static final String USER_ID = "id";
    private static final String USER_NAME = "name";
    private static final String EMAIL = "email";
    private SQLiteDatabase sqLiteDatabase;


    public DatabaseHelper(@Nullable Context context) {
        super(context, DBNAME, null, 1);
        sqLiteDatabase = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tableCreate = "Create table "+ TABLE_NAME +" " +
                            "("+USER_ID+" integer primary key autoincrement, "+
                            USER_NAME+" text, "+
                            EMAIL+" text)";
        db.execSQL(tableCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String dropTable = "drop table IF EXISTS " + TABLE_NAME;
        db.execSQL(dropTable);
        onCreate(db);
    }

    public long saveUser(){
        String name = "Mg Mg";
        String email = "m@gmail.com";
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_NAME,name);
        contentValues.put(EMAIL, email);

        long user_id = sqLiteDatabase.insertOrThrow(TABLE_NAME,null,contentValues);

        return user_id;
    }
}
