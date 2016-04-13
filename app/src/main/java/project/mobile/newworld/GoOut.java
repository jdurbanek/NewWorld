package project.mobile.newworld;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class GoOut extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go_out);

        MapFragment frg = new MapFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.map_frag_container, frg, "Map");
        transaction.commit();

    }

    public void launchCurrentWeek(View view){
        Intent intent = new Intent(this, MyWeek.class);
        startActivity(intent);
    }
}
