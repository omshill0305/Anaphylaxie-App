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
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.olga.aa_app.utility.Utility;

import java.util.Calendar;

/**
 * The Profile page/tab of the main activity.
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
        setHasOptionsMenu(true);
        return rootView;
    }

    /**
     * If p is null the profile overview will be hidden, else it will show the overview and fill in the data.
     *
     * @param profile profile data
     */
    public void updateProfileOverview(Profile profile) {
        if (profile != null) {
            Profile.currentProfile = profile;
            this.profile = profile;
            showOverview();
        } else if (this.profile != null) {
            this.profile = null;
            hideOverview();
        }
    }

    /**
     * Shows and fills the table layout view with the existing profile data.
     */
    private void showOverview() {
        Activity activity = getActivity();
        if (activity == null) {
            return;
        }
        // Personal Data
        TextView name = activity.findViewById(R.id.profile_name);
        name.setText(profile.getName());
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
        gender.setText(genderToString(profile.getSex()));
        // TODO: Allergens...
        // TextView allergens = data.findViewById(R.id.allergens);
        TextView asthma = data.findViewById(R.id.asthma);
        asthma.setText(profile.hasAsthma() ? R.string.yes : R.string.no);

        // Emergency Set
        TextView antihistamine = data.findViewById(R.id.antihistamine);
        antihistamine.setText(profile.getAntihistamineDosage() + " " + profile.getAntihistamine());
        TextView steroid = data.findViewById(R.id.steroid);
        steroid.setText(profile.getSteroidDosage() + " " + profile.getSteroid());
        TextView autoinjector = data.findViewById(R.id.autoinjector);
        autoinjector.setText(profile.getAutoinjector());
        TextView salbumatol = data.findViewById(R.id.salbutamol);
        salbumatol.setText(profile.takesSalbutamol() ? R.string.yes : R.string.no);

        Button button = activity.findViewById(R.id.profile_form_btn);
        button.setText(R.string.edit_profile);
    }

    /**
     * Resets the view by hiding the table layout and reverting the text views to the default values.
     */
    private void hideOverview() {
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.dataprotection:
                Intent intent = new Intent(getActivity(), DataProtectionActivity.class);
                getActivity().startActivity(intent);
                return true;
        }
        return false;
    }
}
