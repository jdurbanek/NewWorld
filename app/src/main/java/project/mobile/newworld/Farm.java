package project.mobile.newworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Farm extends AppCompatActivity {

    Buildings myBuildings = new Buildings();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm);
    }

    public void levelFarm(View view) {
        myBuildings.upgradeFarm();
    }
}
