package com.example.databaseapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class PeopleListAdapter extends ArrayAdapter<People> {

    private int layout;
    private LayoutInflater layoutInflater;
    private ArrayList<People> peopleList;

    PeopleListAdapter(Context context, int layout, ArrayList<People> peopleList) {
        super(context,layout,peopleList);
        this.layout = layout;
        this.peopleList = peopleList;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
       return peopleList.size();
    }

    @Override
    public People getItem(int position) {
        return peopleList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
        ImageView imageView;
        TextView txtName,txtYear;

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();

        if (convertView ==null){
            convertView = layoutInflater.inflate(this.layout,parent,false);

            viewHolder.imageView = convertView.findViewById(R.id.ivImg);
            viewHolder.txtName = convertView.findViewById(R.id.nameField);
            viewHolder.txtYear = convertView.findViewById(R.id.yearField);

            convertView.setTag(viewHolder);

        }
        else viewHolder = (ViewHolder)convertView.getTag();

        People people = peopleList.get(position);

        try{viewHolder.txtName.setText(people.getName());}catch (NullPointerException n){n.printStackTrace();}
        try{viewHolder.txtYear.setText(people.getYear());}catch (NullPointerException n){n.printStackTrace();}

        try{byte[] bytes = people.getImg();
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
        viewHolder.imageView.setImageBitmap(bitmap);
        }catch (NullPointerException n){n.printStackTrace();}

        return convertView;
    }
}
