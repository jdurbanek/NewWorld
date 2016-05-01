package project.mobile.newworld;

import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Base extends AppCompatActivity {
    public static final String RESOURCE_NAME = "Resource";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

    }


    public void goToMainBuilding(View view) {
        Intent intent = new Intent(this, MainBuilding.class);
        startActivity(intent);
    }
    public void goToBarracks(View view) {
        Intent intent = new Intent(this, Barracks.class);
        startActivity(intent);
    }
    public void goToFarm(View view) {
        Intent intent = new Intent(this, Farm.class);
        startActivity(intent);
    }
}