package com.example.accelerometer;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    SeekBar seekb;
    int status;
    TextView txtS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtS = (TextView) findViewById(R.id.txtStatus);
        seekb = (SeekBar) findViewById(R.id.seekBar);
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        if (sensorManager != null){
            Sensor acclero = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            if (acclero != null){
                sensorManager.registerListener(this, acclero, SensorManager.SENSOR_DELAY_NORMAL);

            }

        }else{
            Toast.makeText(this, "Sensor not detected", Toast.LENGTH_SHORT).show();
        }

        seekb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                status = progress;
                txtS.setText("" + status);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            if (Math.abs(sensorEvent.values[0]) > status) {
                ((TextView) findViewById(R.id.testX)).setText("Important on X: " + sensorEvent.values[0]);
            }
            if (Math.abs(sensorEvent.values[1]) > status) {
                ((TextView) findViewById(R.id.testY)).setText("Important on Y: " + sensorEvent.values[1]);
            }
            if (Math.abs(sensorEvent.values[2]) > status) {
                ((TextView) findViewById(R.id.testZ)).setText("Important on Z: " + sensorEvent.values[2]);
            }

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}