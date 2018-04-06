package com.utravel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


public class Splash extends AppCompatActivity {
    final int SPLASH_DELAY = 1000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent formIntent = new Intent(Splash.this, Form.class);
                Splash.this.startActivity(formIntent);
                Splash.this.finish();
            }
        }, SPLASH_DELAY);
    }
}
