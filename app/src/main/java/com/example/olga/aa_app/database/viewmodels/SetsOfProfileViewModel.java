package com.example.olga.aa_app.database.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.olga.aa_app.database.daos.SetsOfProfileDAO;
import com.example.olga.aa_app.database.entities.Profile;
import com.example.olga.aa_app.database.jointables.SetsOfProfile;
import com.example.olga.aa_app.database.repositorys.SetsOfProfileRepository;

import java.util.List;

import io.reactivex.Completable;

public class SetsOfProfileViewModel extends AndroidViewModel implements SetsOfProfileDAO {

    private SetsOfProfileRepository repository;

    public SetsOfProfileViewModel(@NonNull Application application) {
        super(application);
        repository = new SetsOfProfileRepository(application);
    }

    @Override
    public Completable insert(SetsOfProfile setsOfProfile) {
        return repository.insert(setsOfProfile);
    }

    @Override
    public LiveData<List<Profile>> getProfilesWithEmergencySet(int emergencySetId) {
        return repository.getProfilesWithEmergencySet(emergencySetId);
    }

    @Override
    public LiveData<List<Profile>> getEmergencySetsOfProfile(int profileId) {
        return repository.getEmergencySetsOfProfile(profileId);
    }
}
