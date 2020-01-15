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

public class ProfileFormActivity extends AppCompatActivity {

    public static final String UPDATED_PROFILE = "com.example.olga.aa_app.UPDATED_PROFILE";
    private static final String[] ALLERGENS = new String[]{"Belgium", "France", "Italy", "Germany", "Spain"};
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
        toolbar.setLogo(R.drawable.logo1);

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

        birthdayPicker = new DatePickerDialog(ProfileFormActivity.this, R.style.BirthdayPickerStyle,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int y, int m, int d) {
                        birthday.setText(Utility.fmtDate(y, m, d));
                        birthdayPicker.onSaveInstanceState();
                    }
                }, year, month, day);
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

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, ALLERGENS);

        multiComplete = findViewById(R.id.multiAutoCompleteTextView);
        multiComplete.setAdapter(adapter);
        multiComplete.setTokenizer(new SpaceTokenizer());
        multiComplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int last = allergyTags.length();
                allergyTags.append(adapterView.getItemAtPosition(i).toString()).append(" ");
                allergyTags.setSpan(new TagSpan(ProfileFormActivity.this), last, allergyTags.length() - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
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

        Intent intent = getIntent();
        Profile p = (Profile) intent.getSerializableExtra(ProfileFragment.SEND_PROFILE);
        receiveProfile(p);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra(UPDATED_PROFILE,profile);
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

        if (profile == null || !profile.equalProfileInformation(p)) {
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
        DatePicker date = birthdayPicker.getDatePicker();
        String gender = getRadioGroupValue(R.id.gender);
        ArrayList<String> allergens = new ArrayList<>(Arrays.asList(allergyTags.toString().split(" ")));
        String asthma = getRadioGroupValue(R.id.asthma);

        if (gender == null || asthma == null) {
            return null;
        }

        // Emergency Set
        String antihistamine = ((EditText) findViewById(R.id.antihistamine)).getText().toString();
        String antihistamineDosage = ((EditText) findViewById(R.id.antihistamine_dosage)).getText().toString();
        String steroid = ((EditText) findViewById(R.id.steroid)).getText().toString();
        String steroidDosage = ((EditText) findViewById(R.id.steroid_dosage)).getText().toString();
        String autoinjector = ((EditText) findViewById(R.id.autoinjector)).getText().toString();
        // TODO: Can this even have a dosage?
        // String autoinjectorDosage = ((EditText) findViewById(R.id.autoinjector_dosage)).getText().toString();
        String salbutamol = getRadioGroupValue(R.id.salbutamol);

        if (salbutamol == null) {
            return null;
        }

        return new Profile(name, date.getYear(), date.getMonth(), date.getDayOfMonth(), gender,
                allergens, asthma.equals(getString(R.string.yes)), antihistamine, antihistamineDosage,
                steroid, steroidDosage, autoinjector, salbutamol.equals(getString(R.string.yes)));
    }

    private void receiveProfile(Profile p) {
        if (p == null) {
            return;
        }
        profile = p;
        // Profile Name
        ((EditText) findViewById(R.id.profile_name)).setText(p.getProfilName());
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
        setRadioGroupValue(R.id.gender, p.getSex());
        // TODO: Allergens...
        // Asthma
        setRadioGroupValue(R.id.asthma, getString(p.hasAsthma() ? R.string.yes : R.string.no));

        // Antihistamine
        ((EditText) findViewById(R.id.antihistamine)).setText(p.getAntihistaminikumName());
        ((EditText) findViewById(R.id.antihistamine_dosage)).setText(p.getSteroidDosierung());
        // Steroid
        ((EditText) findViewById(R.id.steroid)).setText(p.getSteroidName());
        ((EditText) findViewById(R.id.steroid_dosage)).setText(p.getSteroidDosierung());
        // Autoinjector
        ((EditText) findViewById(R.id.autoinjector)).setText(p.getAutoinjektorName());
        // Salbutamol
        setRadioGroupValue(R.id.salbutamol, getString(p.takesSalbutamol() ? R.string.yes : R.string.no));
    }

    private String getRadioGroupValue(int id) {
        int checkedButton = ((RadioGroup) findViewById(id)).getCheckedRadioButtonId();
        if (checkedButton == -1) {
            return null;
        }
        RadioButton button = findViewById(checkedButton);
        return button.getText().toString();
    }

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
}
