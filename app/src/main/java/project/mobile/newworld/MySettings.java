package project.mobile.newworld;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MySettings extends AppCompatActivity {

    private RadioButton male;
    private RadioButton female;
    private EditText name;
    private EditText sGoals;
    private TextView goalLabel;
    private TextView currentHome;
    private ImageButton updateWifi;
    private int myGoal = 10000;
    private String setHome = "Not set";
    private String myMAC = "";
    private EditText heightFt;
    private EditText heightIn;
    private TextView currentHeight;
    private int myFeet;
    private int myInches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_settings);

        male = (RadioButton)findViewById(R.id.gM);
        female = (RadioButton)findViewById(R.id.gF);
        name = (EditText)findViewById(R.id.nameEText);
        sGoals = (EditText)findViewById(R.id.stepGoals);
        goalLabel = (TextView)findViewById(R.id.goalsLabel);
        currentHome = (TextView)findViewById(R.id.currentHome);
        updateWifi = (ImageButton)findViewById(R.id.updateHome);
        heightFt = (EditText)findViewById(R.id.heightFT);
        heightIn = (EditText)findViewById(R.id.heightIN);
        currentHeight = (TextView)findViewById(R.id.heighttextView);

        SharedPreferences settings = getSharedPreferences("BaseInfo", 0);
        boolean amMale = settings.getBoolean("Male", false);
        boolean amFemale = settings.getBoolean("Female", false);
        String myName = settings.getString("Name", "User");
        int stepG = settings.getInt("Goal", 10000);
        myFeet = settings.getInt("feet", 0);
        myInches = settings.getInt("inches", 0);
        setHome = settings.getString("Wifi", setHome);
        myMAC = settings.getString("MAC", myMAC);

        male.setChecked(amMale);
        female.setChecked(amFemale);
        if(male.isChecked() && female.isChecked()){
            male.setChecked(false);
            female.setChecked(false);
        }

        name.setText(myName);
        goalLabel.setText("Step Goal: " + stepG);
        myGoal = stepG;
        currentHome.setText("Current Home: " +  setHome);
        currentHeight.setText("Current Height: " + myFeet + " ft " + myInches + " in ");

    }

    public void saveSettings(View view){

        String myName = name.getText().toString().trim();


        try {
            myGoal = Integer.parseInt(sGoals.getText().toString().trim());
          //  myFeet = Integer.parseInt(heightFt.getText().toString().trim());
          //  myInches = Integer.parseInt(heightIn.getText().toString().trim());
        }catch (NumberFormatException e){

        }

        SharedPreferences settings = getSharedPreferences("BaseInfo", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("Goal", myGoal);
        editor.putString("Name", myName);
        editor.putBoolean("Male", male.isChecked());
        editor.putBoolean("Female", female.isChecked());
        editor.putInt("feet", myFeet);
        editor.putInt("inches", myInches);
        editor.commit();
        finish();
    }

    public void updateMyWifi(View view){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mWifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if(mWifi.isConnected()) {
            WifiManager wifiManager =
                    (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            WifiInfo wifi = wifiManager.getConnectionInfo();
            final String checkHome = wifi.getBSSID();
            final String currentName = wifi.getSSID();
            // myMAC = wifi.getBSSID();

            if (!checkHome.equals(myMAC)) {


                AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
                dlgAlert.setMessage("Are you sure you want to update your home to " + currentName + "?");
                dlgAlert.setTitle("Update Home");
                dlgAlert.setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //dismiss the dialog
                                myMAC = checkHome;
                                setHome = currentName;
                                currentHome.setText("Current Home: " + setHome);
                            }
                        });
                dlgAlert.setCancelable(true);
                dlgAlert.create().show();
            } else {
                Toast toast = Toast.makeText(getApplicationContext(), "You are already at Home!! " + myMAC, Toast.LENGTH_LONG);
                toast.show();
            }
        }else {
            Toast toast = Toast.makeText(getApplicationContext(), "You are not connected to any WiFi.", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    @Override
    protected void onStop(){
        super.onStop();

        //Toast toast = Toast.makeText(getApplicationContext(), heightFt.getText().toString() + " " + heightIn.getText().toString(), Toast.LENGTH_LONG);
        //toast.show();
        String ft = heightFt.getText().toString();
        String in = heightIn.getText().toString();

        try {
            if(sGoals.getText().toString().length() > 0)
                myGoal = Integer.parseInt(sGoals.getText().toString().trim());
            if(ft.length() > 0)
                myFeet = Integer.parseInt(ft);
            if(in.length() > 0)
                myInches = Integer.parseInt(in);
        }catch (NumberFormatException e){

        }

        SharedPreferences settings = getSharedPreferences("BaseInfo", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("Goal", myGoal);
        editor.putString("Name", name.getText().toString().trim());
        editor.putBoolean("Male", male.isChecked());
        editor.putBoolean("Female", female.isChecked());
        editor.putString("Wifi", setHome);
        editor.putString("MAC", myMAC);
        editor.putInt("feet", myFeet);
        editor.putInt("inches", myInches);
        editor.commit();
    }
}
