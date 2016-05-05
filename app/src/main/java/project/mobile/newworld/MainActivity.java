package project.mobile.newworld;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    public static final String PREFS_NAME = "BaseInfo";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        SharedPreferences settings = getSharedPreferences("BaseInfo", 0);
        int baseLevel = settings.getInt("Base Level", 1);
        String mac = settings.getString("MAC", "");
        Toast toast = Toast.makeText(getApplicationContext(), mac, Toast.LENGTH_LONG);
        toast.show();
        BroadcastReceiver br = new WifiBroadcastReceiver(getApplicationContext(), mac);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WifiManager.SUPPLICANT_STATE_CHANGED_ACTION);
        getApplicationContext().registerReceiver(br, intentFilter);








        //fake week persistence
        ArrayList<Week> weekList = new ArrayList<>();
        int numWeeks = 0;// take out for final version when num weeks actually changes TODO



        //dummy data date change will be detected and weeks will be created automatically TODO
        //for(int x = 0; x < 10 ; x++){
        String date;


        //extremely hard coded to put in dummy data.
        //String today = formatter.format(newDate);
        Date newDate = new Date();
        Format formatter;
        formatter = new SimpleDateFormat("dd/MM/yyyy");
        String today = formatter.format(newDate);


        date = "04/17/2106";
        Week week = new Week(date);
        int dayNum = 17;
        int month  = 4;
        int j = 0;
        boolean done = false;
        while(!done){
            String dayNumStr = "" +dayNum;
            if(dayNum < 10)
                dayNumStr = "0" + dayNum;
            date = dayNumStr+ "/" +"0" + month + "/2016";
            System.out.println("Here " + date);
            Day day = new Day(date);
            day.setSteps((j)*215 +3000 );
            day.setTime((j) * 10 + 5);
            day.setDistance(day.getSteps() / 2000);
            if(week.getDays().size() == 7){
                weekList.add(week);
                week = new Week(day.getDate());
                numWeeks++;
            }
            if(date.equals(today)){
                day.setSteps(0);
                day.setDistance(0);
                day.setTime(0);
                done = true;
            }
            week.addDay(day);
            if(dayNum == 30) {
                dayNum = 1;
                month = 5;
            }
            else
                dayNum++;
            j++;
        }
        System.out.println("The lastweek: " + week.toString());
        weekList.add(week);
        numWeeks++;
        SharedPreferences settingsTwo = getSharedPreferences("Resource", 0);
        SharedPreferences.Editor editor = settingsTwo.edit();
        editor.putInt("numWeeks",numWeeks);
        for(int t = 0; t < weekList.size(); t++){
            String str = "" + (t+1);
            editor.putString(str, weekList.get(t).toString());
            System.out.println(weekList.get(t).toString() + "main");
            editor.commit();
        }

    }



    public void launchFirstScreen(View view) {
        Intent intent = new Intent(this, OptionsScreen.class);
        startActivity(intent);
    }

    @Override
    protected void onStop(){
        super.onStop();
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("Health", 1000);
        editor.commit();

    }
}
