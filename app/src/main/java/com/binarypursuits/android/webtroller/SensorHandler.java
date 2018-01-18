package com.binarypursuits.android.webtroller;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;

import com.binarypursuits.android.webtroller.sensors.AccelerometerHandler;
import com.binarypursuits.android.webtroller.sensors.GameRotationVectorHandler;
import com.binarypursuits.android.webtroller.sensors.LinearAccelerometerHandler;
import com.binarypursuits.android.webtroller.sensors.MagneticFieldHandler;

import org.json.JSONException;
import org.json.JSONObject;

public class SensorHandler {

    private static final String TAG = "SensorHandler";

    private AccelerometerHandler accelerometer;
    private LinearAccelerometerHandler linear;
    private MagneticFieldHandler magnetic;
    private GameRotationVectorHandler rotation;

    public SensorHandler(Context context) {

        accelerometer = new AccelerometerHandler(context);
        linear = new LinearAccelerometerHandler(context);
        magnetic = new MagneticFieldHandler(context);
        rotation = new GameRotationVectorHandler(context);

    }

    private float[] calculateOrientation() {
        float R[] = new float[9];
        float outR[] = new float[9];
        float I[] = new float[9];
        float orientation[] = new float[3];

        //  boolean success = SensorManager.getRotationMatrix(R, I, this.accelerometer.getValues(), this.magnetic.getValues());
        boolean success = SensorManager.getRotationMatrix(R, I, this.accelerometer.getValues(), this.rotation.getValues());

        if (success) {
            SensorManager.remapCoordinateSystem(R, SensorManager.AXIS_Y, SensorManager.AXIS_X, outR);
            SensorManager.getOrientation(outR, orientation);
        }

        return orientation;
    }

    public float getOffsetDegree() {
        float[] orientation = this.calculateOrientation();
        return (float)(Math.toDegrees(orientation[0]) + 360) % 360;
    }

    public JSONObject getSensorJson() {
        JSONObject obj = new JSONObject();

        try {

            obj.put("orientation", this.getOrientation());
            obj.put("linear", this.linear.getJson());

        } catch (JSONException e) {

            Log.e(TAG, e.getMessage());

        }

        return obj;
    }

    private JSONObject getOrientation() {
        JSONObject obj = new JSONObject();
        float[] orientation = this.calculateOrientation();

        try {

            obj.put("z", orientation[0]);
            obj.put("x",orientation[1]);
            obj.put("y", orientation[2]);

        } catch (JSONException e) {

            Log.e(TAG, e.getMessage());

        }

        return obj;

    }

}
