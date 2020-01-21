package com.example.olga.aa_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class SymptomsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptoms);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setLogo(R.drawable.logo1);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }

        Button skin = findViewById(R.id.skin);
        skin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SymptomsActivity.this, SkinActivity.class));
            }
        });

        Button airways = findViewById(R.id.airways);
        airways.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SymptomsActivity.this, AirwaysActivity.class));
            }
        });

        Button gastro = findViewById(R.id.gastro_intestinal);
        gastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SymptomsActivity.this, GastroIntestinalActivity.class));
            }
        });

        Button cardiovascular = findViewById(R.id.cardiovascular);
        cardiovascular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SymptomsActivity.this, CardiovascularActivity.class));
            }
        });

        Button dizziness = findViewById(R.id.dizziness);
        dizziness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SymptomsActivity.this, DizzinessActivity.class));
            }
        });

        Button indefinableDread = findViewById(R.id.panic);
        indefinableDread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SymptomsActivity.this, IndefinableDreadActivity.class));
            }
        });

        Button runny_nose = findViewById(R.id.runny_nose);
        runny_nose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SymptomsActivity.this, TreatmentRedActivity.class));
            }
        });

        Button no_idea = findViewById(R.id.no_idea);
        no_idea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SymptomsActivity.this, TreatmentRedActivity.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_extra, menu);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (getFragmentManager().getBackStackEntryCount() == 0) {
            super.onBackPressed();
        } else if (getFragmentManager().getBackStackEntryCount() == 1) {
            moveTaskToBack(false);
        } else {
            getFragmentManager().popBackStack();
        }
        return true;
    }
}

