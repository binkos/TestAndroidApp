package com.example.databaseapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {
    static final String TABLE_NAME = "database.db";
    static final String DATABASE = "users";
    static final int SCHEME = 1;
    static final String COLUMN_ID ="_id";
    static final String COLUMN_NAME = "name";
    static final String COLUMN_YEAR = "year";
    static final String COLUMN_IMAGE = "image";

    public DataBaseHelper(Context context) {
        super(context, TABLE_NAME, null, SCHEME);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS "+ DATABASE + " ("+COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +COLUMN_NAME+" TEXT,"+COLUMN_YEAR+" INTEGER,"+ COLUMN_IMAGE+" BLOB);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+DATABASE);
        db.close();
    }
}
