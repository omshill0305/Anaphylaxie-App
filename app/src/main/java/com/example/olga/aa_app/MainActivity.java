package com.example.olga.aa_app;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import com.example.olga.aa_app.database.viewmodels.AllergyViewModel;
import com.facebook.stetho.Stetho;


public class MainActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 3000;

    AllergyViewModel allergyViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        Stetho.initializeWithDefaults(this);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent homeIntent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(homeIntent);
                finish();
            }
        }, SPLASH_TIME_OUT);

    }
}
