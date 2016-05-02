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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main_building);
        super.onCreate(savedInstanceState);

        mbLevel = (TextView) findViewById(R.id.mbLevel);
        currWood = (TextView) findViewById(R.id.currWood);
        currStone = (TextView) findViewById(R.id.currStone);
        currMetal = (TextView) findViewById(R.id.currMetal);

        SharedPreferences saves = getSharedPreferences("Resource", 0);
        //remove next 3 lines after testing is over
        SharedPreferences.Editor editor = saves.edit();
        editor.putInt("MB", 1);
        editor.commit();
        int cWood = saves.getInt("Wood", 0);
        int cStone = saves.getInt("Stone", 0);
        int cMetal = saves.getInt("Metal",0);
        int cMB = saves.getInt("MB", 1);

        myResources.setWood(cWood);
        myResources.setStone(cStone);
        myResources.setMetal(cMetal);
        myBuildings.setMBLevel(cMB);

        currWood.setText("Wood " + cWood);
        currStone.setText("Stone " + cStone);
        currMetal.setText("Metal " + cMetal);
        mbLevel.setText("Level " + cMB);
    }


    public void levelMainBuilding(View view) {
        SharedPreferences saves = getSharedPreferences("Resource", 0);
        int level = saves.getInt("MB",1);
        if (level < 4) {
            if (myResources.getWood() >= level * 500) {
                myBuildings.upgradeMainBuilding();
                SharedPreferences.Editor editor = saves.edit();
                editor.putInt("Wood", myResources.spendWood(level * 500));
                editor.putInt("MB", myBuildings.getMainBuilding());
                editor.commit();
                mbLevel.setText("Level " + saves.getInt("MB", 1));
                currWood.setText("Wood " + saves.getInt("Wood", 0));

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
        SharedPreferences saves = getSharedPreferences(RESOURCE_NAME, 0);

        SharedPreferences.Editor editor = saves.edit();
        editor.putInt("Wood", myResources.collectWood());
        editor.commit();
        currWood.setText("Wood " + saves.getInt("Wood", 0));
    }
    public void collectStone(View view) {
        SharedPreferences saves = getSharedPreferences(RESOURCE_NAME, 0);
        SharedPreferences.Editor editor = saves.edit();
        editor.putInt("Stone", myResources.collectStone());
        editor.commit();
        currStone.setText("Stone " + saves.getInt("Stone", 0));
    }
    public void collectMetal(View view) {
        SharedPreferences saves = getSharedPreferences(RESOURCE_NAME, 0);
        SharedPreferences.Editor editor = saves.edit();
        editor.putInt("Metal", myResources.collectMetal());
        editor.commit();
        currMetal.setText("Metal " + saves.getInt("Metal", 0));
    }
}
