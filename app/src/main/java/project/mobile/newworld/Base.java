package project.mobile.newworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Base extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }
    public void launchHomeScreen(View view) {
        Intent intent = new Intent(this, OptionsScreen.class);
        startActivity(intent);
    }
    Resource myResources = new Resource();
    Buildings myBuildings = new Buildings();


    public void collectMaterials(View view) {
        myResources.collectMaterials();
    }

    public void upgradeMainBuilding(){
        myBuildings.upgradeMainBuilding();
    }
    public void upgradeBarracks(){
        myBuildings.upgradeBarracks();
    }
    public void upgradeFarm()
    {
        myBuildings.upgradeFarm();
    }
    public void upgradeWall(){
        myBuildings.upgradeWall();
    }

    public void upgradeBase(View view){
        if(myResources.getMaterials()>200){
            Toast toast = Toast.makeText(getApplicationContext(), "My Base level:3 ", Toast.LENGTH_LONG);
            toast.show();
        }
        else
        {
            Toast toast = Toast.makeText(getApplicationContext(), "not enough materials", Toast.LENGTH_LONG);
            toast.show();
        }
    }


    public void testResources(View view)
    {
        Toast toast = Toast.makeText(getApplicationContext(), "i have  "+myResources.getMaterials()+ "materials", Toast.LENGTH_LONG);
        toast.show();
    }
}