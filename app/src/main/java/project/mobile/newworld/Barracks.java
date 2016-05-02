package project.mobile.newworld;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Barracks extends AppCompatActivity {


    public static final String RESOURCE_NAME = "Resource";
    private TextView bLevel;
    private TextView currWood;
    private TextView currStone;
    private TextView currMetal;

    Resource myResources = new Resource();
    Buildings myBuildings = new Buildings();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barracks);

        bLevel = (TextView) findViewById(R.id.mbLevel);
        currWood = (TextView) findViewById(R.id.currWood);
        currStone = (TextView) findViewById(R.id.currStone);
        currMetal = (TextView) findViewById(R.id.currMetal);

        SharedPreferences savedResourses = getSharedPreferences("Resource", 0);
        int cWood = savedResourses.getInt("Wood", 0);
        int cStone = savedResourses.getInt("Stone", 0);
        int cMetal = savedResourses.getInt("Metal",0);

        myResources.setWood(cWood);
        myResources.setStone(cStone);
        myResources.setMetal(cMetal);
        currWood.setText("Wood " + savedResourses.getInt("Wood", 0));
        currStone.setText("Stone " + savedResourses.getInt("Stone", 0));
        currMetal.setText("Metal " + savedResourses.getInt("Metal", 0));

    }

    public void levelBarracks(View view){
        myBuildings.upgradeBarracks();
    }
}