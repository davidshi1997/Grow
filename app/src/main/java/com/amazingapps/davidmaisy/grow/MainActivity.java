package com.amazingapps.davidmaisy.grow;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences settings = getSharedPreferences("settings", MODE_PRIVATE);
        if (!settings.contains("weight")) {
            try {
                Intent k = new Intent(MainActivity.this, IntroActivity.class);
                startActivity(k);
                finish();
            } catch (Exception e) {
            }
        }

        setContentView(R.layout.activity_main);
    }

    // TODO: Should check the time to see if plant should be dead
    @Override
    protected void onResume() {
        super.onResume();
    }

    // TODO: Should save current waterlevel and seedtype before destruction
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
