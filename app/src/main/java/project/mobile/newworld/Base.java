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

    //collect resources
    public void collectWood(View view) {
        myResources.collectWood();
    }
    public void collectMetal(View view) {
        myResources.collectMetal();
    }
    public void collectStone(View view) {
        myResources.collectStone();
    }

    //level buildings
    public void levelMainBuilding(View view){
       int level = myBuildings.getMainBuilding();
        if(level<4){
            if(myResources.getWood()>= level*500){
                myResources.spendWood(level*500);
                myBuildings.upgradeMainBuilding();
                Toast toast = Toast.makeText(getApplicationContext(), "Base is now level" + myBuildings.getMainBuilding(), Toast.LENGTH_LONG);
                toast.show();
            }
            else{
                Toast toast = Toast.makeText(getApplicationContext(), "not enough resources", Toast.LENGTH_LONG);
                toast.show();
            }
        }
        else if(level > 3 && level < 7){
            if(myResources.getWood()>= level*1000 && myResources.getStone() >= level*250) {
                myResources.spendWood(level * 1000);
                myResources.spendStone(level * 250);
                myBuildings.upgradeMainBuilding();
            }
            else{
                Toast toast = Toast.makeText(getApplicationContext(), "not enough resources", Toast.LENGTH_LONG);
                toast.show();
            }
        }
        else if(level >7 && level <10){
            if(myResources.getWood()>= level*1500 && myResources.getStone() >= level*500 && myResources.getMetal() >= level*100) {
                myResources.spendWood(level * 1500);
                myResources.spendStone(level * 500);
                myResources.spendMetal(level * 100);
                myBuildings.upgradeMainBuilding();
            }
            else{
                Toast toast = Toast.makeText(getApplicationContext(), "not enough resources", Toast.LENGTH_LONG);
                toast.show();
            }

        }
        else if(level == 10){
            Toast toast = Toast.makeText(getApplicationContext(), "Base is max level", Toast.LENGTH_LONG);
            toast.show();
        }




    }
    public void levelBarracks(View view){
        myBuildings.upgradeBarracks();
    }
    public void levelFarm(View view)
    {
        myBuildings.upgradeFarm();
    }
    public void levelWall(View view){
        myBuildings.upgradeWall();
    }


    //extras for testing
    public void testResources(View view)
    {
        Toast toast = Toast.makeText(getApplicationContext(), "i have  "+myResources.getWood()+ "materials", Toast.LENGTH_LONG);
        toast.show();
    }
}