package com.example.olga.aa_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.olga.aa_app.utility.Utility;

import java.util.Calendar;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        rootView.findViewById(R.id.profile_data).setVisibility(View.GONE);
        Button button = rootView.findViewById(R.id.profile_form_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Activity activity = getActivity();
                if (activity != null) {
                    Intent intent = new Intent(getActivity(), ProfileFormActivity.class);
                    if (profile != null) {
                        intent.putExtra(SEND_PROFILE, profile);
                    }
                    activity.startActivityForResult(intent, REQUEST_PROFILE_UPDATE);
                }
            }
        });
        return rootView;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile p) {
        if (p != null) {
            profile = p;
            showProfile();
        } else if (profile != null) {
            profile = null;
            hideProfile();
        }
    }

    private void showProfile() {
        Activity activity = getActivity();
        if (activity == null) {
            return;
        }
        TextView name = activity.findViewById(R.id.profile_name);
        name.setText(profile.getProfilName());
        TableLayout data = activity.findViewById(R.id.profile_data);
        data.setVisibility(View.VISIBLE);
        TextView birthday = data.findViewById(R.id.birthday);
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(profile.getBirthday());
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        birthday.setText(Utility.fmtDate(year, month, day));
        TextView gender = data.findViewById(R.id.gender);
        gender.setText(profile.getSex());
        // TODO: Allergens...
        // TextView allergens = data.findViewById(R.id.allergens);
        TextView asthma = data.findViewById(R.id.asthma);
        asthma.setText(profile.hasAsthma() ? R.string.yes : R.string.no);
        TextView antihistamine = data.findViewById(R.id.antihistamine);
        antihistamine.setText(profile.getAntihistaminikumDosierung() + " " + profile.getAntihistaminikumName());
        TextView steroid = data.findViewById(R.id.steroid);
        steroid.setText(profile.getSteroidDosierung() + " " + profile.getSteroidName());
        TextView autoinjector = data.findViewById(R.id.autoinjector);
        autoinjector.setText(profile.getAutoinjektorName());
        TextView salbumatol = data.findViewById(R.id.salbutamol);
        salbumatol.setText(profile.takesSalbutamol() ? R.string.yes : R.string.no);
        Button button = activity.findViewById(R.id.profile_form_btn);
        button.setText(R.string.edit_profile);
    }

    private void hideProfile() {
        Activity activity = getActivity();
        if (activity == null) {
            return;
        }
        Button button = activity.findViewById(R.id.profile_form_btn);
        button.setText(getString(R.string.create_profile));
        TextView name = activity.findViewById(R.id.profile_name);
        name.setText(R.string.no_profile);
        TableLayout data = activity.findViewById(R.id.profile_data);
        data.setVisibility(View.GONE);
    }
}
