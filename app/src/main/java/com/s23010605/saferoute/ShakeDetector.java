package com.s23010605.saferoute;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class ShakeDetector implements SensorEventListener {

    private static final float SHAKE_THRESHOLD = 12.0f;
    private static final int REQUIRED_SHAKES = 3;
    private static final long SHAKE_RESET_TIME = 1000; // 1 second

    private int shakeCount = 0;
    private long lastShakeTime = 0;
    private OnShakeListener listener;

    public interface OnShakeListener {
        void onShakeDetected();
    }

    public ShakeDetector(OnShakeListener listener) {
        this.listener = listener;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        double acceleration = Math.sqrt(x * x + y * y + z * z) - SensorManager.GRAVITY_EARTH;

        if (acceleration > SHAKE_THRESHOLD) {
            long currentTime = System.currentTimeMillis();
            if (lastShakeTime == 0 || (currentTime - lastShakeTime) < SHAKE_RESET_TIME) {
                shakeCount++;
                if (shakeCount >= REQUIRED_SHAKES) {
                    listener.onShakeDetected();
                    shakeCount = 0;
                }
            } else {
                shakeCount = 1;
            }
            lastShakeTime = currentTime;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Not needed
    }
}
