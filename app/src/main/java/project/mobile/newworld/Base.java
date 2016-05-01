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
    Resource myResources = new Resource();
    Buildings myBuildings = new Buildings();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        SharedPreferences savedResourses = getSharedPreferences("Resource", 0);
        int currWood = savedResourses.getInt("Wood", 0);
        int currStone = savedResourses.getInt("Stone", 0);
        int currMetal = savedResourses.getInt("Metal",0);

        myResources.setWood(currWood);
        myResources.setStone(currStone);
        myResources.setMetal(currMetal);

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




    public void levelFarm(View view) {
        myBuildings.upgradeFarm();
    }


    //extras for testing
    public void testResources(View view)
    {
        SharedPreferences settings = getSharedPreferences("Resource", 0);
        int currWood = settings.getInt("Wood", 0);
        Toast toast = Toast.makeText(getApplicationContext(), "i have  " + currWood + " wood"+"Base is now level" + myBuildings.getMainBuilding(), Toast.LENGTH_LONG);
        toast.show();
    }

    protected void onStop() {
        super.onStop();

    }


}