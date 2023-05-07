package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MainActivity2 extends AppCompatActivity {
    ArrayList<String> names;
    ArrayList<String> image_urls;
    ListView lw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String mus_name;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Bundle extras = getIntent().getExtras();
        mus_name = extras.getString("key");
        getSupportActionBar().setTitle(mus_name);
        lw = findViewById(R.id.ListView);
        DBAdapter dbAdapter = new DBAdapter(MainActivity2.this);
        names = dbAdapter.get_names_data(mus_name);
        image_urls = dbAdapter.get_image_urls(mus_name);

        CustomAdapter ca = new CustomAdapter(R.layout.list_item, names, image_urls, this);
        lw.setAdapter(ca);

        lw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = names.get(position);
                ArrayList<String> url = dbAdapter.get_vid_url(mus_name, name);
                String url_string =  url.get(0);
                Intent intent;
                if(!url_string.contains("youtube")) {
                    intent = new Intent(MainActivity2.this, MainActivity3.class);
                }
                else{
                    intent = new Intent(MainActivity2.this, yotube_video.class);
                }
                intent.putExtra("key", url_string);
                intent.putExtra("key2", name);
                startActivity(intent);
            }
            });
        };
    }