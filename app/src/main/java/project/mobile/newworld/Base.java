package project.mobile.newworld;

import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Base extends AppCompatActivity {

    private ImageView backMB;
    private ImageView backBarracks;
    private ImageView backMessHall;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        backMB = (ImageView) findViewById(R.id.MainBuilding);
        backBarracks = (ImageView) findViewById(R.id.barracks);
        backMessHall = (ImageView) findViewById(R.id.messhall);

        setImages();
    }


    public void setImages(){
        SharedPreferences saves = getSharedPreferences("Resource", 0);


        if(saves.getInt("Barracks",1)>=4 && saves.getInt("Barracks",1)<7){
            backBarracks.setBackgroundResource(R.drawable.stonebarracks);
        }
        if(saves.getInt("Barracks",1)>=7){
            backBarracks.setBackgroundResource(R.drawable.metalbarracks);
        }
        if(saves.getInt("MessHall",1)>=4 && saves.getInt("MessHall",1)<7){
            backMessHall.setBackgroundResource(R.drawable.stonemesshall);
        }
        if(saves.getInt("MessHall",1)>=7){
            backMessHall.setBackgroundResource(R.drawable.metalmesshall);
        }
        if(saves.getInt("MB",1)>=4 && saves.getInt("MB",1)<7){
            backMB.setBackgroundResource(R.drawable.sbdud1);
        }
        if(saves.getInt("MB",1)>=7){
            backMB.setBackgroundResource(R.drawable.mbdud1);
        }
    }

    public void goToMainBuilding(View view) {
        Intent intent = new Intent(this, MainBuilding.class);
        startActivity(intent);
    }
    public void goToBarracks(View view) {
        Intent intent = new Intent(this, Barracks.class);
        startActivity(intent);
    }
    public void goToFarm(View view) {
        Intent intent = new Intent(this, Farm.class);
        startActivity(intent);
    }
}