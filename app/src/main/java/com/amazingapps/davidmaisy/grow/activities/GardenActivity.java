package com.amazingapps.davidmaisy.grow.activities;

import android.app.ActionBar;
import android.preference.PreferenceFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;

import com.amazingapps.davidmaisy.grow.R;

public class GardenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garden);
    }

    public void showPlantDetails(View view) {
        TextView plantDate = (TextView)findViewById(R.id.tv_plantDate);
        plantDate.setText("hello");

    }



}
