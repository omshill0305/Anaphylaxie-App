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
import androidx.lifecycle.ViewModelProviders;

import com.example.olga.aa_app.database.entities.EmergencySet;
import com.example.olga.aa_app.database.entities.Profile;
import com.example.olga.aa_app.database.jointables.SetsOfProfile;
import com.example.olga.aa_app.database.viewmodels.AllergiesOfProfileViewModel;
import com.example.olga.aa_app.database.viewmodels.AllergyViewModel;
import com.example.olga.aa_app.database.viewmodels.EmergencySetViewModel;
import com.example.olga.aa_app.database.viewmodels.ProfileViewModel;
import com.example.olga.aa_app.database.viewmodels.SetsOfProfileViewModel;
import com.example.olga.aa_app.utility.SpaceTokenizer;
import com.example.olga.aa_app.utility.TagSpan;
import com.example.olga.aa_app.utility.Utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Profile Form
 * <p>
 * Checks user input and saves the profile data if valid. The saved profile
 * can be viewed in the profile tab
 * (ProfileFragment).
 */
public class ProfileFormActivity extends AppCompatActivity {

    // Message to signal the main activity (HomeActivity) that profile data
    // was updated and/or saved.
    public static final String UPDATED_PROFILE = "com.example.olga.aa_app.UPDATED_PROFILE";

    private ProfileForm oldProfileForm;
    private DatePickerDialog birthdayPicker;
    private MultiAutoCompleteTextView multiComplete;
    private SpannableStringBuilder allergyTags = new SpannableStringBuilder();

    private ProfileViewModel profileViewModel;
    private AllergyViewModel allergyViewModel;
    private SetsOfProfileViewModel setsOfProfileViewModel;
    private AllergiesOfProfileViewModel allergiesOfProfileViewModel;
    private EmergencySetViewModel emergencySetViewModel;

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

        // creates instances of needed view models.
        profileViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
        allergyViewModel = ViewModelProviders.of(this).get(AllergyViewModel.class);
        setsOfProfileViewModel = ViewModelProviders.of(this).get(SetsOfProfileViewModel.class);
        allergiesOfProfileViewModel = ViewModelProviders.of(this).get(AllergiesOfProfileViewModel.class);
        emergencySetViewModel = ViewModelProviders.of(this).get(EmergencySetViewModel.class);


        Intent intent = getIntent();
        oldProfileForm = (ProfileForm) intent.getSerializableExtra(ProfileFragment.SEND_PROFILE);
        receiveProfile(oldProfileForm);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra(UPDATED_PROFILE, oldProfileForm);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void cancelProfileChanges(View view) {
        // TODO: Feedback (saved/not saved)
        super.onBackPressed();
    }

