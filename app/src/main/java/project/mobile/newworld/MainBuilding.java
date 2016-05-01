package project.mobile.newworld;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainBuilding extends AppCompatActivity {


    public static final String RESOURCE_NAME = "Resource";
    private TextView mbLevel;
    private TextView currWood;
    private TextView currStone;
    private TextView currMetal;

    Resource myResources = new Resource();
    Buildings myBuildings = new Buildings();
    SharedPreferences savedResourses = getSharedPreferences("Resource", 0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main_building);

        super.onCreate(savedInstanceState);

        mbLevel = (TextView) findViewById(R.id.mbLevel);
        currWood = (TextView) findViewById(R.id.currWood);
        currStone = (TextView) findViewById(R.id.currStone);
        currMetal = (TextView) findViewById(R.id.currMetal);


        int cWood = savedResourses.getInt("Wood", 0);
        int cStone = savedResourses.getInt("Stone", 0);
        int cMetal = savedResourses.getInt("Metal",0);

        myResources.setWood(cWood);
        myResources.setStone(cStone);
        myResources.setMetal(cMetal);
        currWood.setText("Wood " + savedResourses.getInt("Wood", 0));
        currStone.setText("Stone " + savedResourses.getInt("Stone", 0));
        currMetal.setText("Metal " + savedResourses.getInt("Metal", 0));

        mbLevel.setText("Level " + getMainBuildingLevel());

    }


    public int getMainBuildingLevel() {
        int currLevel = savedResourses.getInt("MB", 1);
        return currLevel;
    }

    public void levelMainBuilding(View view) {
        int level = myBuildings.getMainBuilding();
        if (level < 4) {
            if (myResources.getWood() >= level * 500) {
                myResources.spendWood(level * 500);
                myBuildings.upgradeMainBuilding();
                SharedPreferences resources = getSharedPreferences(RESOURCE_NAME, 0);
                SharedPreferences.Editor editor = resources.edit();
                editor.putInt("Wood", myResources.spendWood(level * 500));
                editor.commit();
            } else {
                Toast toast = Toast.makeText(getApplicationContext(), "not enough resources", Toast.LENGTH_LONG);
                toast.show();
            }
        } else if (level > 3 && level < 7) {
            if (myResources.getWood() >= level * 1000 && myResources.getStone() >= level * 250) {
                myResources.spendWood(level * 1000);
                myResources.spendStone(level * 250);
                myBuildings.upgradeMainBuilding();
            } else {
                Toast toast = Toast.makeText(getApplicationContext(), "not enough resources", Toast.LENGTH_LONG);
                toast.show();
            }
        } else if (level > 7 && level < 10) {
            if (myResources.getWood() >= level * 1500 && myResources.getStone() >= level * 500 && myResources.getMetal() >= level * 100) {
                myResources.spendWood(level * 1500);
                myResources.spendStone(level * 500);
                myResources.spendMetal(level * 100);
                myBuildings.upgradeMainBuilding();
            } else {
                Toast toast = Toast.makeText(getApplicationContext(), "not enough resources", Toast.LENGTH_LONG);
                toast.show();
            }

        } else if (level == 10) {
            Toast toast = Toast.makeText(getApplicationContext(), "Base is max level", Toast.LENGTH_LONG);
            toast.show();
        }

    }

    public void collectWood(View view) {
        SharedPreferences resources = getSharedPreferences(RESOURCE_NAME, 0);
        SharedPreferences.Editor editor = resources.edit();
        editor.putInt("Wood", myResources.collectWood());
        editor.commit();
        currWood.setText("Wood " + resources.getInt("Wood", 0));
    }
    public void collectStone(View view) {
        SharedPreferences resources = getSharedPreferences(RESOURCE_NAME, 0);
        SharedPreferences.Editor editor = resources.edit();
        editor.putInt("Stone", myResources.collectStone());
        editor.commit();
        currStone.setText("Stone " + resources.getInt("Stone", 0));
    }
    public void collectMetal(View view) {
        SharedPreferences resources = getSharedPreferences(RESOURCE_NAME, 0);
        SharedPreferences.Editor editor = resources.edit();
        editor.putInt("Metal", myResources.collectMetal());
        editor.commit();
        currMetal.setText("Metal " + resources.getInt("Metal", 0));
    }
}
