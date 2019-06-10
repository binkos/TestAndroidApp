package com.example.databaseapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {

    DataBaseHelper dataBaseHelper;
    SQLiteDatabase db;
    Button addButton;
    ListView listView;
    Cursor cursor;
    SimpleCursorAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addButton = findViewById(R.id.addButton);
        listView = findViewById(R.id.list);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(getApplicationContext(),UserActivity.class);
            intent.putExtra("id",id);
            startActivity(intent);
        });
        dataBaseHelper = new DataBaseHelper(this);
    }

    @Override
    public void onResume() {
        super.onResume();

        db = dataBaseHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM " +DataBaseHelper.DATABASE,null);
    }

    public void add(View view){
        Intent intent = new Intent(getApplicationContext(),UserActivity.class);
        startActivity(intent);
    }

}

