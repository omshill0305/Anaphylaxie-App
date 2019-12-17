package com.example.olga.aa_app.database.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.olga.aa_app.database.daos.EmergencySetDAO;
import com.example.olga.aa_app.database.daos.ProfileDAO;
import com.example.olga.aa_app.database.entities.Allergy;
import com.example.olga.aa_app.database.entities.Profile;
import com.example.olga.aa_app.database.repositorys.EmergencySetRepository;
import com.example.olga.aa_app.database.repositorys.ProfileRepository;

import java.util.List;

import io.reactivex.Completable;

public class ProfileViewModel extends AndroidViewModel implements ProfileDAO {

    private ProfileRepository repository;

    public ProfileViewModel(@NonNull Application application) {
        super(application);
        repository = new ProfileRepository(application);
    }

    public Completable insert(Profile profile) {
        return repository.insert(profile);
    }

    public Completable update(Profile profile) {
        return repository.update(profile);
    }

    public Completable delete(Profile profile) {
        return repository.delete(profile);
    }

    @Override
    public LiveData<List<Profile>> getAllProfiles() {
        return repository.getAllProfiles();
    }
}
