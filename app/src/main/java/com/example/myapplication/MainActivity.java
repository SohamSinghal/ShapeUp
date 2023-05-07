package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    GridView gw;
    ArrayList<String> image_urls;
    ArrayList<String> names;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int splash_screen_timeout = 5000;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gw = (GridView) findViewById(R.id.GridView);
        getSupportActionBar().setTitle("Workout App");
        DBAdapter dbAdapter = new DBAdapter(this);
        try{
        dbAdapter.CheckDB();}
        catch (Exception e){
            e.printStackTrace();
        }
        names = dbAdapter.get_names_data("muscles");
        image_urls = dbAdapter.get_image_urls("muscles");

        CustomAdapter ca = new CustomAdapter(R.layout.grid_item,names,image_urls,this);
        gw.setAdapter(ca);

        gw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = names.get(position);
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                intent.putExtra("key",name);
                startActivity(intent);
            }
        });
    }
}