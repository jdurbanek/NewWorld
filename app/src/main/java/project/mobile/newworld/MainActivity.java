package project.mobile.newworld;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String PREFS_NAME = "BaseInfo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SharedPreferences settings = getSharedPreferences("BaseInfo", 0);
        int baseLevel = settings.getInt("Base Level", 1);
        String mac = settings.getString("MAC", "");
     //   Toast toast = Toast.makeText(getApplicationContext(), "My Base level: " + baseLevel, Toast.LENGTH_LONG);
      //  toast.show();

        BroadcastReceiver br = new WifiBroadcastReceiver(getApplicationContext(), mac);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WifiManager.SUPPLICANT_STATE_CHANGED_ACTION);
        getApplicationContext().registerReceiver(br, intentFilter);





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
