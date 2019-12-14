package com.example.olga.aa_app.database.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.olga.aa_app.database.daos.EmergencySetDAO;
import com.example.olga.aa_app.database.daos.ProfileDAO;
import com.example.olga.aa_app.database.entities.Allergy;
import com.example.olga.aa_app.database.entities.Profile;
import com.example.olga.aa_app.database.repositorys.EmergencySetRepository;
import com.example.olga.aa_app.database.repositorys.ProfileRepository;

/**
 * The ViewModel is a wrapper class for the repositories. The main purpose of this class is to call
 * methods from the repositories and provide the data that the user needs. The ViewModel exists, to
 * separate obscure the repository class and the underlying structure and classes. This is the class
 * that the Activities and Fragments will be interacting with. Again, LiveData has to be called and
 * referenced just once in the repository, as its contents are updated automatically and do not need
 * to be called again with a queries, this is why LiveData only has to be referenced once, and only
 * returned later.
 */
public class ProfileViewModel extends AndroidViewModel implements ProfileDAO {

    private ProfileRepository repository;

    public ProfileViewModel(@NonNull Application application) {
        super(application);
        repository = new ProfileRepository(application);
    }

    public void insert(Profile profile) {
        repository.insert(profile);
    }

    public void update(Profile profile) {
        repository.update(profile);
    }

    public void delete(Profile profile) {
        repository.delete(profile);
    }
}
