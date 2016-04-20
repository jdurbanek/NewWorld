package project.mobile.newworld;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.lang.reflect.Type;

public class GoOut extends AppCompatActivity {

    int myType = 0;
    TextView myText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go_out);

        myText = (TextView) findViewById(R.id.activityStatus);
        if(myType == 0) {
            myText.setText("Idle");
        }else if(myType == 1) {
            myText.setText("Running");
        }else if(myType == 2) {
            myText.setText("Walking");
        }else if(myType == 3){
            myText.setText("Biking");
        }
    }

    public void launchCurrentWeek(View view){
        Intent intent = new Intent(this, MyWeek.class);
        startActivity(intent);
    }

    public void selectActivity(View view) {
        Intent intent = new Intent(this, MyType.class);
        startActivityForResult(intent, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1) {
            if(resultCode == RESULT_OK){
                myType = data.getIntExtra("Type", 0);
                if(myType == 0) {
                    myText.setText("Idle");
                }else if(myType == 1) {
                    myText.setText("Running");
                }else if(myType == 2) {
                    myText.setText("Walking");
                }else if(myType == 3){
                    myText.setText("Biking");
                }
            }
        }

    }
}
