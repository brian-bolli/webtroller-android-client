package com.binarypursuits.android.webtroller.sensors;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class AccelerometerHandler extends SensorEventWrapper {

    private static final String NAME = "accelerometer";

    public AccelerometerHandler(Context context) {
        super(context, NAME, Sensor.TYPE_ACCELEROMETER);
    }

}
