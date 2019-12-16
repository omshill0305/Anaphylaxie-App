package com.example.olga.aa_app.database.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.olga.aa_app.database.daos.EmergencySetDAO;
import com.example.olga.aa_app.database.entities.EmergencySet;
import com.example.olga.aa_app.database.repositorys.EmergencySetRepository;

import java.util.List;

import io.reactivex.Completable;

/**
 * The ViewModel is a wrapper class for the repositories. The main purpose of this class is to call
 * methods from the repositories and provide the data that the user needs. The ViewModel exists, to
 * separate obscure the repository class and the underlying structure and classes. This is the class
 * that the Activities and Fragments will be interacting with. Again, LiveData has to be called and
 * referenced just once in the repository, as its contents are updated automatically and do not need
 * to be called again with a queries, this is why LiveData only has to be referenced once, and only
 * returned later.
 */
public class EmergencySetViewModel extends AndroidViewModel implements EmergencySetDAO {

    private EmergencySetRepository repository;

    public EmergencySetViewModel(@NonNull Application application) {
        super(application);
        repository = new EmergencySetRepository(application);
    }

    public Completable insert(EmergencySet emergencySet){
        return repository.insert(emergencySet);
    }

    public Completable update(EmergencySet emergencySet){
        return repository.update(emergencySet);
    }

    public Completable delete(EmergencySet emergencySet){
        return repository.delete(emergencySet);
    }

    @Override
    public LiveData<List<EmergencySet>> getAllEmergencySets() {
        return repository.getAllEmergencySets();
    }
}
