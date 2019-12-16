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

/**
 * The repository class is a class that is created for every entity. It is used to wrap the queries
 * and to separate it from the DAOs. Because database operation cannot be performed directly on the
 * database itself, we will use the "databaseWriteExecutor" that we declared in the database, to
 * execute all database operations. LiveData is a exception. LiveData needs a getter in the DAO, but
 * don't need a query to get it. Simply call it to get the reference.
 */
public class BrandNameRepository implements BrandNameDAO {

    private BrandNameDAO brandNameDAO;


    public BrandNameRepository(Application application){
        ReactionDatabase database = ReactionDatabase.getInstance(application);
        brandNameDAO = database.brandNameDAO();
    }

    /* ----------------------------------DAO queries--------------------------------------------*/


    public Completable insert(BrandName brandName){
        return brandNameDAO.insert(brandName);
    }

    public Completable update(BrandName brandName){
        return brandNameDAO.update(brandName);
    }

    public Completable delete(BrandName brandName){
        return brandNameDAO.delete(brandName);
    }

    @Override
    public Single<BrandName> getBrandNameByID(int id) {

        return brandNameDAO.getBrandNameByID(id);
    }

    @Override
    public Single<BrandName> getBrandNameByName(String name) {

        return brandNameDAO.getBrandNameByName(name);
    }

    @Override
    public LiveData<List<BrandName>> getAllBrandNames() {
        return brandNameDAO.getAllBrandNames();
    }
}
