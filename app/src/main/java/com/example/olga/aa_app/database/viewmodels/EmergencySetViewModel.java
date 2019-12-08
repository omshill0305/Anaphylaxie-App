package com.example.olga.aa_app.database.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.olga.aa_app.database.daos.EmergencySetDAO;
import com.example.olga.aa_app.database.entities.EmergencySet;
import com.example.olga.aa_app.database.repositorys.EmergencySetRepository;


public class EmergencySetViewModel extends AndroidViewModel implements EmergencySetDAO {

    private EmergencySetRepository repository;

    public EmergencySetViewModel(@NonNull Application application) {
        super(application);
        repository = new EmergencySetRepository(application);
    }

    public void insert(EmergencySet emergencySet){
        repository.insert(emergencySet);
    }

    public void update(EmergencySet emergencySet){
        repository.update(emergencySet);
    }

    public void delete(EmergencySet emergencySet){
        repository.delete(emergencySet);
    }
}
