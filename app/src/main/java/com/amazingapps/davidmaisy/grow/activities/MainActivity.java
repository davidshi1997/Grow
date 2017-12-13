package com.amazingapps.davidmaisy.grow.activities;

import android.animation.ObjectAnimator;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.amazingapps.davidmaisy.grow.R;
import com.amazingapps.davidmaisy.grow.plant.Plant;
import com.amazingapps.davidmaisy.grow.utils.AlarmReceiver;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    LinearLayout dropDownMenuIconItem;
    ProgressBar progressBar;
    Context context;

    // TODO: Should load in saved data, such as coins, seeds, and drinks
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = getApplicationContext();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (!preferences.contains("daily_water")) {
            try {
                Intent k = new Intent(MainActivity.this, IntroActivity.class);
                startActivity(k);
                finish();
            } catch (Exception e) {
            }
        }

        setContentView(R.layout.activity_main);

        dropDownMenuIconItem = (LinearLayout) findViewById(R.id.vertical_dropdown_menu_items);

    }

    // TODO: Should check the time to see if plant should be dead and kill any background notification service
    @Override
    protected void onResume() {
        super.onResume();

        Date currentTime = Calendar.getInstance().getTime();

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        progressBar.bringToFront();
        ObjectAnimator animation = ObjectAnimator.ofInt (progressBar, "progress", 0, 500);
        animation.setDuration(5000);
        animation.setInterpolator(new DecelerateInterpolator());
        animation.start();
    }

    // TODO: Should save current waterlevel and seedtype before destruction as well as starts background notification service
    // https://developer.android.com/guide/components/services.html
    // https://developer.android.com/guide/topics/ui/notifiers/notifications.html
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    // TODO: Make this fancier if there's time
    public void verticalDropDownIconMenu(View view) {
        if (dropDownMenuIconItem.getVisibility() == View.VISIBLE) {
            dropDownMenuIconItem.setVisibility(View.INVISIBLE);
        } else {
            dropDownMenuIconItem.setVisibility(View.VISIBLE);
        }
    }

    // TODO: Split this into different transitions for views
    public void menuItemClick(View view) {
        dropDownMenuIconItem.setVisibility(View.INVISIBLE);
    }

    public void menuGardenClick(View view){
        Intent k = new Intent(MainActivity.this, GardenActivity.class);
        startActivity(k);
    }

    public void menuSettingsClick(View view) {
        Intent k = new Intent(MainActivity.this, SettingsActivity.class);
        startActivity(k);
    }

    public void newPlant(View view) {
        String type = "flower"; // TODO: Change this to non static



        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = settings.edit();


    }

    public void waterPlant(View view) {
        // Cancel all old notifications
        NotificationManager nManager = ((NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE));
        nManager.cancelAll();

        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = settings.edit();

        int current_water = 0;
        settings.getInt("current_water", current_water);
        int daily_water = 0;
        settings.getInt("daily_water", daily_water);

        // TODO: Change this to a dynamic current_water value
        current_water += 8;
        editor.putInt("current_water", current_water);

        if (current_water > daily_water) {
            // TODO: Pop up congratz dialog
        } else {

        }

        // Make a new one
        handleNotification();
    }

    public void addPlanttoGarden(Plant newPlant) {

    }

    private void handleNotification() {
        Intent alarmIntent = new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Calendar c = Calendar.getInstance();
        c.add(Calendar.SECOND, 10);
        long time = c.getTimeInMillis();
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, time, pendingIntent);
    }
}
