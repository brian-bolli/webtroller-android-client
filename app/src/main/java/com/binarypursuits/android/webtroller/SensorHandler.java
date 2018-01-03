package com.binarypursuits.android.webtroller;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class SensorHandler implements SensorEventListener {

    private static final String TAG = "SensorHandler";

    private float[] linear = new float[3];
    private float[] orientation = new float[3];

    public SensorHandler(Context context) {
        SensorManager manager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);

        Sensor mLinear = manager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        manager.registerListener(this, mLinear, SensorManager.SENSOR_DELAY_GAME);

        Sensor mOrientation = manager.getDefaultSensor(Sensor.TYPE_GAME_ROTATION_VECTOR);
        manager.registerListener(this, mOrientation, SensorManager.SENSOR_DELAY_GAME);
    }

    public JSONObject getUpdatedOrientationAngles() {

        JSONObject obj = new JSONObject();

        try {
            Double degrees = (orientation[0] * 180) / Math.PI;
            obj.put("x", degrees.floatValue());

            degrees = (orientation[1] * 180) / Math.PI;
            obj.put("y", degrees.floatValue());

            degrees = (orientation[2] * 180) / Math.PI;
            obj.put("z", degrees.floatValue());

        } catch (JSONException e) {

            Log.e(TAG, e.getMessage());

        }

        return obj;
    }

    public JSONObject getUpdatedLinearAcceleration() {

        JSONObject obj = new JSONObject();

        try {

            Double degrees = (linear[0] * 180) / Math.PI;
            obj.put("x",  degrees.floatValue());

            degrees = (linear[1] * 180) / Math.PI;
            obj.put("y",  degrees.floatValue());

            degrees = (linear[2] * 180) / Math.PI;
            obj.put("z",  degrees.floatValue());

        } catch (JSONException e) {

            Log.e(TAG, e.getMessage());

        }

        return obj;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) { }

    @Override
    public void onSensorChanged(SensorEvent event) {

        switch (event.sensor.getType()) {
            case Sensor.TYPE_LINEAR_ACCELERATION:
                linear = event.values.clone();
                break;
            case Sensor.TYPE_GAME_ROTATION_VECTOR:
                orientation = event.values.clone();
                break;
            default:
                return;
        }

    }
}
