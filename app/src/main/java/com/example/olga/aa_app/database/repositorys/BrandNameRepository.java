package com.example.olga.aa_app.database.repositorys;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.olga.aa_app.database.ReactionDatabase;
import com.example.olga.aa_app.database.daos.BrandNameDAO;
import com.example.olga.aa_app.database.entities.BrandName;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class BrandNameRepository implements BrandNameDAO {

    private BrandNameDAO brandNameDAO;


    public BrandNameRepository(Application application){
        ReactionDatabase database = ReactionDatabase.getInstance(application);
        brandNameDAO = database.brandNameDAO();
    }

    /* ----------------------------------DAO queries--------------------------------------------*/


    public Completable insert(BrandName brandName){
        return brandNameDAO.insert(brandName).subscribeOn(Schedulers.io());
    }

    public Completable update(BrandName brandName){
        return brandNameDAO.update(brandName).subscribeOn(Schedulers.io());
    }

    public Completable delete(BrandName brandName){
        return brandNameDAO.delete(brandName).subscribeOn(Schedulers.io());
    }

    @Override
    public Single<BrandName> getBrandNameByID(int id) {

        return brandNameDAO.getBrandNameByID(id).subscribeOn(Schedulers.io());
    }

    @Override
    public Single<BrandName> getBrandNameByName(String name) {

        return brandNameDAO.getBrandNameByName(name).subscribeOn(Schedulers.io());
    }

    @Override
    public LiveData<List<BrandName>> getAllBrandNames() {
        return brandNameDAO.getAllBrandNames();
    }
}
