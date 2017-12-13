package com.amazingapps.davidmaisy.grow.plant;
import java.util.Calendar;
import java.util.Date;
/**
 * Created by davidshi on 12/10/17.
 */

public class Plant {
    private String type;
    private int phase;
    private int position;
    private int year;
    private int month;
    private int day;
    private int dayOfWeek;
    private String state;

    String[] monthName = {"January", "February",
            "March", "April", "May", "June", "July",
            "August", "September", "October", "November",
            "December"
    };
    public Plant(String type) {
        this.type = type;
        this.phase = -1;
        this.state = "alive";
        this.position = -1;
        Calendar now = Calendar.getInstance();
        this.year = now.get(Calendar.YEAR);
        this.month = now.get(Calendar.MONTH);
        this.dayOfWeek = now.get(Calendar.DAY_OF_WEEK);
        this.day = now.get(Calendar.DAY_OF_MONTH);
    }

    public Plant(String type, int phase, String state) {
        this.type = type;
        this.phase = phase;
        this.state = state;
        this.position = -1;
        Calendar now = Calendar.getInstance();
        this.year = now.get(Calendar.YEAR);
        this.month = now.get(Calendar.MONTH);
        this.dayOfWeek = now.get(Calendar.DAY_OF_WEEK);
        this.day = now.get(Calendar.DAY_OF_MONTH);
    }

    public Plant(String type, int phase, String state, int position, Calendar past) {
        this.type = type;
        this.phase = phase;
        this.state = state;
        this.position = position;
        this.year = past.get(Calendar.YEAR);
        this.month = past.get(Calendar.MONTH);
        this.dayOfWeek = past.get(Calendar.DAY_OF_WEEK);
        this.day = past.get(Calendar.DAY_OF_MONTH);
    }


    public String generateURI() {
        return "drawable/ic_" + type + "_" + phase + "_" + state + ".png";
    }


    public boolean putPosition(int position) {
        if (position < 36 && position > -1) {
            this.position = position;
            return true;
        }
        return false;
    }

    public int getPosition() {
        return this.position;
    }

    public String getType(){ return this.type;}

    public int getPhase(){ return this.phase;}

    public String getState(){ return this.state;}

    private String getDayOfWeek(){
        switch (dayOfWeek) {
            case Calendar.SUNDAY:
                return "Sunday";

            case Calendar.MONDAY:
                return "Monday";

            case Calendar.TUESDAY:
                return "Tuesday";

            case Calendar.WEDNESDAY:
                return "Wednesday";

            case Calendar.THURSDAY:
                return "Thursday";

            case Calendar.FRIDAY:
                return "Friday";

            case Calendar.SATURDAY:
                return "Saturday";
        }
        return "";
    }

    public String getDate(){
        return getDayOfWeek() + ", " + monthName[this.month] +  " " + this.day +", " + this.year;
    }
}
