package project.mobile.newworld;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainBuilding extends AppCompatActivity {

    private TextView mbLevel;
    private TextView currWood;
    private TextView currStone;
    private TextView currMetal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_building);


        mbLevel = (TextView)findViewById(R.id.mbLevel);
        currWood = (TextView)findViewById(R.id.currWood);
        currStone = (TextView)findViewById(R.id.currStone);
        currMetal = (TextView)findViewById(R.id.currMetal);

        SharedPreferences settings = getSharedPreferences("Resource", 0);
        currWood.setText("Wood " + settings.getInt("Wood", 0));
        currStone.setText("Stone " + settings.getInt("Stone", 0));
        currMetal.setText("Metal " + settings.getInt("Metal", 0));

        mbLevel.setText("Level "+ getMainBuildingLevel());

    }

    public void goToBase(View view) {
        Intent intent = new Intent(this, Base.class);
        startActivity(intent);
    }


    public int getMainBuildingLevel(){
        SharedPreferences settings = getSharedPreferences("Resource", 0);
        int currLevel = settings.getInt("MB", 1);
        return currLevel;
    }
}
