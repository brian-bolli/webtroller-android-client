package com.binarypursuits.android.webtroller;

/**
 * Created by brian on 1/1/2018.
 */

public class SensorDataSet {

    public float x;
    public float y;
    public float z;

    SensorDataSet(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    SensorDataSet(float[] coords) {
        this.x = coords[0];
        this.y = coords[1];
        this.z = coords[2];
    }

}
