package com.example.olga.aa_app;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    private enum Page {
        Profiles,
        EmergencyCall,
        Reactions;

        @Override
        public String toString() {
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
    private Page currentPage = Page.Profiles;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

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
        toolbar.setLogo(R.drawable.logo1);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        initializeFragments();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void initializeFragments() {
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .add(R.id.main_frame, reactionFragment, Page.Reactions.toString())
                .hide(reactionFragment).commit();
        fm.beginTransaction()
                .add(R.id.main_frame, emergencyCallFragment, Page.EmergencyCall.toString())
                .hide(emergencyCallFragment).commit();
        fm.beginTransaction()
                .add(R.id.main_frame, profileFragment, Page.Profiles.toString())
                .commit();
    }

    private void setPage(Page page) {
        if (currentPage == page) {
            return;
        }
        getSupportFragmentManager()
                .beginTransaction()
                .hide(getFragmentFromPage(currentPage))
                .show(getFragmentFromPage(page)).commit();
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
}
