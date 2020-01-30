package com.example.olga.aa_app;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import android.widget.TextView;


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

        LinearLayout instructions = findViewById(R.id.instructions);

        LayoutInflater inflater = getLayoutInflater();
        TextView stay_calm = (TextView) inflater.inflate(R.layout.instruction, instructions, false);
        stay_calm.setText(R.string.stay_calm);
        TextView medicines = (TextView) inflater.inflate(R.layout.instruction, instructions, false);
        medicines.setText(getString(R.string.treatment_green_medicine,
                "Dosierung",
                "Antihistaminikum",
                "Dosierung",
                "Steroid"
        ));
        TextView after_treatment = (TextView) inflater.inflate(R.layout.instruction, instructions, false);
        after_treatment.setText(R.string.after_treatment);

        instructions.addView(stay_calm);
        instructions.addView(medicines);
        instructions.addView(after_treatment);

        showAddItemDialog1(null);

        TextView selection = findViewById(R.id.selection);
        selection.setText(R.string.wheals);

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
