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
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

/**
 * Selection of symptoms of a category
 */
public class SymptomCategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptom_category);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setLogo(R.drawable.logo1);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }

        Intent intent = getIntent();
        String category = intent.getStringExtra(SymptomsActivity.SELECTED_CATEGORY);

        HashMap<String, String> des = setup(category);

        if (des == null) {
            return;
        }

        final TableLayout root = findViewById(R.id.listing_layout);
        final LayoutInflater inflater = getLayoutInflater();

        for (final Map.Entry<String, String> entry : des.entrySet()) {
            TableRow row = (TableRow) inflater.inflate(R.layout.selection_button, root, false);
            final CheckBox checkBox = (CheckBox) row.getChildAt(0);
            checkBox.setText(entry.getKey());

            if (entry.getValue() != null) {
                ImageButton info_button = (ImageButton) row.getChildAt(1);
                info_button.setBackground(getDrawable(R.color.colorProjektLightGreen));
                info_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(SymptomCategoryActivity.this, R.style.MyAlertDialogStyleInfo);
                        builder.setTitle(checkBox.getText());

                        LinearLayout linear_layout = (LinearLayout) inflater.inflate(R.layout.custom_layout_red, root, false);
                        TextView textView = (TextView) linear_layout.getChildAt(0);
                        textView.setText(entry.getValue());
                        builder.setView(linear_layout);

                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {

                            }
                        });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                });
            }
            root.addView(row);
        }
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

    private HashMap<String, String> setup(String category) {
        BaseView container = findViewById(R.id.content);
        Button choose = findViewById(R.id.choose);
        switch (category) {
            case SymptomsActivity.CATEGORY_AIRWAYS:
                container.setIcon(R.drawable.lung);
                container.setTitle(getString(R.string.airways));
                choose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(SymptomCategoryActivity.this, TreatmentRedActivity.class));
                    }
                });
                return getAirwaysDescription();
            case SymptomsActivity.CATEGORY_CARDIOVASCULAR:
                container.setIcon(R.drawable.cardiogram);
                container.setTitle(getString(R.string.cardiovascular));
                choose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(SymptomCategoryActivity.this, TreatmentRedActivity.class));
                    }
                });
                return getCardiovascularDescription();
            case SymptomsActivity.CATEGORY_GASTRO_INTESTINAL:
                container.setIcon(R.drawable.stomach);
                container.setTitle(getString(R.string.gastro_intestinal));
                choose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(SymptomCategoryActivity.this, TreatmentRedActivity.class));
                    }
                });
                return getGastroIntestinalDescription();
            case SymptomsActivity.CATEGORY_SKIN:
                container.setIcon(R.drawable.ic_head);
                container.setTitle(getString(R.string.skin));
                choose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(SymptomCategoryActivity.this, TreatmentGreenActivity.class));
                    }
                });
                return getSkinDescription();
            default:
                return null;
        }
    }

    private HashMap<String, String> getAirwaysDescription() {
        HashMap<String, String> des = new HashMap<>(4);
        des.put(getString(R.string.difficulty_in_breathing), getString(R.string.info_difficulty_in_breathing));
        des.put(getString(R.string.hoarseness), null);
        des.put(getString(R.string.wheezing), getString(R.string.info_wheezing));
        des.put(getString(R.string.cough), null);
        return des;
    }

    private HashMap<String, String> getCardiovascularDescription() {
        HashMap<String, String> des = new HashMap<>(2);
        des.put(getString(R.string.blood_pressure_drop), getString(R.string.info_blood_pressure_drop));
        des.put(getString(R.string.unconsciousness), null);
        return des;
    }

    private HashMap<String, String> getGastroIntestinalDescription() {
        HashMap<String, String> des = new HashMap<>(5);
        des.put(getString(R.string.diarrhea), getString(R.string.info_diarrhea));
        des.put(getString(R.string.abdominal_pain), getString(R.string.info_abdominal_pain));
        des.put(getString(R.string.nausea), getString(R.string.info_nausea));
        des.put(getString(R.string.tingling_mouth_throat), getString(R.string.info_tingling));
        des.put(getString(R.string.vomiting), getString(R.string.info_vomiting));
        return des;
    }

    private HashMap<String, String> getSkinDescription() {
        HashMap<String, String> des = new HashMap<>(3);
        des.put(getString(R.string.wheals), getString(R.string.info_wheals));
        des.put(getString(R.string.swollen_lip_face), getString(R.string.info_swollen_lip));
        des.put(getString(R.string.pruritus), getString(R.string.info_pruritus));
        return des;
    }
}
