package com.example.olga.aa_app;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
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

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

/**
 * Selection of symptoms of a category
 */
public class SymptomCategoryActivity extends AppCompatActivity {

    String category = "";

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
        category = intent.getStringExtra(SymptomsActivity.SELECTED_CATEGORY);

        setup(category);

        TypedArray names = getSymptomResources();
        TypedArray descriptions = getDescriptionResources();

        if (names == null || descriptions == null || names.length() != descriptions.length()) {
            return;
        }

        final TableLayout root = findViewById(R.id.listing_layout);
        final LayoutInflater inflater = getLayoutInflater();

        for (int i = 0; i < names.length(); i++) {
            TableRow row = (TableRow) inflater.inflate(R.layout.selection_button, root, false);
            final CheckBox checkBox = (CheckBox) row.getChildAt(0);

            if (names.getResourceId(i, 0) != 0) {
                String name = names.getString(i);
                checkBox.setText(name);
            }

            if (descriptions.getResourceId(i, 0) != 0) {
                final String description = descriptions.getString(i);
                ImageButton info_button = (ImageButton) row.getChildAt(1);
                info_button.setBackground(getDrawable(R.color.colorProjektLightGreen));
                info_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(SymptomCategoryActivity.this,
                            R.style.MyAlertDialogStyleInfo
                        );
                        builder.setTitle(checkBox.getText());

                        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.custom_layout_red, root, false);
                        TextView textView = (TextView) layout.getChildAt(0);
                        textView.setText(description);
                        builder.setView(layout);

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
            else {
                ImageButton info_button = (ImageButton) row.getChildAt(1);
                info_button.setEnabled(false);
            }
            root.addView(row);
        }
        names.recycle();
        descriptions.recycle();
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

    private void setup(String symptomCategory) {
        category = symptomCategory;
        Button choose = findViewById(R.id.choose);
        BaseView container = findViewById(R.id.content);
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
                break;
            case SymptomsActivity.CATEGORY_CARDIOVASCULAR:
                container.setIcon(R.drawable.cardiogram);
                container.setTitle(getString(R.string.cardiovascular));
                choose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(SymptomCategoryActivity.this, TreatmentRedActivity.class));
                    }
                });
                break;
            case SymptomsActivity.CATEGORY_GASTRO_INTESTINAL:
                container.setIcon(R.drawable.stomach);
                container.setTitle(getString(R.string.gastro_intestinal));
                choose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(SymptomCategoryActivity.this, TreatmentRedActivity.class));
                    }
                });
                break;
            case SymptomsActivity.CATEGORY_SKIN:
                container.setIcon(R.drawable.ic_head);
                container.setTitle(getString(R.string.skin));
                choose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(SymptomCategoryActivity.this, TreatmentGreenActivity.class));
                    }
                });
                break;
            case SymptomsActivity.CATEGORY_DIZZINESS:
                container.setIcon(R.drawable.headache);
                container.setTitle("Dizziness");
                choose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(SymptomCategoryActivity.this, TreatmentGreenActivity.class));
                    }
                });
                break;
            case SymptomsActivity.CATEGORY_RUNNY_NOSE:
                container.setIcon(R.drawable.runny);
                container.setTitle("Runny Nose");
                choose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(SymptomCategoryActivity.this, TreatmentGreenActivity.class));
                    }
                });
                break;
            case SymptomsActivity.CATEGORY_INDEFINABLE_DREAD:
                container.setIcon(R.drawable.ghost);
                container.setTitle("Indefinable Dread");
                choose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(SymptomCategoryActivity.this, TreatmentGreenActivity.class));
                    }
                });
                break;
            default:
                break;
        }
    }

    /**
     * TODO: Change Symptom, Reaction and Algorithm class to use identifiers.
     * @return Returns string resource identifiers of selected checkboxes.
     */
    private ArrayList<Integer> getSelectedSymptoms() {
        TypedArray names = getSymptomResources();
        if (names == null || names.length() == 0) {
            return new ArrayList<>();
        }

        TableLayout root = findViewById(R.id.listing_layout);
        int len = Math.min(names.length(), root.getChildCount());
        ArrayList<Integer> selection = new ArrayList<>(len);

        for (int i = 0; i < len; i++) {
            TableRow row = (TableRow) root.getChildAt(i);
            CheckBox checkBox = (CheckBox) row.getChildAt(0);
            if (checkBox.isSelected()) {
                selection.add(names.getResourceId(i, 0));
            }
        }
        names.recycle();
        return selection;
    }

    private TypedArray getSymptomResources() {
        Resources resources = getResources();
        switch (category) {
            case SymptomsActivity.CATEGORY_AIRWAYS:
                return resources.obtainTypedArray(R.array.airways_symptoms);
            case SymptomsActivity.CATEGORY_CARDIOVASCULAR:
                return resources.obtainTypedArray(R.array.cardiovascular_symptoms);
            case SymptomsActivity.CATEGORY_GASTRO_INTESTINAL:
                return resources.obtainTypedArray(R.array.gastro_intestinal_symptoms);
            case SymptomsActivity.CATEGORY_SKIN:
                return resources.obtainTypedArray(R.array.skin_symptoms);
            case SymptomsActivity.CATEGORY_DIZZINESS:
                return resources.obtainTypedArray(R.array.dizziness_symptoms);
            case SymptomsActivity.CATEGORY_RUNNY_NOSE:
                return resources.obtainTypedArray(R.array.runny_nose_symptoms);
            case SymptomsActivity.CATEGORY_INDEFINABLE_DREAD:
                return resources.obtainTypedArray(R.array.indefinable_dread_symptoms);
            default:
                return null;
        }
    }

    private TypedArray getDescriptionResources() {
        Resources resources = getResources();
        switch (category) {
            case SymptomsActivity.CATEGORY_AIRWAYS:
                return resources.obtainTypedArray(R.array.airways_descriptions);
            case SymptomsActivity.CATEGORY_CARDIOVASCULAR:
                return resources.obtainTypedArray(R.array.cardiovascular_descriptions);
            case SymptomsActivity.CATEGORY_GASTRO_INTESTINAL:
                return resources.obtainTypedArray(R.array.gastro_intestinal_descriptions);
            case SymptomsActivity.CATEGORY_SKIN:
                return resources.obtainTypedArray(R.array.skin_descriptions);
            case SymptomsActivity.CATEGORY_DIZZINESS:
                return resources.obtainTypedArray(R.array.dizziness_description);
            case SymptomsActivity.CATEGORY_RUNNY_NOSE:
                return resources.obtainTypedArray(R.array.runny_nose_description);
            case SymptomsActivity.CATEGORY_INDEFINABLE_DREAD:
                return resources.obtainTypedArray(R.array.indefinable_dread_description);
            default:
                return null;
        }
    }
}
