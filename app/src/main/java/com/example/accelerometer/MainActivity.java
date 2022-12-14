package com.example.accelerometer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    public static String MA = "MainActivity";
    SeekBar seekb;
    int status = 10;
    TextView txtS;
    WebView wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        txtS = (TextView) findViewById(R.id.txtStatus);
        seekb = (SeekBar) findViewById(R.id.seekBar);
        wv = (WebView) findViewById(R.id.webview);

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
            float directionSum = Math.abs((sensorEvent.values[0])) + Math.abs((sensorEvent.values[1])) + Math.abs((sensorEvent.values[2]));

            if (Math.abs(sensorEvent.values[0]) > status) {
                Log.w(MA,"The significant Move is on X axis" + R.layout.activity_main);
                 Toast.makeText(this, "Important on X: " + sensorEvent.values[0], Toast.LENGTH_SHORT).show();
            }
            if (Math.abs(sensorEvent.values[1]) > status) {
                Log.w(MA,"The significant Move is on Y axis" + R.layout.activity_main);
                 Toast.makeText(this, "Important on Y: " + sensorEvent.values[1], Toast.LENGTH_SHORT).show();
            }
            if (Math.abs(sensorEvent.values[2]) > status) {
                Log.w(MA,"The significant Move is on Z axis" + R.layout.activity_main);
                 Toast.makeText(this, "Important on Z: " + sensorEvent.values[2], Toast.LENGTH_SHORT).show();
            }

            if (Math.abs(sensorEvent.values[0]) >= Math.max(Math.abs(sensorEvent.values[1]),Math.abs(sensorEvent.values[2]))
            && Math.abs(sensorEvent.values[0]) > status){
                wv.loadUrl("https://www.ecosia.org/");
            }
            else if (Math.abs(sensorEvent.values[1]) >= Math.max(Math.abs(sensorEvent.values[0]),Math.abs(sensorEvent.values[2]))
                    && Math.abs(sensorEvent.values[1]) > status){
                wv.loadUrl("https://www.dogpile.com/");
            }else if (Math.abs(sensorEvent.values[2]) >= Math.max(Math.abs(sensorEvent.values[1]),Math.abs(sensorEvent.values[0]))
                    && Math.abs(sensorEvent.values[2]) > status){
                wv.loadUrl("https://webb.nasa.gov/");
            }
            
             if (directionSum > 25){
                wv.loadUrl("https://jumpingjaxfitness.files.wordpress.com/2010/07/dizziness.jpg");
            }

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }


}
