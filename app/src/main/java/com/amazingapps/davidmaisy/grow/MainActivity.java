package com.amazingapps.davidmaisy.grow;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {



    // TODO: Should load in saved data, such as coins, seeds, and drinks
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        SharedPreferences settings = getSharedPreferences("settings", MODE_PRIVATE);
//        if (!settings.contains("dailywater")) {
//            try {
//                Intent k = new Intent(MainActivity.this, IntroActivity.class);
//                startActivity(k);
//                finish();
//            } catch (Exception e) {
//            }
//        }

        setContentView(R.layout.activity_main);
    }

    // TODO: Should check the time to see if plant should be dead and kill any background notification service
    @Override
    protected void onResume() {
        super.onResume();

        Date currentTime = Calendar.getInstance().getTime();
    }

    // TODO: Should save current waterlevel and seedtype before destruction as well as starts background notification service
    // https://developer.android.com/guide/components/services.html
    // https://developer.android.com/guide/topics/ui/notifiers/notifications.html
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
