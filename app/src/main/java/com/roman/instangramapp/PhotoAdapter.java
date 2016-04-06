package com.roman.instangramapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

class PhotoAdapter extends BaseAdapter {

    ArrayList<PhotoObject> list;
    Context context;

    PhotoAdapter(Context c, ArrayList<PhotoObject> photoObject){
        this.context = c;
        this.list = photoObject;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.single_photo, parent, false);

        TextView userName = (TextView) row.findViewById(R.id.textView);
        ImageView img = (ImageView) row.findViewById(R.id.imageView);

        PhotoObject theObject = list.get(position);
        userName.setText(theObject.userName);
        img.setImageBitmap(theObject.thumbnail);

        return row;
    }
}