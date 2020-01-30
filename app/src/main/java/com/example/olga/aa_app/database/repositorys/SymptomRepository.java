package com.example.olga.aa_app.database.repositorys;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.olga.aa_app.database.ReactionDatabase;
import com.example.olga.aa_app.database.daos.SymptomDAO;
import com.example.olga.aa_app.database.entities.Symptom;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;


public class SymptomRepository implements SymptomDAO {

    private SymptomDAO symptomDAO;


    public SymptomRepository(Application application){
        ReactionDatabase database = ReactionDatabase.getInstance(application);
        symptomDAO = database.symptomDAO();
    }


    /* ----------------------------------DAO queries--------------------------------------------*/


    public Single<Long> insert(Symptom symptom){
        return symptomDAO.insert(symptom).subscribeOn(Schedulers.io());
    }

    public Single<Integer> update(Symptom symptom){
        return symptomDAO.update(symptom).subscribeOn(Schedulers.io());
    }

    public Single<Integer> delete(Symptom symptom){
        return symptomDAO.delete(symptom).subscribeOn(Schedulers.io());
    }

    @Override
    public LiveData<List<Symptom>> getAllSymptoms() {
        return symptomDAO.getAllSymptoms();
    }

    @Override
    public Single<Symptom> getSymptomByID(long id) {
        return symptomDAO.getSymptomByID(id).subscribeOn(Schedulers.io());
    }

    @Override
    public Single<Symptom> getSymptomByName(String name) {
        return symptomDAO.getSymptomByName(name).subscribeOn(Schedulers.io());
    }

}
