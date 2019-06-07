package com.example.databaseapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import java.io.ByteArrayOutputStream;
import java.io.IOException;


public class UserActivity extends AppCompatActivity {

    DataBaseHelper dataBaseHelper;
    Cursor cursor;
    long userId = 0;
    SQLiteDatabase db;
    EditText etName;
    EditText etYear;
    Button delButton;
    ImageView ivIMG;
    ByteArrayOutputStream stream;
    byte[] byteArray;
    Bundle bundle;
    Bitmap cameraPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        delButton = findViewById(R.id.delete);
        etName = findViewById(R.id.nameField);
        etYear = findViewById(R.id.yearField);
        ivIMG = findViewById(R.id.ivImg);

        dataBaseHelper = new DataBaseHelper(this);
        db = dataBaseHelper.getWritableDatabase();

        Bundle extras = getIntent().getExtras();
        if (extras!=null) userId = extras.getLong("id");

        if (userId>0){
        cursor = db.rawQuery("SELECT * FROM " +DataBaseHelper.DATABASE + " WHERE "+ DataBaseHelper.COLUMN_ID+" ="+userId,null);
        cursor.moveToFirst();

        etName.setText(cursor.getString(1));
        etYear.setText(String.valueOf(cursor.getInt(2)));
     //   ivIMG.setImageBitmap(BitmapFactory.decodeByteArray(cursor.getBlob(3),0,cursor.getBlob(3).length));

        cursor.close();
        }
        else {
            delButton.setVisibility(View.GONE);
        }

        ivIMG.setOnClickListener(v -> {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent,1);
        });

    }

    public void addBut(View view){
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",etName.getText().toString());
        contentValues.put("year",etYear.getText().toString());
        contentValues.put("image",byteArray);

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
        bundle.clear();
        cameraPicture.recycle();
        try {
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        startActivity(new Intent(this,MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP));
    }

    @Override
    public void onActivityResult(int reqCode,int resultCode, Intent data){
        if(data==null){return;}
        if (resultCode==RESULT_OK&&reqCode==1) {

                bundle = data.getExtras();
                
                cameraPicture = (Bitmap) bundle.get("data");
                stream = new ByteArrayOutputStream();
                cameraPicture.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byteArray = stream.toByteArray();
                ivIMG.setImageBitmap(cameraPicture);


        }
    }
}
