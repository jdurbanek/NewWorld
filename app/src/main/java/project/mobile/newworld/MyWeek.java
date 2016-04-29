package project.mobile.newworld;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MyWeek extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_week);



        getFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment_container, CurrentWeek.newInstance(null, null))
                .addToBackStack(null)
                .commit();
    }
}
