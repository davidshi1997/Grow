package com.amazingapps.davidmaisy.grow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    LinearLayout dropDownMenuIconItem;

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

        dropDownMenuIconItem = (LinearLayout) findViewById(R.id.vertical_dropdown_menu_items);
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
}
