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

    public Completable insert(BrandName brandName){
        return repository.insert(brandName);
    }

    public Completable update(BrandName brandName){
        return repository.update(brandName);
    }

    public Completable delete(BrandName brandName){
        return repository.delete(brandName);
    }

    @Override
    public Single<BrandName> getBrandNameByID(int id) {
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