package project.mobile.newworld;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainBuilding extends AppCompatActivity {


    public static final String RESOURCE_NAME = "Resource";
    private TextView mbLevel;
    private TextView currWood;
    private TextView currStone;
    private TextView currMetal;
    private ImageView backG;

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
        backG = (ImageView) findViewById(R.id.mainBuilding);

        SharedPreferences saves = getSharedPreferences("Resource", 0);
        //remove next 3 lines after testing is over
        SharedPreferences.Editor editor = saves.edit();
        editor.putInt("MB", 1);
        editor.putInt("Wood",0);
        editor.putInt("Stone",0);
        editor.putInt("Metal",0);
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
        if (level <= 3) {
            if (saves.getInt("Wood",0) >= level * 500) {
                SharedPreferences.Editor editor = saves.edit();
                editor.putInt("Wood", myResources.spendWood(level * 500));

                myBuildings.upgradeMainBuilding();
                editor.putInt("MB", myBuildings.getMainBuilding());
                editor.commit();

                if(saves.getInt("MB",1)==4){
                    backG.setBackgroundResource(R.drawable.sbdud1);
                }

                mbLevel.setText("Level " + saves.getInt("MB", 1));
                currWood.setText("Wood " + saves.getInt("Wood", 0));


            } else {
                Toast toast = Toast.makeText(getApplicationContext(), "not enough resources", Toast.LENGTH_LONG);
                toast.show();

            }
        } else if ((level >= 4) && (level <= 6)) {
            if ((saves.getInt("Wood",0) >= (level * 1000)) &&
                    ((saves.getInt("Stone",0))) >= (level * 250)) {

                SharedPreferences.Editor editor = saves.edit();
                editor.putInt("Wood", myResources.spendWood(level * 1000));
                editor.putInt("Stone", myResources.spendStone(level * 250));

                myBuildings.upgradeMainBuilding();
                editor.putInt("MB", myBuildings.getMainBuilding());
                editor.commit();

                if(saves.getInt("MB",1)==7){
                    backG.setBackgroundResource(R.drawable.mbdud1);
                }

                mbLevel.setText("Level " + saves.getInt("MB", 1));
                currWood.setText("Wood " + saves.getInt("Wood", 0));
                currStone.setText("Stone " + saves.getInt("Stone", 0));
            } else {
                Toast toast = Toast.makeText(getApplicationContext(), "not enough resources", Toast.LENGTH_LONG);
                toast.show();
            }
        } else if (level >= 7 && level < 10) {
            if ((saves.getInt("Wood",0) >= (level * 1500)) &&
                    (saves.getInt("Stone",0) >= (level * 500)) &&
                    (saves.getInt("Metal",0) >= (level * 100))) {

                SharedPreferences.Editor editor = saves.edit();
                editor.putInt("Wood", myResources.spendWood(level * 1500));
                editor.putInt("Stone", myResources.spendStone(level * 500));
                editor.putInt("Metal", myResources.spendMetal(level * 100));

                myBuildings.upgradeMainBuilding();
                editor.putInt("MB", myBuildings.getMainBuilding());
                editor.commit();

                mbLevel.setText("Level " + saves.getInt("MB", 1));
                currWood.setText("Wood " + saves.getInt("Wood", 0));
                currStone.setText("Stone " + saves.getInt("Stone", 0));
                currMetal.setText("Metal " + saves.getInt("Metal", 0));

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
