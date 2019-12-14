package com.example.olga.aa_app.database.repositorys;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.olga.aa_app.database.ReactionDatabase;
import com.example.olga.aa_app.database.daos.BrandNameDAO;
import com.example.olga.aa_app.database.entities.BrandName;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

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


    public void insert(BrandName brandName){
        ReactionDatabase.databaseWriteExecutor.execute(() ->{
            brandNameDAO.insert(brandName);
        });
    }

    public void update(BrandName brandName){
        ReactionDatabase.databaseWriteExecutor.execute(() ->{
            brandNameDAO.update(brandName);
        });
    }

    public void delete(BrandName brandName){
        ReactionDatabase.databaseWriteExecutor.execute(() ->{
            brandNameDAO.delete(brandName);
        });
    }

    @Override
    public BrandName getBrandNameByID(int id) {
        AtomicReference<BrandName> returnValue = new AtomicReference<>();

        ReactionDatabase.databaseWriteExecutor.execute(() ->{
            returnValue.set(brandNameDAO.getBrandNameByID(id));
        });

        return returnValue.get();
    }

    @Override
    public BrandName getBrandNameByName(String name) {
        AtomicReference<BrandName> returnValue = new AtomicReference<>();

        ReactionDatabase.databaseWriteExecutor.execute(() ->{
            returnValue.set(brandNameDAO.getBrandNameByName(name));
        });

        return returnValue.get();
    }

    @Override
    public LiveData<List<BrandName>> getAllBrandNames() {
        return brandNameDAO.getAllBrandNames();
    }
}
