package com.amazingapps.davidmaisy.grow.plant;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by davidshi on 12/10/17.
 */

public class Garden {
    String[] monthName = {"JANUARY", "FEBRUARY",
            "MARCH", "APRIL", "MAY", "JUNE", "JULY",
            "AUGUST", "SEPTEMBER", "OCTOBER", "NOVEMBER",
            "DECEMBER"
    };
    private int month;
    private int year;
    private ArrayList<Plant> plants;

    public Garden() {
        this.plants = new ArrayList<Plant>();

        Calendar now = Calendar.getInstance();
        this.year = now.get(Calendar.YEAR);
        this.month = now.get(Calendar.MONTH);
    }

    public Garden(ArrayList<Plant> plants, Calendar past) {
        this.plants = plants;

        this.year = past.get(Calendar.YEAR);
        this.month = past.get(Calendar.MONTH);
    }

    public String getTitle() {
        return monthName[this.month] + " " + this.year;
    }

    public ArrayList<Plant> getPlants() {
        return this.plants;
    }

    public void addPlant(Plant newPlant) {
        plants.add(newPlant);
    }
}
