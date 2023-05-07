package com.example.myapplication;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    int item;
    ArrayList<String> image_name;
    ArrayList<String> image_pic;
    Context context;
    LayoutInflater layoutInflater;

    public CustomAdapter(int item,ArrayList<String> image_name, ArrayList<String> image_pic, Context context) {
        this.image_name = image_name;
        this.image_pic = image_pic;
        this.context = context;
        this.item = item;
        this.layoutInflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return image_name.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = layoutInflater.inflate(item, parent,false);
        }
        TextView tvname = convertView.findViewById(R.id.name);
        ImageView imageView = convertView.findViewById(R.id.image);
        tvname.setText(image_name.get(position).replace("_"," "));
        Glide.with(context).load(image_pic.get(position)).into(imageView);
        return convertView;
    }
}