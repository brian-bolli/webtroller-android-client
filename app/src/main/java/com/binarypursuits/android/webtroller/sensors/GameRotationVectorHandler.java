package com.binarypursuits.android.webtroller.sensors;

import android.content.Context;
import android.hardware.Sensor;

/**
 * Created by brian on 1/16/2018.
 */

public class GameRotationVectorHandler extends SensorEventWrapper {

    private static final String NAME = "gamerotation";

    public GameRotationVectorHandler(Context context) {
        super(context, NAME, Sensor.TYPE_GAME_ROTATION_VECTOR);
    }

}
