package com.example.olga.aa_app;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.olga.aa_app.utility.SpaceTokenizer;
import com.example.olga.aa_app.utility.TagSpan;
import com.example.olga.aa_app.utility.Utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

/**
 * Profile Form
 * <p>
 * Checks user input and saves the profile data if valid. The saved profile
 * can be viewed in the profile tab
 * (ProfileFragment).
 */
public class ProfileFormActivity extends AppCompatActivity {

    // Message to signal the main activity that profile data
    // was updated and/or saved.
    public static final String UPDATED_PROFILE = "com.example.olga.aa_app.UPDATED_PROFILE";

    private Profile profile = null;
    private DatePickerDialog birthdayPicker;
    private MultiAutoCompleteTextView multiComplete;
    private SpannableStringBuilder allergyTags = new SpannableStringBuilder();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_form);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }

        final TextView birthday = findViewById(R.id.birthday);

        final Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        birthdayPicker = new DatePickerDialog(ProfileFormActivity.this,
            R.style.BirthdayPickerStyle,
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int y, int m, int d) {
                    birthday.setText(Utility.fmtDate(y, m, d));
                    birthdayPicker.onSaveInstanceState();
                }
            },
            year,
            month,
            day
        );
        birthdayPicker.getDatePicker().setMaxDate(calendar.getTimeInMillis());
        birthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (savedInstanceState != null) {
                    birthdayPicker.onRestoreInstanceState(savedInstanceState);
                }
                birthdayPicker.show();
            }
        });

        multiComplete = findViewById(R.id.known_allergens);
        multiComplete.setAdapter(new ArrayAdapter<>(this,
            android.R.layout.simple_dropdown_item_1line,
            getResources().getStringArray(R.array.allergens)
        ));
        multiComplete.setTokenizer(new SpaceTokenizer());
        multiComplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int last = allergyTags.length();
                allergyTags.append(adapterView.getItemAtPosition(i).toString()).append(" ");
                allergyTags.setSpan(new TagSpan(ProfileFormActivity.this),
                    last,
                    allergyTags.length() - 1,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                );
                multiComplete.setText(allergyTags);
                multiComplete.setSelection(allergyTags.length());
            }
        });
        multiComplete.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (i == KeyEvent.KEYCODE_DEL) {
                    allergyTags.replace(0, allergyTags.length(), multiComplete.getText());
                }
                return false;
            }
        });

        Spinner autoinjector = findViewById(R.id.autoinjector);
        autoinjector.setAdapter(new ArrayAdapter<>(this,
            android.R.layout.simple_spinner_dropdown_item,
            getResources().getStringArray(R.array.autoinjectors)
        ));

        Intent intent = getIntent();
        Profile p = (Profile) intent.getSerializableExtra(ProfileFragment.SEND_PROFILE);
        receiveProfile(p);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra(UPDATED_PROFILE, profile);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void cancelProfileChanges(View view) {
        // TODO: Feedback (saved/not saved)
        super.onBackPressed();
    }

    public void saveProfileChanges(View view) {
        Profile p = sendProfile();
        if (p == null) {
            Utility.showToast(getApplicationContext(), "Not all fields have been filled out.");
            return;
        }

        if (profile == null || !profile.same(p)) {
            profile = p;
            // TODO: Save...
            Utility.showToast(getApplicationContext(), "Changes were saved!");
            this.onBackPressed();
        } else {
            Utility.showToast(getApplicationContext(), "Nothing was changed.");
        }
    }

    private Profile sendProfile() {
        // Profile Data
        String name = ((EditText) findViewById(R.id.profile_name)).getText().toString();
        TextView birthday = findViewById(R.id.birthday);
        Profile.Gender gender = genderFromString(getRadioGroupValue(R.id.gender));
        ArrayList<String> allergens = new ArrayList<>(Arrays.asList(allergyTags.toString().split(" ")));
        String asthma = getRadioGroupValue(R.id.asthma);

        if (name.isEmpty() || gender == null || asthma == null || birthday.getText().length() == 0) {
            return null;
        }

        // Emergency Set
        String antihistamine = ((EditText) findViewById(R.id.antihistamine)).getText().toString();
        String antihistamineDosage = ((EditText) findViewById(R.id.antihistamine_dosage)).getText().toString();
        String steroid = ((EditText) findViewById(R.id.steroid)).getText().toString();
        String steroidDosage = ((EditText) findViewById(R.id.steroid_dosage)).getText().toString();
        Spinner autoinjector = findViewById(R.id.autoinjector);
        String salbutamol = getRadioGroupValue(R.id.salbutamol);

        if (salbutamol == null || autoinjector.getSelectedItem() == null) {
            return null;
        }

        DatePicker date = birthdayPicker.getDatePicker();
        String[] autoInjectors = getResources().getStringArray(R.array.autoinjectors);

        return new Profile(name,
            date.getYear(),
            date.getMonth(),
            date.getDayOfMonth(),
            gender,
            allergens,
            asthma.equals(getString(R.string.yes)),
            antihistamine,
            antihistamineDosage,
            steroid,
            steroidDosage,
            autoInjectors[autoinjector.getSelectedItemPosition()],
            salbutamol.equals(getString(R.string.yes))
        );
    }

    private void receiveProfile(Profile p) {
        if (p == null) {
            return;
        }
        profile = p;
        // Profile Name
        ((EditText) findViewById(R.id.profile_name)).setText(p.getName());
        // Birthday
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(p.getBirthday());
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        final TextView birthday = findViewById(R.id.birthday);
        birthday.setText(Utility.fmtDate(year, month, day));
        birthdayPicker.updateDate(year, month, day);
        // Gender
        setRadioGroupValue(R.id.gender, genderToString(p.getSex()));
        // TODO: Allergens...
        // Asthma
        setRadioGroupValue(R.id.asthma, getString(p.hasAsthma() ? R.string.yes : R.string.no));

        // Antihistamine
        ((EditText) findViewById(R.id.antihistamine)).setText(p.getAntihistamine());
        ((EditText) findViewById(R.id.antihistamine_dosage)).setText(p.getSteroidDosage());
        // Steroid
        ((EditText) findViewById(R.id.steroid)).setText(p.getSteroid());
        ((EditText) findViewById(R.id.steroid_dosage)).setText(p.getSteroidDosage());
        // Autoinjector
        Spinner autoinjector = findViewById(R.id.autoinjector);
        ArrayAdapter<String> autoinjectorAdapter = (ArrayAdapter<String>) autoinjector.getAdapter();
        autoinjector.setSelection(autoinjectorAdapter.getPosition(p.getAutoinjector()));
        // Salbutamol
        setRadioGroupValue(R.id.salbutamol, getString(p.takesSalbutamol() ? R.string.yes : R.string.no));
    }

    /**
     * Helper method to retrieve the selected radio button value of a radio group.
     *
     * @param id The id of the radio group.
     * @return Selected radio button value or null if nothing is selected.
     */
    private String getRadioGroupValue(int id) {
        int checkedButton = ((RadioGroup) findViewById(id)).getCheckedRadioButtonId();
        if (checkedButton == -1) {
            return null;
        }
        RadioButton button = findViewById(checkedButton);
        return button.getText().toString();
    }

    /**
     * Helper method to set the value of a radio button in a radio group.
     *
     * @param id    The id of the radio group.
     * @param value The value of the radio button that will be selected.
     */
    private void setRadioGroupValue(int id, String value) {
        RadioGroup radioGroup = findViewById(id);
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            RadioButton button = (RadioButton) radioGroup.getChildAt(i);
            if (button.getText().toString().equals(value)) {
                button.setChecked(true);
                break;
            }
        }
    }

    private Profile.Gender genderFromString(String gender) {
        if (gender == null) {
            return null;
        }
        if (gender.equalsIgnoreCase(getString(R.string.male))) {
            return Profile.Gender.Male;
        } else if (gender.equalsIgnoreCase(getString(R.string.female))) {
            return Profile.Gender.Female;
        } else if (gender.equalsIgnoreCase(getString(R.string.diverse))) {
            return Profile.Gender.Diverse;
        } else {
            return null;
        }
    }

    private String genderToString(Profile.Gender gender) {
        switch (gender) {
            case Male:
                return getString(R.string.male);
            case Female:
                return getString(R.string.female);
            case Diverse:
                return getString(R.string.diverse);
            default:
                return "";
        }
    }
}
