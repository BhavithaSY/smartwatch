package com.example.hci.heartrateom;

import android.app.Activity;
import android.app.ListActivity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.wearable.view.CircledImageView;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends Activity implements SensorEventListener{

    //UI elements
    private CircledImageView mCircledImageView;
    private TextView mTextView;

    //sensor and sensor manager
    Sensor mHeartRateSensor;
    SensorManager mSensorManager;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //sensor and sensor manager
        mSensorManager=((SensorManager)getSystemService(SENSOR_SERVICE));
        mHeartRateSensor=mSensorManager.getDefaultSensor(Sensor.TYPE_HEART_RATE);
       /* if (mHeartRateSensor == null) {

          final   List<Sensor> sensors=mSensorManager.getSensorList(Sensor.TYPE_ALL);
            for(Sensor sensor1:sensors)
            {
          Log.e("sensors",sensor1.getStringType());
            }
        }*/
        //view
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                mTextView = (TextView) stub.findViewById(R.id.text);
            }
        });
    }

    @Override
    protected void onResume()
    {
        super.onResume();


        if(mSensorManager!=null)
        {
            mSensorManager.registerListener((SensorEventListener)this , mHeartRateSensor , SensorManager.SENSOR_DELAY_NORMAL);
        }
    }
    @Override
    protected void onPause()
    {
        super.onPause();
        if(mSensorManager!=null)
        {
            mSensorManager.unregisterListener((SensorEventListener) this);
        }
    }
  @Override
    public void onSensorChanged(SensorEvent event)
    {

        //update your data.This check is very raw.you should improve it when the sensor is unable to calculate the heart rate
        if(event.sensor.getType()==Sensor.TYPE_HEART_RATE)
        {
            if((int)event.values[0]>0)
            {
                Log.d("mytag",""+Float.toString(event.values[0]));
                //mCircledImageView.setCircleColor(getResources().getColor(R.color.green));
              //  mTextView.setText(""+(int)event.values[0]);

            }
        }
    }

@Override
    public void onAccuracyChanged(Sensor sensor,int accuracy)
{

}






}
