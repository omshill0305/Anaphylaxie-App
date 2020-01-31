package com.example.olga.aa_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import com.example.olga.aa_app.database.entities.Allergy;
import com.example.olga.aa_app.database.entities.Profile;
import com.example.olga.aa_app.database.viewmodels.AllergyViewModel;
import com.example.olga.aa_app.database.viewmodels.ProfileViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class HomeActivity extends AppCompatActivity {

    private enum Page {
        Profiles, EmergencyCall, Reactions;

        public String format() {
            switch (this) {
                case Profiles:
                    return "Profiles";
                case EmergencyCall:
                    return "EmergencyCall";
                case Reactions:
                    return "Reactions";
            }
            return null;
        }
    }

    private ReactionFragment reactionFragment = new ReactionFragment();
    private ProfileFragment profileFragment = new ProfileFragment();
    private EmergencyCallFragment emergencyCallFragment = new EmergencyCallFragment();
    private Page currentPage = Page.Reactions;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener =
        new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_profile:
                    setPage(Page.Profiles);
                    return true;
                case R.id.navigation_notruf:
                    setPage(Page.EmergencyCall);
                    return true;
                case R.id.navigation_reaction:
                    setPage(Page.Reactions);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_reaction);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        AllergyViewModel allergyViewModel = ViewModelProviders.of(this).get(AllergyViewModel.class);

        Allergy test = new Allergy("Test");
        test.setAllergyId(1);
        allergyViewModel.clearAllergies()
                .andThen(allergyViewModel.insert(test))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();

        Observable.fromIterable(allergeneList())
                .map(a -> allergyViewModel.insert(a))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();

        /*allergyViewModel.insert(new Allergy("placeholder"))
                .flatMap(s -> {
                    Allergy a = new Allergy("b");
                    a.setAllergyId(s);
                    return allergyViewModel.delete(a);
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();*/

        initializeFragments();

    }

    private ArrayList<Allergy> allergeneList(){
        ArrayList<Allergy> allergies = new ArrayList<>();

        String[] stringArray = getResources().getStringArray(R.array.allergens);

        for(int i = 0 ; i < stringArray.length; i++){
            Allergy a = new Allergy(stringArray[i]);
            //a.setAllergyId(i + 1);

            allergies.add(a);
        }

        return allergies;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ProfileFragment.REQUEST_PROFILE_UPDATE) {
            if (resultCode == RESULT_OK) {
                ProfileForm profileForm = (ProfileForm) data.getSerializableExtra(ProfileFormActivity.UPDATED_PROFILE);
                if (profileForm != null) {
                    profileFragment.updateProfileOverview(profileForm);
                }
            }
        }
    }

    private void initializeFragments() {
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
            .add(R.id.main_frame, profileFragment, Page.Profiles.format())
            .hide(profileFragment)
            .commit();
        fm.beginTransaction()
            .add(R.id.main_frame, emergencyCallFragment, Page.EmergencyCall.format())
            .hide(emergencyCallFragment)
            .commit();
        fm.beginTransaction().add(R.id.main_frame, reactionFragment, Page.Reactions.format()).commit();
    }

    private void setPage(Page page) {
        if (currentPage == page) {
            return;
        }
        getSupportFragmentManager().beginTransaction()
            .hide(getFragmentFromPage(currentPage))
            .show(getFragmentFromPage(page))
            .commit();
        currentPage = page;
    }

    private Fragment getFragmentFromPage(Page page) {
        switch (page) {
            case Profiles:
                return profileFragment;
            case EmergencyCall:
                return emergencyCallFragment;
            case Reactions:
                return reactionFragment;
        }
        return profileFragment;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.dataprotection:
                return false;
        }
        return false;
    }
}
