package com.example.olga.aa_app;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private Profile profile = null;
    public static final int REQUEST_PROFILE_UPDATE = 1;
    public static final String SEND_PROFILE = "com.example.olga.aa_app.SEND_PROFILE";

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        Button button = rootView.findViewById(R.id.profile_form_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ProfileFormActivity.class);
                if (profile != null) {
                    intent.putExtra(SEND_PROFILE, profile);
                }
                getActivity().startActivityForResult(intent, REQUEST_PROFILE_UPDATE);
            }
        });
        return rootView;
    }

    public Profile getProfile() {
        return  profile;
    }

    public void setProfile(Profile p) {
        profile = p;
        showProfile();
    }

    private void showProfile() {

    }
}
