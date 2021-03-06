package com.example.olga.aa_app;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class TreatmentRedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treatment_red);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setLogo(R.drawable.logo1);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }

        TextView medicine = findViewById(R.id.medicine);
        if (Profile.currentProfile != null) {
            medicine.setText(getString(R.string.treatment_red_medicine, Profile.currentProfile.getAutoinjector()));
        } else {
            medicine.setText(getString(R.string.treatment_red_medicine, ""));
        }

        TextView selection = findViewById(R.id.selection);
        StringBuilder selectedReactions = new StringBuilder();
        if (Profile.currentProfile != null) {
            for (Symptom symptom : Profile.currentProfile.getCurrentReaction().getSymptoms()) {
                selectedReactions.append(getString(symptom.getName())).append(", ");
            }
        }
        if (selectedReactions.length() > 2) {
            selection.setText(selectedReactions.substring(0, selectedReactions.length() - 2));
        }

        Button back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TreatmentRedActivity.this, SymptomsActivity.class));
            }
        });

        startStepByStepInstructions();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_extra, menu);
        return true;
    }

    @Override
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

    private void startStepByStepInstructions() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AppTheme_Emergency_Alert);
        builder.setTitle(getString(R.string.step, 1))
            .setMessage("Es bestehen Anzeichen für eine schwere Reaktion")
            .setPositiveButton(R.string.continue_, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int i) {
                    showMedicine();
                }
            });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showMedicine() {
        String autoinjector = "";
        if (Profile.currentProfile != null) {
            autoinjector = Profile.currentProfile.getAutoinjector();
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AppTheme_Emergency_Alert);
        builder.setTitle(getString(R.string.step, 2))
            .setMessage(getString(R.string.treatment_red_medicine, autoinjector))
            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int i) {

                }
            });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
