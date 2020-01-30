package com.example.olga.aa_app.database.repositorys;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.olga.aa_app.database.ReactionDatabase;
import com.example.olga.aa_app.database.daos.EmergencySetDAO;
import com.example.olga.aa_app.database.entities.Allergy;
import com.example.olga.aa_app.database.entities.EmergencySet;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class EmergencySetRepository implements EmergencySetDAO{

    private EmergencySetDAO emergencySetDAO;


    public EmergencySetRepository(Application application){
        ReactionDatabase database = ReactionDatabase.getInstance(application);
        emergencySetDAO = database.emergencySetDAO();
    }


    /* ----------------------------------DAO queries--------------------------------------------*/


    public Single<Long> insert(EmergencySet emergencySet){
        return emergencySetDAO.insert(emergencySet).subscribeOn(Schedulers.io());
    }

    public Single<Integer> update(EmergencySet emergencySet){
        return emergencySetDAO.update(emergencySet).subscribeOn(Schedulers.io());
    }

    public Single<Integer> delete(EmergencySet emergencySet){
        return emergencySetDAO.delete(emergencySet).subscribeOn(Schedulers.io());
    }

    @Override
    public LiveData<List<EmergencySet>> getAllEmergencySets() {
        return emergencySetDAO.getAllEmergencySets();
    }
}
