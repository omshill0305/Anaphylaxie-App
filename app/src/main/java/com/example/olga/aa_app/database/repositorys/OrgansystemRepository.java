package com.example.olga.aa_app.database.repositorys;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.olga.aa_app.database.ReactionDatabase;
import com.example.olga.aa_app.database.daos.OrgansystemDAO;
import com.example.olga.aa_app.database.daos.SymptomDAO;
import com.example.olga.aa_app.database.entities.Organsystem;
import com.example.olga.aa_app.database.entities.Symptom;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class OrgansystemRepository implements OrgansystemDAO {

    private OrgansystemDAO organsystemDAO;


    public OrgansystemRepository(Application application){
        ReactionDatabase database = ReactionDatabase.getInstance(application);
        organsystemDAO = database.organsystemDAO();
    }


    /* ----------------------------------DAO queries--------------------------------------------*/


    public Single<Long> insert(Organsystem organsystem){
        return organsystemDAO.insert(organsystem).subscribeOn(Schedulers.io());
    }

    public Single<Integer> update(Organsystem organsystem){
        return organsystemDAO.update(organsystem).subscribeOn(Schedulers.io());
    }

    public Single<Integer> delete(Organsystem organsystem){
        return organsystemDAO.delete(organsystem).subscribeOn(Schedulers.io());
    }

    @Override
    public LiveData<List<Organsystem>> getAllOrgansystems() {
        return organsystemDAO.getAllOrgansystems();
    }

    @Override
    public Single<Organsystem> getOrgansystemById(long id) {
        return organsystemDAO.getOrgansystemById(id).subscribeOn(Schedulers.io());
    }

    @Override
    public Single<Organsystem> getOrgansystemByName(String name) {
        return organsystemDAO.getOrgansystemByName(name).subscribeOn(Schedulers.io());
    }

}
