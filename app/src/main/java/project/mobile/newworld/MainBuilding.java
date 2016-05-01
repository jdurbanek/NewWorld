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

    private TextView mbLevel;
    private TextView currWood;
    private TextView currStone;
    private TextView currMetal;

    public static final String RESOURCE_NAME = "Resource";
    Resource myResources = new Resource();
    Buildings myBuildings = new Buildings();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_building);


        mbLevel = (TextView) findViewById(R.id.mbLevel);
        currWood = (TextView) findViewById(R.id.currWood);
        currStone = (TextView) findViewById(R.id.currStone);
        currMetal = (TextView) findViewById(R.id.currMetal);

        SharedPreferences settings = getSharedPreferences("Resource", 0);
        currWood.setText("Wood " + settings.getInt("Wood", 0));
        currStone.setText("Stone " + settings.getInt("Stone", 0));
        currMetal.setText("Metal " + settings.getInt("Metal", 0));

        mbLevel.setText("Level " + getMainBuildingLevel());

    }


    public int getMainBuildingLevel() {
        SharedPreferences settings = getSharedPreferences("Resource", 0);
        int currLevel = settings.getInt("MB", 1);
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
    }
    public void collectStone(View view) {
        SharedPreferences resources = getSharedPreferences(RESOURCE_NAME, 0);
        SharedPreferences.Editor editor = resources.edit();
        editor.putInt("Stone", myResources.collectStone());
        editor.commit();
    }
    public void collectMetal(View view) {
        SharedPreferences resources = getSharedPreferences(RESOURCE_NAME, 0);
        SharedPreferences.Editor editor = resources.edit();
        editor.putInt("Metal", myResources.collectMetal());
        editor.commit();
    }
}
