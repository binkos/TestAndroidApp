package com.example.databaseapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;

public class UserActivity extends AppCompatActivity {

    DataBaseHelper dataBaseHelper;
    Cursor cursor;
    long userId = 0;
    SQLiteDatabase db;
    EditText etName;
    EditText etYear;
    Button delButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        delButton = findViewById(R.id.delete);
        etName = findViewById(R.id.nameField);
        etYear = findViewById(R.id.yearField);

        dataBaseHelper = new DataBaseHelper(this);
        db = dataBaseHelper.getWritableDatabase();

        Bundle extras = getIntent().getExtras();
        if (extras!=null) userId = extras.getLong("id");

        if (userId>0){
        cursor = db.rawQuery("SELECT * FROM " +DataBaseHelper.DATABASE + " WHERE "+ DataBaseHelper.COLUMN_ID+" ="+userId,null);
        cursor.moveToFirst();

        etName.setText(cursor.getString(1));
        etYear.setText(String.valueOf(cursor.getInt(2)));

        cursor.close();
        }
        else {
            delButton.setVisibility(View.GONE);
        }
    }

    public void addBut(View view){
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",etName.getText().toString());
        contentValues.put("year",etYear.getText().toString());


        if (userId>0){
            db.update(DataBaseHelper.DATABASE,contentValues,DataBaseHelper.COLUMN_ID+"=?",new String[]{String.valueOf(userId)});
        }
        else {
            db.insert(DataBaseHelper.DATABASE,null,contentValues);
        }
        goHome();
    }

    public void deleteBut(View view){
        db.delete(DataBaseHelper.DATABASE,DataBaseHelper.COLUMN_ID+"=?",new String[]{String.valueOf(userId)});
        goHome();
    }

    public void goHome(){
        db.close();
        startActivity(new Intent(this,MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP));
    }


}
