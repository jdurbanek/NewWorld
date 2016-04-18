package project.mobile.newworld;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.lang.reflect.Type;

public class GoOut extends AppCompatActivity {

    int myType = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go_out);

       // TypeFragment frg = new TypeFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        //start the instance with 0 to indicate an idle activity.
        transaction.add(R.id.map_frag_container, TypeFragment.newInstance(myType), "Type");
        transaction.commit();

    }

    public void launchCurrentWeek(View view){
        Intent intent = new Intent(this, MyWeek.class);
        startActivity(intent);
    }

    public void selectActivity(View view) {
        Intent intent = new Intent(this, MyType.class);
        startActivityForResult(intent, 0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1) {
            if(resultCode == RESULT_OK){
                myType = data.getIntExtra("Type", 0);
            }
        }

    }
}
