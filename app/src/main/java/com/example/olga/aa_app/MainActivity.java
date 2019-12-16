package com.example.olga.aa_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.olga.aa_app.database.entities.Allergy;
import com.example.olga.aa_app.database.entities.BrandName;
import com.example.olga.aa_app.database.viewmodels.AllergyViewModel;
import com.example.olga.aa_app.database.viewmodels.BrandNameViewModel;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
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

        Single<Allergy> test = allergyViewModel.getAllergyByName("bruh2");
        test.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<Allergy>() {
                    @Override
                    public void onSuccess(Allergy allergy) {
                        System.out.println("shoutout");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("oof");
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
