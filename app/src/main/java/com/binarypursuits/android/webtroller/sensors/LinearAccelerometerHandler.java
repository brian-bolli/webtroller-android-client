package com.binarypursuits.android.webtroller.sensors;

import android.content.Context;
import android.hardware.Sensor;

/**
 * Created by brian on 1/12/2018.
 */

public class LinearAccelerometerHandler extends SensorEventWrapper {

    private static final String NAME = "linear";

    public LinearAccelerometerHandler(Context context) {
        super(context, NAME, Sensor.TYPE_LINEAR_ACCELERATION);
    }

}
