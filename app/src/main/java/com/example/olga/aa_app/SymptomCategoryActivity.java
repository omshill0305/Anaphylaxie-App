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
import android.widget.TableLayout;
import android.widget.TableRow;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.olga.aa_app.utility.Utility;

import java.util.ArrayList;

/**
 * Selection of symptoms of a category
 * <p>
 * The header is set in setup and the content of the activity is created dynamically in onCreate.
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
        setup(category);

        TypedArray names = getSymptomResources(category);
        TypedArray descriptions = getDescriptionResources(category);

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
                info_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(
                            SymptomCategoryActivity.this,
                            R.style.AppTheme_Alert
                        );
                        builder.setTitle(checkBox.getText())
                            .setMessage(description)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int i) {

                                }
                            });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                });
            } else {
                ImageButton info_button = (ImageButton) row.getChildAt(1);
                info_button.setEnabled(false);
                info_button.setAlpha(0.5f);
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

    private void setup(final String category) {
        Button choose = findViewById(R.id.choose);
        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Reaction reaction = getCurrentReaction(category);
                if (reaction.isEmpty() || Profile.currentProfile == null) {
                    Utility.showToast(SymptomCategoryActivity.this, getString(R.string.min_symptom));
                } else {
                    chooseActivity(Algorithm.evaluate(reaction));
                }
            }
        });
        BaseView container = findViewById(R.id.content);
        switch (category) {
            case SymptomsActivity.CATEGORY_AIRWAYS:
                container.setIcon(R.drawable.lung);
                container.setTitle(getString(R.string.airways));
                break;
            case SymptomsActivity.CATEGORY_CARDIOVASCULAR:
                container.setIcon(R.drawable.ic_cardiogram);
                container.setTitle(getString(R.string.cardiovascular));
                break;
            case SymptomsActivity.CATEGORY_GASTRO_INTESTINAL:
                container.setIcon(R.drawable.stomach);
                container.setTitle(getString(R.string.gastro_intestinal));
                break;
            case SymptomsActivity.CATEGORY_SKIN:
                container.setIcon(R.drawable.ic_head);
                container.setTitle(getString(R.string.skin));
                break;
            case SymptomsActivity.CATEGORY_DIZZINESS:
                container.setIcon(R.drawable.headache);
                container.setTitle(getString(R.string.dizziness));
                break;
            case SymptomsActivity.CATEGORY_RUNNY_NOSE:
                container.setIcon(R.drawable.runny);
                container.setTitle(getString(R.string.runny_nose));
                break;
            case SymptomsActivity.CATEGORY_INDEFINABLE_DREAD:
                container.setIcon(R.drawable.ghost);
                container.setTitle(getString(R.string.panic));
                break;
            default:
                break;
        }
    }

    private Reaction getCurrentReaction(String category) {
        Reaction reaction;
        Profile profile = Profile.currentProfile;

        if (profile != null) {
            reaction = profile.getCurrentReaction();
        } else {
            reaction = new Reaction();
        }

        ArrayList<Integer> symptoms = getSelectedSymptoms(category);
        for (Integer symptom : symptoms) {
            reaction.addSymptom(new Symptom(symptom));
        }

        return reaction;
    }

    /**
     * Searches for checked checkboxes and returns their mapped resource identifier.
     *
     * @return Returns string resource identifiers of selected checkboxes.
     */
    private ArrayList<Integer> getSelectedSymptoms(String category) {
        TypedArray names = getSymptomResources(category);
        if (names == null || names.length() == 0) {
            return new ArrayList<>();
        }

        TableLayout root = findViewById(R.id.listing_layout);
        int len = Math.min(names.length(), root.getChildCount());
        ArrayList<Integer> selection = new ArrayList<>(len);

        for (int i = 0; i < len; i++) {
            TableRow row = (TableRow) root.getChildAt(i);
            CheckBox checkBox = (CheckBox) row.getChildAt(0);
            if (checkBox.isChecked()) {
                selection.add(names.getResourceId(i, 0));
            }
        }
        names.recycle();
        return selection;
    }

    private void chooseActivity(String evaluatedAlgorithm) {
        Intent intent;
        if (evaluatedAlgorithm.equalsIgnoreCase(Algorithm.ALGORITHM_1)) {
            intent = new Intent(SymptomCategoryActivity.this, TreatmentGreenActivity.class);
        } else {
            intent = new Intent(SymptomCategoryActivity.this, TreatmentRedActivity.class);
        }

        intent.putExtra("evaluatedAlgorithm", evaluatedAlgorithm);
        startActivity(intent);
    }

    private TypedArray getSymptomResources(String category) {
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

    private TypedArray getDescriptionResources(String category) {
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
