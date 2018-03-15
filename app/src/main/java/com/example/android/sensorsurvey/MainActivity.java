package com.example.android.sensorsurvey;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.List;

/**
 * SensorSurvey queries the sensor manager for a list of available
 * sensors, and displays the list in a TextView.
 */
public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager manager;

    private Sensor ligth;
    private Sensor proximity;
    private Sensor acle;
    private Sensor gyr;

    TextView l;
    TextView pr;
    TextView ac;
    TextView gr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        proximity = manager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        ligth = manager.getDefaultSensor(Sensor.TYPE_LIGHT);
        acle = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        gyr = manager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);


        StringBuilder sensorText = new StringBuilder();

        l = (TextView) findViewById(R.id.sensor_l);
        l.setText(sensorText.toString());

        pr = (TextView) findViewById(R.id.sensor_pr);
        pr.setText(sensorText.toString());

        ac = (TextView) findViewById(R.id.sensor_ac);
        ac.setText(sensorText.toString());

        gr = (TextView) findViewById(R.id.sensor_gr);
        gr.setText(sensorText.toString());

    }

    @Override
    protected void onStop() {
        super.onStop();

        manager.unregisterListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(proximity != null) {
            manager.registerListener(this, proximity, SensorManager.SENSOR_DELAY_NORMAL);
        }

        if(ligth != null) {
            manager.registerListener(this, ligth, SensorManager.SENSOR_DELAY_NORMAL);
        }

        if(acle != null) {
            manager.registerListener(this, acle, SensorManager.SENSOR_DELAY_NORMAL);
        }

        if(gyr != null) {
            manager.registerListener(this, gyr, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }



    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        int sensorType = sensorEvent.sensor.getType();

        switch (sensorType) {
            case Sensor.TYPE_LIGHT:
                float currentValue = sensorEvent.values[0];
                l.setText("light " + currentValue);
                break;
            case Sensor.TYPE_PROXIMITY:
                float currentValue2 = sensorEvent.values[0];
                pr.setText("pr " + currentValue2);
                break;
            case Sensor.TYPE_ACCELEROMETER:
                float currentValue3 = sensorEvent.values[0];
                ac.setText("ac " + currentValue3 + " " + sensorEvent.values[1] + " " + sensorEvent.values[2]);
                break;
            case Sensor.TYPE_GYROSCOPE:
                float currentValue4 = sensorEvent.values[0];
                gr.setText("gr " + currentValue4 + " " + sensorEvent.values[1] + " " + sensorEvent.values[2]);
                break;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
