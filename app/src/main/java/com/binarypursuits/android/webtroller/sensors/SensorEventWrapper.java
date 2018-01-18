package com.binarypursuits.android.webtroller.sensors;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class SensorEventWrapper implements SensorEventListener {

    private static final String TAG = "SensorEventWrapper";

    private int sensorType;
    private String name;

    protected float[] values = new float[3];

    public SensorEventWrapper(Context context, String name, int sensorType) {
        initialize(context, name, sensorType, SensorManager.SENSOR_DELAY_GAME);
    }

    public SensorEventWrapper(Context context, String name, int sensorType, int sensorDelay) {
        initialize(context, name, sensorType, sensorDelay);
    }

    public float[] getValues() {
        return this.values;
    }

    private JSONObject buildValuesJsonObject() {
        JSONObject obj = new JSONObject();

        try {

            obj.put("x", this.values[0]);
            obj.put("y",this.values[1]);
            obj.put("z", this.values[2]);

        } catch (JSONException e) {

            Log.e(TAG, e.getMessage());

        }

        return obj;
    }

    public JSONObject getJson() {
        JSONObject obj = new JSONObject();

        try {

            obj.put(this.name, this.buildValuesJsonObject());

        } catch (JSONException e) {

            Log.e(TAG, e.getMessage());

        }

        return obj;
    }

    private void initialize(Context context, String name, int sensorType, int sensorDelay) {
        this.sensorType = sensorType;
        this.name = name;
        SensorManager manager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        manager.registerListener(this, manager.getDefaultSensor(sensorType), sensorDelay);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == this.sensorType) {
            this.values = event.values.clone();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}
