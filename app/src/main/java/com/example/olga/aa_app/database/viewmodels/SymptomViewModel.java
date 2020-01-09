package com.example.olga.aa_app.database.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.olga.aa_app.database.daos.EmergencySetDAO;
import com.example.olga.aa_app.database.daos.SymptomDAO;
import com.example.olga.aa_app.database.entities.EmergencySet;
import com.example.olga.aa_app.database.entities.Symptom;
import com.example.olga.aa_app.database.repositorys.EmergencySetRepository;
import com.example.olga.aa_app.database.repositorys.SymptomRepository;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public class SymptomViewModel extends AndroidViewModel implements SymptomDAO {

    private SymptomRepository repository;

    public SymptomViewModel(@NonNull Application application) {
        super(application);
        repository = new SymptomRepository(application);
    }

    public Completable insert(Symptom symptom){
        return repository.insert(symptom);
    }

    public Completable update(Symptom symptom){
        return repository.update(symptom);
    }

    public Completable delete(Symptom symptom){
        return repository.delete(symptom);
    }

    @Override
    public LiveData<List<Symptom>> getAllSymptoms() {
        return repository.getAllSymptoms();
    }

    @Override
    public Single<Symptom> getAllergyByID(int id) {
        return repository.getAllergyByID(id);
    }

    @Override
    public Single<Symptom> getAllergyByName(String name) {
        return repository.getAllergyByName(name);
    }
}
