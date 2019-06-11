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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DataBaseHelper dataBaseHelper;
    SQLiteDatabase db;
    Button addButton;
    ListView listView;
    Cursor cursor;
    ArrayList<People> list;
    PeopleListAdapter adapter;
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

        list = new ArrayList<>();
        list.clear();
        list.add(new People(0,"Vlad",100,null));

        adapter = new PeopleListAdapter(this,R.layout.list_image_name_year,list);


        db = dataBaseHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM " +DataBaseHelper.DATABASE,null);
        cursor.moveToFirst();

        listView.setAdapter(adapter);

        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            int year = cursor.getInt(2);
            byte[] image = cursor.getBlob(3);

            list.add(new People(id,name,year,image));
        }
        adapter.notifyDataSetChanged();


    }

    public void add(View view){
        Intent intent = new Intent(getApplicationContext(),UserActivity.class);
        startActivity(intent);
    }

}

