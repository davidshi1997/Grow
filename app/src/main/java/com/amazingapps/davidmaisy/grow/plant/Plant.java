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

    String[] monthName = {"January", "February",
            "March", "April", "May", "June", "July",
            "August", "September", "October", "November",
            "December"
    };
    public Plant(String type) {
        this.type = type;
        this.phase = -1;
        this.position = -1;
        Calendar now = Calendar.getInstance();
        this.year = now.get(Calendar.YEAR);
        this.month = now.get(Calendar.MONTH);
        this.dayOfWeek = now.get(Calendar.DAY_OF_WEEK);
        this.day = now.get(Calendar.DAY_OF_MONTH);
    }

    public Plant(String type, int stage) {
        this.type = type;
        this.phase = stage;
        this.position = -1;
        Calendar now = Calendar.getInstance();
        this.year = now.get(Calendar.YEAR);
        this.month = now.get(Calendar.MONTH);
        this.dayOfWeek = now.get(Calendar.DAY_OF_WEEK);
        this.day = now.get(Calendar.DAY_OF_MONTH);

    }

    public String generateURI() {
        return "drawable/ic_" + type + "_" + phase + ".png";
    }

    public String generateDeadURI() {
        if (this.phase != 0)
            return "drawable/ic_" + type + "_" + phase + "_dead.png";
        return null;
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
        System.out.println(this.dayOfWeek);
        return getDayOfWeek() + ", " + monthName[this.month] +  " " + this.day +", " + this.year;
    }
}
