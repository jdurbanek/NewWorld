package project.mobile.newworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class OptionsScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options_screen);


    }

    public void base(View view){
        Intent intent = new Intent(this, Base.class);
        startActivity(intent);
    }

    public void out(View view){
        Intent intent = new Intent(this, GoOut.class);
        startActivity(intent);
    }

    public void battle(View view){
        Intent intent = new Intent(this, Battle.class);
        startActivity(intent);
    }

    public void settings(View view){
        Intent intent = new Intent(this, MySettings.class);
        startActivity(intent);
    }
}
