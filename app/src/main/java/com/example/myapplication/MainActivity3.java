package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;



public class MainActivity3 extends AppCompatActivity {
    VideoView vw;
    MediaController mc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String url;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Bundle extras = getIntent().getExtras();
        url = extras.getString("key");
        getSupportActionBar().setTitle(extras.getString("key2"));
        vw = findViewById(R.id.videoView);
        vw.setVideoPath(url);
        mc = new MediaController(this);
        vw.setMediaController(mc);
        vw.requestFocus();
        vw.start();
    }
}