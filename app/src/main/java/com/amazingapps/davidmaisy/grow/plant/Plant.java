package com.amazingapps.davidmaisy.grow.plant;

/**
 * Created by davidshi on 12/10/17.
 */

public class Plant {
    private String type;
    private int phase;
    private int position;

    public Plant(String type) {
        this.type = type;
        this.phase = -1;
        this.position = -1;
    }

    public Plant(String type, int stage) {
        this.type = type;
        this.phase = stage;
        this.position = -1;
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
}
