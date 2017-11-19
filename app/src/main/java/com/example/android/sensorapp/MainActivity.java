package com.example.android.sensorapp;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    SensorManager sm = null;
    TextView textView = null;
    List list;

    SensorEventListener sel = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float[] values = event.values;
            textView.setText("x :      "+values[0]+"\n\ny :      "+values[1]+"\n\nz :      "+values[2]);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sm = (SensorManager)getSystemService(SENSOR_SERVICE);
        textView = (TextView)findViewById(R.id.textView);

        list = sm.getSensorList(Sensor.TYPE_ACCELEROMETER);
        if (list.size() > 0)
            sm.registerListener(sel, (Sensor)list.get(0), SensorManager.SENSOR_DELAY_NORMAL);
        else
            Toast.makeText(getBaseContext(), "Error: No Accelerometer", Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onStop() {
        if(list.size() > 0)
            sm.unregisterListener(sel);
        super.onStop();
    }
}

