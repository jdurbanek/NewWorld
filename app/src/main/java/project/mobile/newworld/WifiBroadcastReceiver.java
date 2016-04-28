package project.mobile.newworld;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.widget.Toast;

/**
 * Created by Josh on 4/27/2016.
 */
public class WifiBroadcastReceiver extends BroadcastReceiver {

    private Context context;

    public WifiBroadcastReceiver(Context c){
        this.context = c;
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        String action = intent.getAction();
        if (WifiManager.SUPPLICANT_STATE_CHANGED_ACTION .equals(action)) {
            SupplicantState state = intent.getParcelableExtra(WifiManager.EXTRA_NEW_STATE);
            if (SupplicantState.isValidState(state)
                    && state == SupplicantState.COMPLETED) {

                boolean connected = checkConnectedToDesiredWifi();
                Toast toast = Toast.makeText(context, "Connected: " + connected, Toast.LENGTH_LONG);
                toast.show();
            }else {
                boolean connected = false;
                Toast toast = Toast.makeText(context, "Connected: " + connected, Toast.LENGTH_LONG);
                toast.show();
            }
        }
    }

    /** Detect you are connected to a specific network. */
    private boolean checkConnectedToDesiredWifi() {
        boolean connected = false;
        //set to the desired wifi mac address.
        String desiredMacAddress = "00:24:6c:ce:9c:81";

        WifiManager wifiManager =
                (WifiManager) context.getSystemService(Context.WIFI_SERVICE);

        WifiInfo wifi = wifiManager.getConnectionInfo();
     //   Toast toast = Toast.makeText(context, "Connected: " + wifi, Toast.LENGTH_LONG);
       // toast.show();
        if (wifi != null) {
            // get current router Mac address
            String bssid = wifi.getBSSID();
           // Toast toast = Toast.makeText(context, "Connected: " + bssid, Toast.LENGTH_LONG);
           // toast.show();
            connected = desiredMacAddress.equals(bssid);
        }

        return connected;
    }

}
