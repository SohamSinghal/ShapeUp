package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class HomeActivity extends AppCompatActivity {
    private static int splash_time = 2000;
    Animation animation;
    ImageView home_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);
        home_image = findViewById(R.id.logo_image);
        animation = new AlphaAnimation(1,0);
        animation.setInterpolator(new AccelerateInterpolator());
        animation.setStartOffset(500);
        animation.setDuration(1800);
        home_image.setAnimation(animation);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent home_intent = new Intent(HomeActivity.this,MainActivity.class);
                startActivity(home_intent);
                finish();
            }
        },splash_time);

    }
}