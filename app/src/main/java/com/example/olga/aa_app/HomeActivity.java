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
import com.example.olga.aa_app.database.viewmodels.AllergyViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;
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
        initializeFragments();

        AllergyViewModel allergyViewModel = ViewModelProviders.of(this).get(AllergyViewModel.class);
        Single<Allergy> allergy1 = allergyViewModel.getAllergyByName("bruh3");

        CompositeDisposable d = new CompositeDisposable();

        d.add(allergy1
                .doOnSuccess(s -> System.out.println("got allergy: " + s.name))
                .flatMapCompletable( s -> allergyViewModel.delete(s))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        System.out.println("deleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("something went wrong");
                    }
                }));
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
                Profile p = (Profile) data.getSerializableExtra(ProfileFormActivity.UPDATED_PROFILE);
                if (p != null) {
                    profileFragment.updateProfileOverview(p);
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
