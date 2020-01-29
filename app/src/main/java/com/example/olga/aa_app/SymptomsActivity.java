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

    public static final String SELECTED_CATEGORY = "com.example.olga.aa_app.SELECTED_CATEGORY";
    public static final String CATEGORY_AIRWAYS = "CATEGORY_AIRWAYS";
    public static final String CATEGORY_CARDIOVASCULAR = "CATEGORY_CARDIOVASCULAR";
    public static final String CATEGORY_GASTRO_INTESTINAL = "CATEGORY_GASTRO_INTESTINAL";
    public static final String CATEGORY_SKIN = "CATEGORY_SKIN";
    public static final String CATEGORY_DIZZINESS = "CATEGORY_DIZZINESS";
    public static final String CATEGORY_RUNNY_NOSE = "CATEGORY_RUNNY_NOSE";
    public static final String CATEGORY_INDEFINABLE_DREAD = "CATEGORY_INDEFINABLE_DREAD";

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
        skin.setOnClickListener(categoryOnCLickListener(CATEGORY_SKIN));

        Button airways = findViewById(R.id.airways);
        airways.setOnClickListener(categoryOnCLickListener(CATEGORY_AIRWAYS));

        Button gastro = findViewById(R.id.gastro_intestinal);
        gastro.setOnClickListener(categoryOnCLickListener(CATEGORY_GASTRO_INTESTINAL));

        Button cardiovascular = findViewById(R.id.cardiovascular);
        cardiovascular.setOnClickListener(categoryOnCLickListener(CATEGORY_CARDIOVASCULAR));

        Button dizziness = findViewById(R.id.dizziness);
        dizziness.setOnClickListener(categoryOnCLickListener(CATEGORY_DIZZINESS));

        Button indefinableDread = findViewById(R.id.panic);
        indefinableDread.setOnClickListener(categoryOnCLickListener(CATEGORY_INDEFINABLE_DREAD));

        Button runny_nose = findViewById(R.id.runny_nose);
        runny_nose.setOnClickListener(categoryOnCLickListener(CATEGORY_RUNNY_NOSE));

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

    private View.OnClickListener categoryOnCLickListener(final String category) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SymptomsActivity.this, SymptomCategoryActivity.class);
                intent.putExtra(SELECTED_CATEGORY, category);
                startActivity(intent);
            }
        };
    }
}

