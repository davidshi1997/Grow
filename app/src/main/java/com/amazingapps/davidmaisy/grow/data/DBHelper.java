package com.amazingapps.davidmaisy.grow.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

import com.amazingapps.davidmaisy.grow.plant.Garden;
import com.amazingapps.davidmaisy.grow.plant.Plant;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Grow.db";
    public static final int DATABASE_VERSION = 1;

    public static final String INVENTORY_TABLE_NAME = "inventory";
    public static final String INVENTORY_COLUMN_TYPE = "type";
    public static final String INVENTORY_COLUMN_COUNT = "count";

    public static final String GARDEN_TABLE_NAME = "garden";
    public static final String GARDEN_COLUMN_ID = "id";
    public static final String GARDEN_COLUMN_TYPE = "type";
    public static final String GARDEN_COLUMN_PHASE = "phase";
    public static final String GARDEN_COLUMN_STATE = "state";
    public static final String GARDEN_COLUMN_POSITION = "position";
    public static final String GARDEN_COLUMN_CALENDAR = "calendar";
    private HashMap hp;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + INVENTORY_TABLE_NAME + "(" +
                INVENTORY_COLUMN_TYPE + " TEXT PRIMARY KEY, " +
                INVENTORY_COLUMN_COUNT + " INTEGER NOT NULL);"
        );

        db.execSQL("CREATE TABLE IF NOT EXISTS " + GARDEN_TABLE_NAME + "(" +
                GARDEN_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                GARDEN_COLUMN_TYPE + " TEXT NOT NULL, " +
                GARDEN_COLUMN_PHASE + " INTEGER NOT NULL, " +
                GARDEN_COLUMN_STATE + " TEXT NOT NULL, " +
                GARDEN_COLUMN_POSITION + " INTEGER NOT NULL, " +
                GARDEN_COLUMN_CALENDAR + " TEXT NOT NULL);"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS " + INVENTORY_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + GARDEN_TABLE_NAME);
        onCreate(db);
    }

    public int numberOfPlants(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, GARDEN_TABLE_NAME);
        return numRows;
    }

    public boolean updateCurrentPlant (Plant plant) {
        Calendar now = Calendar.getInstance();
        long lastId;

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT id from garden order by id DESC limit 1";
        Cursor c = db.rawQuery(query, null);
        if (c != null && c.moveToFirst()) {
            lastId = c.getLong(0); //The 0 is the column index, we only have 1 column, so the index is 0
            ContentValues contentValues = new ContentValues();
            contentValues.put("type", plant.getType());
            contentValues.put("phase", plant.getPhase());
            contentValues.put("state", plant.getState());
            contentValues.put("position", plant.getPosition());
            contentValues.put("calendar", now.toString());

            db.update("contacts", contentValues, "id = ? ", new String[]{String.valueOf(lastId)});
            return true;
        } else {
            return false;
        }
    }

    public boolean insertPlant (Plant newPlant) {
        Calendar now = Calendar.getInstance();

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("type", newPlant.getType());
        contentValues.put("phase", newPlant.getPhase());
        contentValues.put("state", newPlant.getState());
        contentValues.put("position", newPlant.getPosition());
        contentValues.put("calendar", now.toString());
        db.insert("garden", null, contentValues);
        return true;
    }

    public Plant getCurrentPlant () {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res =  db.rawQuery( "select * from garden", null );
        res.moveToFirst();

        String type;
        int phase;
        String state;
        int position;
        String calendar;

        type = res.getString(0);
        phase = res.getInt(1);
        state = res.getString(2);
        position = res.getInt(3);
        calendar = res.getString(4);

        try {
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
            cal.setTime(sdf.parse(calendar));

            Plant plant = new Plant(type, phase, state, position, cal);
            return plant;
        } catch (ParseException e) {
            return null;
        }
    }

    public ArrayList<Garden> makeGardens() {
        ArrayList<Garden> gardens = new ArrayList<Garden>();
        ArrayList<Plant> plants = new ArrayList<Plant>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from garden", null );
        res.moveToFirst();

        String type;
        int phase;
        String state;
        int position;
        String calendar;

        Calendar prev = null;

        // Loop through entire garden table
        while(!res.isAfterLast()) {
            type = res.getString(1);
            phase = res.getInt(2);
            state = res.getString(3);
            position = res.getInt(4);
            calendar = res.getString(5);

            res.moveToNext();

            // Just in case the plant is not yet grown
            if(position == -1) {
                continue;
            }

            try {
                Calendar cal = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
                cal.setTime(sdf.parse(calendar));
                Plant plant = new Plant(type, phase, state, position, cal);

                if (prev == null ||
                        (prev.get(Calendar.YEAR) == cal.get(Calendar.YEAR) && prev.get(Calendar.MONTH) == cal.get(Calendar.MONTH))){
                    plants.add(plant);
                } else {
                    Garden garden = new Garden(plants, prev);
                    gardens.add(garden);

                    plants = new ArrayList<Plant>();
                    plants.add(plant);
                }

                if (prev != null && res.isAfterLast() && prev.get(Calendar.YEAR) == cal.get(Calendar.YEAR) && prev.get(Calendar.MONTH) == cal.get(Calendar.MONTH)) {
                    Garden garden = new Garden(plants, prev);
                    gardens.add(garden);
                }

                prev = cal;
            } catch(ParseException e){
                return null;
            }
        }

        return gardens;
    }
}