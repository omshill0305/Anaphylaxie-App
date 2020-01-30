package com.example.olga.aa_app.database.repositorys;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.olga.aa_app.database.ReactionDatabase;
import com.example.olga.aa_app.database.daos.AllergyDAO;
import com.example.olga.aa_app.database.entities.Allergy;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class AllergyRepository implements AllergyDAO{

    private AllergyDAO allergyDAO;


    public AllergyRepository(Application application){
        ReactionDatabase database = ReactionDatabase.getInstance(application);
        allergyDAO = database.allergyDAO();
    }


    /* ----------------------------------DAO queries--------------------------------------------*/


    public Single<Long> insert(Allergy allergy){
        return allergyDAO.insert(allergy).subscribeOn(Schedulers.io());
    }

    @Override
    public Single<Long[]> insertAll(List<Allergy> allergies) {
        return allergyDAO.insertAll(allergies).subscribeOn(Schedulers.io());
    }

    public Single<Integer> update(Allergy allergy){
        return allergyDAO.update(allergy).subscribeOn(Schedulers.io());
    }

    public Single<Integer> delete(Allergy allergy){
        return allergyDAO.delete(allergy).subscribeOn(Schedulers.io());
    }

    // LiveData gets reference from DAO without the use of databaseWriteExecutor
    public LiveData<List<Allergy>> getAllAllergies(){
        return allergyDAO.getAllAllergies();
    }

    @Override
    public Single<Allergy> getAllergyByID(long id) {
        return allergyDAO.getAllergyByID(id).subscribeOn(Schedulers.io());
    }

    @Override
    public Single<Allergy> getAllergyByName(String name) {
        return allergyDAO.getAllergyByName(name).subscribeOn(Schedulers.io());
    }
}
