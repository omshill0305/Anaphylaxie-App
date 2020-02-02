package com.example.olga.aa_app.database.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.olga.aa_app.database.daos.BrandNameDAO;
import com.example.olga.aa_app.database.entities.BrandName;
import com.example.olga.aa_app.database.repositorys.BrandNameRepository;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public class BrandNameViewModel extends AndroidViewModel implements BrandNameDAO {

    private BrandNameRepository repository;

    public BrandNameViewModel(@NonNull Application application) {
        super(application);
        repository = new BrandNameRepository(application);
    }

    public Single<Long> insert(BrandName brandName){
        return repository.insert(brandName);
    }

    public Single<Integer> update(BrandName brandName){
        return repository.update(brandName);
    }

    public Single<Integer> delete(BrandName brandName){
        return repository.delete(brandName);
    }

    @Override
    public Single<BrandName> getBrandNameByID(long id) {
        return repository.getBrandNameByID(id);
    }

    @Override
    public Single<BrandName> getBrandNameByName(String name) {
        return repository.getBrandNameByName(name);
    }

    @Override
    public LiveData<List<BrandName>> getAllBrandNames() {
        return repository.getAllBrandNames();
    }
}
