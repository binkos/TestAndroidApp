package com.example.databaseapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class PeopleListAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private LayoutInflater layoutInflater;
    private ArrayList<People> peopleList;

    PeopleListAdapter(Context context, int layout, ArrayList<People> peopleList) {
        this.layout = layout;
        this.peopleList = peopleList;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
       return peopleList.size();
    }

    @Override
    public Object getItem(int position) {
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
            convertView = layoutInflater.inflate(this.layout,null,false);

            viewHolder.imageView = convertView.findViewById(R.id.ivImg);
            viewHolder.txtName = convertView.findViewById(R.id.nameField);
            viewHolder.txtYear = convertView.findViewById(R.id.yearField);
            convertView.setTag(viewHolder);

        }
        else viewHolder = (ViewHolder)convertView.getTag();

        People people = peopleList.get(position);

        viewHolder.txtName.setText(people.getName());
        viewHolder.txtYear.setText(people.getYear());

        byte[] bytes = people.getImg();
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
        viewHolder.imageView.setImageBitmap(bitmap);

        return convertView;
    }
}
