package com.example.olga.aa_app.database.repositorys;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.olga.aa_app.database.ReactionDatabase;
import com.example.olga.aa_app.database.daos.MedicineDAO;
import com.example.olga.aa_app.database.daos.SymptomDAO;
import com.example.olga.aa_app.database.entities.Medicine;
import com.example.olga.aa_app.database.entities.Symptom;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class SymptomRepository implements SymptomDAO {

    private SymptomDAO symptomDAO;


    public SymptomRepository(Application application){
        ReactionDatabase database = ReactionDatabase.getInstance(application);
        symptomDAO = database.symptomDAO();
    }


    /* ----------------------------------DAO queries--------------------------------------------*/


    public Completable insert(Symptom symptom){
        return symptomDAO.insert(symptom).subscribeOn(Schedulers.io());
    }

    public Completable update(Symptom symptom){
        return symptomDAO.update(symptom).subscribeOn(Schedulers.io());
    }

    public Completable delete(Symptom symptom){
        return symptomDAO.delete(symptom).subscribeOn(Schedulers.io());
    }

    @Override
    public LiveData<List<Symptom>> getAllSymptoms() {
        return symptomDAO.getAllSymptoms();
    }

    @Override
    public Single<Symptom> getAllergyByID(int id) {
        return symptomDAO.getAllergyByID(id).subscribeOn(Schedulers.io());
    }

    @Override
    public Single<Symptom> getAllergyByName(String name) {
        return symptomDAO.getAllergyByName(name).subscribeOn(Schedulers.io());
    }

}
