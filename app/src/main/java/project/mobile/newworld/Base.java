package project.mobile.newworld;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class Base extends AppCompatActivity {

    public static final String PREFS_NAME = "BaseInfo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);



        SharedPreferences settings = getSharedPreferences("BaseInfo", 0);
        int health = settings.getInt("Health", 1);
        Toast t =  Toast.makeText(getApplicationContext(), "My Health is: " + health, Toast.LENGTH_LONG);
        t.show();


    }

    @Override
    protected void onStop(){
        super.onStop();
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("Base Level", 100);
        editor.commit();

    }
}
