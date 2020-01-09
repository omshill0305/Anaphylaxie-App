package com.example.olga.aa_app.database.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.olga.aa_app.database.daos.EmergencyCallTimestampDAO;
import com.example.olga.aa_app.database.daos.ReactionTimestampDAO;
import com.example.olga.aa_app.database.entities.EmergencyCallTimestamp;
import com.example.olga.aa_app.database.entities.ReactionTimestamp;
import com.example.olga.aa_app.database.repositorys.EmergencyCallTimestampRepository;
import com.example.olga.aa_app.database.repositorys.ReactionTimestampRepository;

import io.reactivex.Completable;

public class EmergencyCallTimestampViewModel extends AndroidViewModel implements EmergencyCallTimestampDAO {

    private EmergencyCallTimestampRepository repository;

    public EmergencyCallTimestampViewModel(@NonNull Application application) {
        super(application);
        repository = new EmergencyCallTimestampRepository(application);
    }

    @Override
    public Completable insert(EmergencyCallTimestamp emergencyCallTimestamp) {
        return repository.insert(emergencyCallTimestamp);
    }

}