    public void saveProfileChanges(View view) {
        ProfileForm updatedProfileForm = sendProfile();
        if (updatedProfileForm == null) {
            Utility.showToast(getApplicationContext(), "Not all fields have been filled out.");
            return;
        }

        if (oldProfileForm == null){
            createProfileInDB(updatedProfileForm)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableCompletableObserver() {
                        @Override
                        public void onComplete() {
                            oldProfileForm = updatedProfileForm;
                            Utility.showToast(getApplicationContext(), "Profile was created");
                            onBackPressed();
                        }

                        @Override
                        public void onError(Throwable e) {
                            Utility.showToast(getApplicationContext(), "Profile could not be created. Please try again!");
                            onBackPressed();
                        }
                    });
        }
        else if(!oldProfileForm.same(updatedProfileForm)) {
            updateProfileInDB(updatedProfileForm)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableCompletableObserver() {
                        @Override
                        public void onComplete() {
                            oldProfileForm = updatedProfileForm;
                            Utility.showToast(getApplicationContext(), "Changes were saved!");
                            onBackPressed();
                        }

                        @Override
                        public void onError(Throwable e) {
                            Utility.showToast(getApplicationContext(), "Changes could not be saved, please try again");
                            onBackPressed();
                        }
                    });
        }
        else {
            Utility.showToast(getApplicationContext(), "Nothing was changed.");
        }
    }

    private Completable updateProfileInDB(ProfileForm p){
        Profile updatedProfile = p.getProfile();
        updatedProfile.setName(p.getName());
        updatedProfile.setBirthday(p.getBirthday());
        updatedProfile.setAge(p.getAge());  //TODO: Age needs to be calculated again... maybe
        updatedProfile.setGender(p.getGender());
        updatedProfile.setAsthma(p.hasAsthma());
        updatedProfile.setSalbutamol(p.takesSalbutamol());

        EmergencySet updatedAntihistamine = p.getEmergencySets().get(0);
        updatedAntihistamine.setBrandName(p.getAntihistamine());
        updatedAntihistamine.setDosage(p.getAntihistamineDosage());

        EmergencySet updatedSteroid = p.getEmergencySets().get(1);
        updatedSteroid.setBrandName(p.getSteroid());
        updatedSteroid.setDosage(p.getSteroidDosage());

        EmergencySet updatedAutoInjektor = p.getEmergencySets().get(2);
        updatedAutoInjektor.setBrandName(p.getAutoinjector());

        return profileViewModel.update(updatedProfile)
                .flatMap(s -> emergencySetViewModel.update(updatedAntihistamine))
                .doOnSuccess(s -> System.out.println("updated lines antihistamine: " + s))
                .flatMap(s -> emergencySetViewModel.update(updatedSteroid))
                .doOnSuccess(s -> System.out.println("updated lines steroid: " + s))
                .flatMap(s -> emergencySetViewModel.update(updatedAutoInjektor))
                .doOnSuccess(s -> System.out.println("updated lines autoinjektor: " + s))
                .toCompletable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private Completable createProfileInDB(ProfileForm p){
        //TODO: Reactivate creating profile when need again
        Profile createdProfile = new Profile(p.getName(), p.getBirthday(), p.getAge(), p.getGender(), p.hasAsthma(), p.takesSalbutamol());

        profileViewModel.insert(createdProfile).subscribe();

        EmergencySet emergencySetAntihistamine = new EmergencySet(p.getAntihistamine(), "Antihistaminikum", p.getAntihistamineDosage(), "ml");
        EmergencySet emergencySetSteroid = new EmergencySet(p.getSteroid(), "Steroid" , p.getSteroidDosage(), "ml");
        EmergencySet emergencySetAutoInjektor = new EmergencySet(p.getAutoinjector(), "Auto-Injektor", "-", "-");
        //TODO: Split and assign dosage string to dosage and dosage unit

        return emergencySetViewModel.insert(emergencySetAntihistamine)
                .flatMap(s -> setsOfProfileViewModel.insert(new SetsOfProfile(1, s.intValue())))
                .doOnSuccess(s -> System.out.println("Inserted antihistamine"))
                .flatMap(s -> emergencySetViewModel.insert(emergencySetSteroid))
                .flatMap(s -> setsOfProfileViewModel.insert(new SetsOfProfile(1, s.intValue())))
                .doOnSuccess(s -> System.out.println("inserted steroid"))
                .flatMap(s -> emergencySetViewModel.insert(emergencySetAutoInjektor))
                .flatMap(s -> setsOfProfileViewModel.insert(new SetsOfProfile(1, s.intValue())))
                .doOnSuccess(s -> System.out.println("Inserted autoinjektor"))
                .toCompletable()
                .observeOn(AndroidSchedulers.mainThread());

        //TODO: Store and delete inserted information if whole chain was not successful
    }

    private ProfileForm sendProfile() {
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

        ProfileForm profileForm = new ProfileForm();

        if(oldProfileForm != null){
            profileForm = new ProfileForm(oldProfileForm);
        }
        profileForm.changeProfileForm(
                name,
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

        return profileForm;
    }


    private void receiveProfile(ProfileForm profile) {
        if (oldProfileForm == null) {
            return;
        }

        // Profile Name
        ((EditText) findViewById(R.id.profile_name)).setText(profile.getName());
        // Birthday
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(profile.getBirthday());  // TODO: Birthday
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        final TextView birthday = findViewById(R.id.birthday);
        birthday.setText(Utility.fmtDate(year, month, day));
        birthdayPicker.updateDate(year, month, day);
        // Gender
        setRadioGroupValue(R.id.gender, genderToString(profile.getGender()));
        // TODO: Allergens...
        // Asthma
        setRadioGroupValue(R.id.asthma, getString(profile.hasAsthma() ? R.string.yes : R.string.no));

        // Antihistamine
        ((EditText) findViewById(R.id.antihistamine)).setText(profile.getAntihistamine());
        ((EditText) findViewById(R.id.antihistamine_dosage)).setText(profile.getAntihistamineDosage());
        // Steroid
        ((EditText) findViewById(R.id.steroid)).setText(profile.getSteroid());
        ((EditText) findViewById(R.id.steroid_dosage)).setText(profile.getSteroidDosage());
        // Autoinjector
        Spinner autoinjector = findViewById(R.id.autoinjector);
        ArrayAdapter<String> autoinjectorAdapter = (ArrayAdapter<String>) autoinjector.getAdapter();
        autoinjector.setSelection(autoinjectorAdapter.getPosition(profile.getAutoinjector()));
        // Salbutamol
        setRadioGroupValue(R.id.salbutamol, getString(profile.takesSalbutamol() ? R.string.yes : R.string.no));

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
