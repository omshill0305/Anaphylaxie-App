package com.example.olga.aa_app;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.olga.aa_app.utility.TagSpan;

public class TreatmentGreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treatment_green);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setLogo(R.drawable.logo1);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }

        TextView medicines = findViewById(R.id.medicine);
        TextView standby = findViewById(R.id.standby);

        if (Profile.currentProfile != null) {
            medicines.setText(getString(
                R.string.treatment_green_medicine,
                Profile.currentProfile.getAntihistamineDosage(),
                Profile.currentProfile.getAntihistamine(),
                Profile.currentProfile.getSteroidDosage(),
                Profile.currentProfile.getSteroid()
            ));
            standby.setText(getString(R.string.after_treatment_green, Profile.currentProfile.getAutoinjector()));
        } else {
            medicines.setText(getString(R.string.treatment_green_medicine, "", "", "", ""));
            standby.setText(getString(R.string.after_treatment_green, ""));
        }

        TextView selection = findViewById(R.id.selection);
        StringBuilder selectedReactions = new StringBuilder();
        if (Profile.currentProfile != null) {
            for (Symptom symptom: Profile.currentProfile.getCurrentReaction().getSymptoms()) {
                selectedReactions.append(getString(symptom.getName())).append(", ");
            }
        }
        if (selectedReactions.length() > 2) {
            selection.setText(selectedReactions.substring(0, selectedReactions.length() - 2));
        }

        showAddItemDialog1(null);

        Button back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TreatmentGreenActivity.this, SymptomsActivity.class));
            }
        });
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

    private void showAddItemDialog2(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.MyAlertDialogStyle);
        builder.setTitle("Schritt 2");

        final View customLayout = getLayoutInflater().inflate(R.layout.custom_layout2, null);
        builder.setView(customLayout);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showAddItemDialog1(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.MyAlertDialogStyle);
        builder.setTitle("Schritt 1");

        final View customLayout = getLayoutInflater().inflate(R.layout.custom_layout, null);
        builder.setView(customLayout);

        builder.setPositiveButton("Weiter", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                showAddItemDialog2(null);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
