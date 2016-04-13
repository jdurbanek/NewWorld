package project.mobile.newworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Base extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }
    public void launchHomeScreen(View view) {
        Intent intent = new Intent(this, OptionsScreen.class);
        startActivity(intent);
    }
}
