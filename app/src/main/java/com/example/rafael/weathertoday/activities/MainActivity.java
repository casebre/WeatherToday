package com.example.rafael.weathertoday.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.rafael.weathertoday.fragments.ForecastFragment;
import com.example.rafael.weathertoday.R;

public class MainActivity extends AppCompatActivity {

    private Button btnForecast;
    private TextView city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnForecast = (Button) findViewById(R.id.button_forecast);
        city = (TextView) findViewById(R.id.edit_city);

        btnForecast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_forecast,
                            ForecastFragment.newInstance(city.getText().toString(), "")).commit();
                }
            }
        });

    }

    public boolean validate() {
        boolean validated = true;

        if(city.length() <= 3) {
            city.setError("More than 3 chars");
            validated = false;
        }

        return  validated;
    }




}
