package com.example.fyp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import io.alterac.blurkit.BlurLayout;

public class Splash extends AppCompatActivity {

    BlurLayout blurLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        blurLayout = findViewById(R.id.blurLayout);

        //Splash
        Thread thread = new Thread() {
            public void run() {
                try {
                    sleep(2000);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {

                    Intent intent = new Intent(Splash.this, Login.class);
                    startActivity(intent);
                }
            }
        };thread.start();

    }
    @Override
    public void onBackPressed(){
        // super.onBackPressed();
    }
}