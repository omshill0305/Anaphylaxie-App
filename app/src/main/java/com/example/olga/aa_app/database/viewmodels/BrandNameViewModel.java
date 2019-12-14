package com.example.olga.aa_app.database.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.olga.aa_app.database.daos.BrandNameDAO;
import com.example.olga.aa_app.database.entities.BrandName;
import com.example.olga.aa_app.database.repositorys.BrandNameRepository;

import java.util.List;

/**
 * The ViewModel is a wrapper class for the repositories. The main purpose of this class is to call
 * methods from the repositories and provide the data that the user needs. The ViewModel exists, to
 * separate obscure the repository class and the underlying structure and classes. This is the class
 * that the Activities and Fragments will be interacting with. Again, LiveData has to be called and
 * referenced just once in the repository, as its contents are updated automatically and do not need
 * to be called again with a queries, this is why LiveData only has to be referenced once, and only
 * returned later.
 */
public class BrandNameViewModel extends AndroidViewModel implements BrandNameDAO {

    private BrandNameRepository repository;

    public BrandNameViewModel(@NonNull Application application) {
        super(application);
        repository = new BrandNameRepository(application);
    }

    public void insert(BrandName brandName){
        repository.insert(brandName);
    }

    public void update(BrandName brandName){
        repository.update(brandName);
    }

    public void delete(BrandName brandName){
        repository.delete(brandName);
    }

    @Override
    public BrandName getBrandNameByID(int id) {
        return repository.getBrandNameByID(id);
    }

    @Override
    public BrandName getBrandNameByName(String name) {
        return repository.getBrandNameByName(name);
    }

    @Override
    public LiveData<List<BrandName>> getAllBrandNames() {
        return repository.getAllBrandNames();
    }
}
