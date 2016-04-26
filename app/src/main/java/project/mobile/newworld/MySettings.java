package project.mobile.newworld;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
    private int myGoal = 10000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_settings);

        male = (CheckBox)findViewById(R.id.gM);
        female = (CheckBox)findViewById(R.id.gF);
        name = (EditText)findViewById(R.id.nameEText);
        sGoals = (EditText)findViewById(R.id.stepGoals);
        goalLabel = (TextView)findViewById(R.id.goalsLabel);

        SharedPreferences settings = getSharedPreferences("BaseInfo", 0);
        boolean amMale = settings.getBoolean("Male", false);
        boolean amFemale = settings.getBoolean("Female", false);
        String myName = settings.getString("Name", "User");
        int stepG = settings.getInt("Goal", 10000);

        male.setChecked(amMale);
        female.setChecked(amFemale);
        if(male.isChecked() && female.isChecked()){
            male.setChecked(false);
            female.setChecked(false);
        }

        name.setText(myName);
        goalLabel.setText("Step Goal: " + stepG);
        myGoal = stepG;
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

    @Override
    protected void onStop(){
        super.onStop();

        try {
            myGoal = Integer.parseInt(sGoals.getText().toString().trim());
        }catch (NumberFormatException e){

        }
        Toast toast = Toast.makeText(getApplicationContext(), "" + myGoal, Toast.LENGTH_LONG);
        toast.show();
        SharedPreferences settings = getSharedPreferences("BaseInfo", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("Goal", myGoal);
        editor.putString("Name", name.getText().toString().trim());
        editor.putBoolean("Male", male.isChecked());
        editor.putBoolean("Female", female.isChecked());
        editor.commit();
    }
}
