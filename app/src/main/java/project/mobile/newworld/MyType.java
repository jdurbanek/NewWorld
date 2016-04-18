package project.mobile.newworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MyType extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_type);
    }

    public void walking(View view){
        Intent intent = new Intent();
        intent.putExtra("Type", 2);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void running(View view){
        Intent intent = new Intent();
        intent.putExtra("Type", 1);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void biking(View view){
        Intent intent = new Intent();
        intent.putExtra("Type", 3);
        setResult(RESULT_OK, intent);
        finish();
    }
}
