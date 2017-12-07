package com.amazingapps.davidmaisy.grow;

import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    LinearLayout dropDownMenuIconItem;
    ProgressBar progressBar;

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

    private static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int pixels) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap
                .getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0x80FFFFFF;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = pixels;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }
}
