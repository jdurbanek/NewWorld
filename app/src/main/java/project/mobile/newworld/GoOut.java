package project.mobile.newworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class GoOut extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go_out);


    }

    public void launchCurrentWeek(View view){
        Intent intent = new Intent(this, MyWeek.class);
        startActivity(intent);
    }
}
