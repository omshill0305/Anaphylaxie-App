package com.example.olga.aa_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.olga.aa_app.utility.Utility;

/**
 * The Reaction page/tab of the main activity.
 */
public class ReactionFragment extends Fragment {

    public ReactionFragment() {
        // Required empty public constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_reaction, container, false);
        Button chooseBt = rootView.findViewById(R.id.button2);
        chooseBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity activity = getActivity();
                if (activity != null) {
                    if (Profile.currentProfile == null) {
                        Utility.showToast(activity.getApplicationContext(), getString(R.string.required_profile));
                    } else {
                        Intent intent = new Intent(activity, SymptomsActivity.class);
                        activity.startActivity(intent);
                    }
                }
            }
        });
        setHasOptionsMenu(true);
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.dataprotection) {
            Activity activity = getActivity();
            if (activity != null) {
                Intent intent = new Intent(activity, DataProtectionActivity.class);
                activity.startActivity(intent);
            }
            return true;
        }
        return false;
    }
}


