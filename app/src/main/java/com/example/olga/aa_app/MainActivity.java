package com.example.olga.aa_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.olga.aa_app.database.entities.BrandName;
import com.example.olga.aa_app.database.viewmodels.AllergyViewModel;
import com.example.olga.aa_app.database.viewmodels.BrandNameViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        AllergyViewModel allergyViewModel = ViewModelProviders.of(this).get(AllergyViewModel.class);
        BrandNameViewModel brandNameViewModel = ViewModelProviders.of(this).get(BrandNameViewModel.class);

        // Probably does not work because it is not synchronized
        BrandName b = brandNameViewModel.getBrandNameByName("FromSoftware");

        System.out.println(b.getName());

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(homeIntent);
                finish();
            }
        }, SPLASH_TIME_OUT);


    }
}
