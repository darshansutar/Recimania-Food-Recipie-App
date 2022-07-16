package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;

public class SplashScreen extends AppCompatActivity {

    ImageView appName,SplashImage;
    LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        appName= findViewById(R.id.app_name);
        SplashImage = findViewById(R.id.bgimage);
        lottieAnimationView = findViewById(R.id.lottie);
        SplashImage.animate().translationY(-16000).setDuration(1000).setStartDelay(4000);
        appName.animate().translationY(14000).setDuration(1000).setStartDelay(4000);
        lottieAnimationView.animate().translationY(14000).setDuration(1000).setStartDelay(4000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreen.this,MainActivity.class));
                finish();
            }
        },3000);


    }
}