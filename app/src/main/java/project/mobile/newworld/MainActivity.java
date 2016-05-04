package project.mobile.newworld;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {

    public static final String PREFS_NAME = "BaseInfo";

    private TextView welcomeName;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        welcomeName = (TextView) findViewById(R.id.welcome);
        SharedPreferences settings = getSharedPreferences("BaseInfo", 0);
        int baseLevel = settings.getInt("Base Level", 1);
        String mac = settings.getString("MAC", "");
        String name = settings.getString("Name", "");
        //  Toast toast = Toast.makeText(getApplicationContext(), mac, Toast.LENGTH_LONG);
        // toast.show();
        welcomeName.setText("    Welcome \n" + name);
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
    protected void onStop() {
        super.onStop();

        //SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        //SharedPreferences.Editor editor = settings.edit();
        //editor.putInt("Health", 1000);
        //editor.commit();


    }


}
