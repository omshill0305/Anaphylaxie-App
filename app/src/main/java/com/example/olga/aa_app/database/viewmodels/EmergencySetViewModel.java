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
