package project.mobile.newworld;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.SystemClock;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.os.Handler;

import java.lang.reflect.Type;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class GoOut extends AppCompatActivity implements SensorEventListener {
    public static final String PREF_NAME = "Resource";


    private SensorManager mSensorManager;
    private Sensor mStepCounterSensor;
    private Sensor mStepDetectorSensor;
    private int currSteps = 0;
    private int numWeeks = 0;

    Button butnstart, butnreset;
    TextView time;
    long starttime = 0L;
    long timeInMilliseconds = 0L;
    long timeSwapBuff = 0L;
    long updatedtime = 0L;
    int t = 1;
    int secs = 0;
    int mins = 0;
    int milliseconds = 0;
    Handler handler = new Handler();


    TextView steps;

    int myType = 0;
    TextView myText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go_out);

        steps = (TextView)findViewById(R.id.steps);
        butnstart = (Button) findViewById(R.id.startStop);
        butnstart.setText("Start");
        time = (TextView)findViewById(R.id.time);


        SharedPreferences settings = getSharedPreferences(PREF_NAME, 0);
        currSteps = settings.getInt("currSteps", 0);
        //numWeeks = settings.getInt("numWeeks", 0); will use when functional
        steps.setText("" + currSteps);

        mSensorManager = (SensorManager)
                getSystemService(Context.SENSOR_SERVICE);
        mStepCounterSensor = mSensorManager
                .getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        mStepDetectorSensor = mSensorManager
                .getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);

        myText = (TextView) findViewById(R.id.activityStatus);
        if(myType == 0) {
            myText.setText("Idle");
        }else if(myType == 1) {
            myText.setText("@string/Running");
        }else if(myType == 2) {
            myText.setText("Walking");
        }else if(myType == 3){
            myText.setText("Biking");
        }


        //fake week persistence
        ArrayList<Week> weekList = new ArrayList<>();
        numWeeks = 0;
        for(int x = 0; x < 10 ; x++){
            String date;
            Date newDate = new Date();
            Format formatter;
            formatter = new SimpleDateFormat("dd/MM/yyyy");
            date = formatter.format(newDate);
            Week week = new Week(date);
            for(int j = 0; j < 7; j++){
                Day day = new Day(date);
                day.setSteps((j+x)*215 +3000 );
                day.setTime((j+x) * 10 + 5);
                day.setDistance(day.getSteps() / 2000);
                week.addDay(day);
            }
            weekList.add(week);
            numWeeks++;
        }


        //SharedPreferences settings = getSharedPreferences(PREF_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("numWeeks",numWeeks);
        for(int t = 0; t < weekList.size(); t++){
            String str = "" + (t+1);
            //Toast toast = Toast.makeText(getApplicationContext(), weekList.get(t).toString(), Toast.LENGTH_LONG);
            //toast.show();
            editor.putString(str, weekList.get(t).toString());
            editor.commit();
        }
        //editor.commit();

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void onSensorChanged(SensorEvent event) {
        Sensor sensor = event.sensor;
        float[] values = event.values;
        int value = -1;

        if (values.length > 0) {
            value = (int) values[0];
        }

        if (sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
            steps.setText("Step Counter Detected : " + value);
        } else if (sensor.getType() == Sensor.TYPE_STEP_DETECTOR) {
            // For test only. Only allowed value is 1.0 i.e. for step taken
            steps.setText("Step Detector Detected : " + value);
        }
    }

    protected void onResume() {

        super.onResume();

        SharedPreferences settings = getSharedPreferences(PREF_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("currSteps", 2000); //currSteps TODO
        editor.commit();

        mSensorManager.registerListener(this, mStepCounterSensor,

                SensorManager.SENSOR_DELAY_FASTEST);
        mSensorManager.registerListener(this, mStepDetectorSensor,

                SensorManager.SENSOR_DELAY_FASTEST);

    }

    protected void onStop() {
        super.onStop();
        mSensorManager.unregisterListener(this, mStepCounterSensor);
        mSensorManager.unregisterListener(this, mStepDetectorSensor);
        SharedPreferences settings = getSharedPreferences(PREF_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("currSteps", 3000); // TODO currSteps
        editor.commit();
    }


    public void launchCurrentWeek(View view){
        Intent intent = new Intent(this, MyWeek.class);
        startActivity(intent);
    }
    public void selectActivity(View view) {
        Intent intent = new Intent(this, MyType.class);
        startActivityForResult(intent, 1);
    }

    public void restart(View view){
        //persist to file Resourse, Keys: Wood, Stone, Metal, BaseLevel

        int wood;
        int stone;
        int metal;

        wood = (int)(currSteps * .1);
        stone = (int)(currSteps * .05);
        metal = (int) (currSteps * .001);

        //calcualte wood, stone, metal based on time and curr steps



    //    SharedPreferences settings = getSharedPreferences(RESOURCE_NAME, 0);
    //    SharedPreferences.Editor editor = settings.edit();
    //    editor.putInt("currSteps", currSteps);
    //    editor.commit();

        starttime = 0L;
        timeInMilliseconds = 0L;
        timeSwapBuff = 0L;
        updatedtime = 0L;
        t = 1;
        secs = 0;
        mins = 0;
        milliseconds = 0;
        butnstart.setText("Start");
        handler.removeCallbacks(updateTimer);
        time.setText("00:00:00");


        //idk if this is working correctly
        SharedPreferences settings = getSharedPreferences(PREF_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        currSteps += settings.getInt("currSteps", 0);
        editor.putInt("currSteps", currSteps);
        editor.commit();
        currSteps = 0;// have to persist
    }



    public void start(View view) {
        //Intent intent = new Intent(this, MyType.class);
        //startActivityForResult(intent, 1);
        //if(butnstart.getText().equals("Start")) butnstart.setText("Stop");
        //else butnstart.setText("Start");


        if (t == 1) {
            butnstart.setText("Pause");
            starttime = SystemClock.uptimeMillis();
            handler.postDelayed(updateTimer, 0);
            t = 0;
        } else {
            butnstart.setText("Start");
            //time.setTextColor(Color.BLUE);
            timeSwapBuff += timeInMilliseconds;
            handler.removeCallbacks(updateTimer);
            t = 1;
        }

    }

    public Runnable updateTimer = new Runnable() {

        public void run() {

            timeInMilliseconds = SystemClock.uptimeMillis() - starttime;

            updatedtime = timeSwapBuff + timeInMilliseconds;

            secs = (int) (updatedtime / 1000);
            mins = secs / 60;
            secs = secs % 60;
            milliseconds = (int) (updatedtime % 1000);
            time.setText("" + mins + ":" + String.format("%02d", secs) + ":"
                    + String.format("%03d", milliseconds));
            //time.setTextColor(Color.RED);
            handler.postDelayed(this, 0);
        }

    };






    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1) {
            if(resultCode == RESULT_OK){
                myType = data.getIntExtra("Type", 0);
                if(myType == 0) {
                    myText.setText("Idle");
                }else if(myType == 1) {
                    myText.setText("Running");
                }else if(myType == 2) {
                    myText.setText("Walking");
                }else if(myType == 3){
                    myText.setText("Biking");
                }
            }
        }
    }

}
