package com.example.olga.aa_app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.olga.aa_app.database.entities.Allergy;
import com.example.olga.aa_app.database.viewmodels.AllergyViewModel;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        AllergyViewModel allergyViewModel = ViewModelProviders.of(this).get(AllergyViewModel.class);

        Allergy allergy = new Allergy("bruh2");
        allergyViewModel.insert(allergy)
                .subscribe(new DisposableCompletableObserver() {
                @Override
                public void onComplete() {
                    System.out.println("it worked");
                }

                @Override
                public void onError(Throwable e) {
                    System.out.println("something went wrong");
                }
        });


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
