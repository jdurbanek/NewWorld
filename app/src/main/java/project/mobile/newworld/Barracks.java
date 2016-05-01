package project.mobile.newworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Barracks extends AppCompatActivity {

    Resource myResources = new Resource();
    Buildings myBuildings = new Buildings();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barracks);
    }

    public void levelBarracks(View view){
        myBuildings.upgradeBarracks();
    }
}