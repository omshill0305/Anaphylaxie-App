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

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.olga.aa_app.database.entities.Allergy;
import com.example.olga.aa_app.database.entities.EmergencySet;
import com.example.olga.aa_app.database.entities.Profile;
import com.example.olga.aa_app.database.viewmodels.AllergyViewModel;
import com.example.olga.aa_app.database.viewmodels.ProfileViewModel;
import com.example.olga.aa_app.utility.Utility;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * The Profile page/tab of the main activity.
 */
public class ProfileFragment extends Fragment {

    private long currentProfileId;
    private ProfileForm currentProfile;
    public static final int REQUEST_PROFILE_UPDATE = 1;
    public static final String SEND_PROFILE = "com.example.olga.aa_app.SEND_PROFILE";

    private ProfileViewModel profileViewModel;
    private AllergyViewModel allergyViewModel;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        Button button = rootView.findViewById(R.id.profile_form_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Activity activity = getActivity();
                if (activity != null) {
                    Intent intent = new Intent(getActivity(), ProfileFormActivity.class);
                    if (currentProfile != null) {
                        intent.putExtra(SEND_PROFILE, currentProfile);
                    }
                    activity.startActivityForResult(intent, REQUEST_PROFILE_UPDATE);
                }
            }
        });
        setHasOptionsMenu(true);

        profileViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
        allergyViewModel = ViewModelProviders.of(this).get(AllergyViewModel.class);
        currentProfileId = 1; // TODO: Remove manual set profile id
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showOverview();
    }

    /**
     * If p is null the profile overview will be hidden, else it will show the overview and fill in the data.
     *
     * @param profileForm profileForm
     */
    public void updateProfileOverview(ProfileForm profileForm) {
        if (profileForm != null) {
            this.currentProfile = profileForm;
            this.currentProfileId = profileForm.getId();

            showOverview();
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

        CompositeDisposable d = new CompositeDisposable();

        createProfileForm()
                .subscribeWith(new DisposableSingleObserver<ProfileForm>() {
                    @Override
                    public void onSuccess(ProfileForm profileForm) {
                        TextView name = activity.findViewById(R.id.profile_name);
                        name.setText(profileForm.getName());
                        TableLayout data = activity.findViewById(R.id.profile_data);
                        data.setVisibility(View.VISIBLE);
                        TextView birthday = data.findViewById(R.id.birthday);
                        final Calendar calendar = Calendar.getInstance();
                        calendar.setTime(profileForm.getBirthday());
                        int day = calendar.get(Calendar.DAY_OF_MONTH);
                        int month = calendar.get(Calendar.MONTH);
                        int year = calendar.get(Calendar.YEAR);
                        birthday.setText(Utility.fmtDate(year, month, day));
                        TextView gender = data.findViewById(R.id.gender);
                        gender.setText(profileForm.genderToString());
                        // TODO: Allergens...
                        TextView allergens = data.findViewById(R.id.allergens);
                        allergens.setText(profileForm.allergensToString());
                        TextView asthma = data.findViewById(R.id.asthma);
                        asthma.setText(profileForm.hasAsthma() ? R.string.yes : R.string.no);

                        // Emergency Set
                        TextView antihistamine = data.findViewById(R.id.antihistamine);
                        antihistamine.setText(profileForm.getAntihistamineDosage() + " " + profileForm.getAntihistamine());
                        TextView steroid = data.findViewById(R.id.steroid);
                        steroid.setText(profileForm.getSteroidDosage() + " " + profileForm.getSteroid());
                        TextView autoinjector = data.findViewById(R.id.autoinjector);
                        autoinjector.setText(profileForm.getAutoinjector());
                        TextView salbutamol = data.findViewById(R.id.salbutamol);
                        salbutamol.setText(profileForm.takesSalbutamol() ? R.string.yes : R.string.no);

                        Button button = activity.findViewById(R.id.profile_form_btn);
                        button.setText(R.string.edit_profile);

                        currentProfile = profileForm;
                    }

                    @Override
                    public void onError(Throwable e) {
                        hideOverview();
                        System.out.println("Could not retrieve profile..."); // TODO: Toast message maybe
                    }
                });

    }

    private Single<ProfileForm> createProfileForm(){

        final AtomicReference<Profile> retrievedProfile = new AtomicReference<>();
        final AtomicReference<List<EmergencySet>> retrievedEmergencySets = new AtomicReference<>();

        return profileViewModel.getProfileByProfileId(currentProfileId)
                .doOnSuccess(s -> {
                    retrievedProfile.set(s);
                    System.out.println("Retrieved profile with id: " + retrievedProfile.get().getProfileId());
                })
                .flatMap(s -> profileViewModel.getAllEmergencySetsOfProfileByProfileId(currentProfileId))
                .doOnSuccess(s -> {
                    retrievedEmergencySets.set(s);
                    System.out.println("Retrieved emergency set with " + s.size() + " entries");
                })
                .flatMap(s -> profileViewModel.getAllAllergiesOfProfileByProfileId(currentProfileId))
                .map( allergies -> {
                    Profile profile = retrievedProfile.get();
                    List<EmergencySet> emergencySets = retrievedEmergencySets.get();

                    ProfileForm form = new ProfileForm(profile, emergencySets, allergies);

                    return form;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
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
