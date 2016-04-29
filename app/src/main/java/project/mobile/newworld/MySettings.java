package project.mobile.newworld;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MySettings extends AppCompatActivity {

    private CheckBox male;
    private CheckBox female;
    private EditText name;
    private EditText sGoals;
    private TextView goalLabel;
    private TextView currentHome;
    private Button updateWifi;
    private int myGoal = 10000;
    private String setHome = "Not set";
    private String myMAC = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_settings);

        male = (CheckBox)findViewById(R.id.gM);
        female = (CheckBox)findViewById(R.id.gF);
        name = (EditText)findViewById(R.id.nameEText);
        sGoals = (EditText)findViewById(R.id.stepGoals);
        goalLabel = (TextView)findViewById(R.id.goalsLabel);
        currentHome = (TextView)findViewById(R.id.currentHome);
        updateWifi = (Button)findViewById(R.id.updateHome);

        SharedPreferences settings = getSharedPreferences("BaseInfo", 0);
        boolean amMale = settings.getBoolean("Male", false);
        boolean amFemale = settings.getBoolean("Female", false);
        String myName = settings.getString("Name", "User");
        int stepG = settings.getInt("Goal", 10000);
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
    }

    public void saveSettings(View view){

        String myName = name.getText().toString().trim();

        try {
            myGoal = Integer.parseInt(sGoals.getText().toString().trim());
        }catch (NumberFormatException e){

        }

        SharedPreferences settings = getSharedPreferences("BaseInfo", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("Goal", myGoal);
        editor.putString("Name", myName);
        editor.putBoolean("Male", male.isChecked());
        editor.putBoolean("Female", female.isChecked());
        editor.commit();
        finish();
    }

    public void updateMyWifi(View view){
        WifiManager wifiManager =
                (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifi = wifiManager.getConnectionInfo();
        final String checkHome = wifi.getSSID();
       // myMAC = wifi.getBSSID();
        if(myMAC.equals("")) {
            myMAC = wifi.getBSSID();
        }
        if(!checkHome.equals(setHome)){
            myMAC = wifi.getBSSID();

            AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
            dlgAlert.setMessage("Are you sure you want to update your home to " + checkHome + "?");
            dlgAlert.setTitle("Update Home");
            dlgAlert.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            //dismiss the dialog
                            setHome = checkHome;
                            currentHome.setText("Current Home: " + setHome);
                        }
                    });
            dlgAlert.setCancelable(true);
            dlgAlert.create().show();
        }else{
            Toast toast = Toast.makeText(getApplicationContext(), "You are already at Home!!", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    @Override
    protected void onStop(){
        super.onStop();

        try {
            myGoal = Integer.parseInt(sGoals.getText().toString().trim());
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
        editor.commit();
    }
}
