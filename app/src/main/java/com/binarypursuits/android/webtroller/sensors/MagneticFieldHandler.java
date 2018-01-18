package com.binarypursuits.android.webtroller.sensors;

import android.content.Context;
import android.hardware.Sensor;

/**
 * Created by brian on 1/12/2018.
 */

public class MagneticFieldHandler extends SensorEventWrapper {

    private static final String NAME = "magnetic";

    public MagneticFieldHandler(Context context) {
        super(context, NAME, Sensor.TYPE_MAGNETIC_FIELD);
    }

}
