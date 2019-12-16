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

/**
 * The repository class is a class that is created for every entity. It is used to wrap the queries
 * and to separate it from the DAOs. Because database operation cannot be performed directly on the
 * database itself, we will use the "databaseWriteExecutor" that we declared in the database, to
 * execute all database operations. LiveData is a exception. LiveData needs a getter in the DAO, but
 * don't need a query to get it. Simply call it to get the reference.
 */
public class AllergyRepository implements AllergyDAO{

    private AllergyDAO allergyDAO;


    public AllergyRepository(Application application){
        ReactionDatabase database = ReactionDatabase.getInstance(application);
        allergyDAO = database.allergyDAO();
    }


    /* ----------------------------------DAO queries--------------------------------------------*/


    public Completable insert(Allergy allergy){
        return allergyDAO.insert(allergy);
    }

    public Completable update(Allergy allergy){
        return allergyDAO.update(allergy);
    }

    public Completable delete(Allergy allergy){
        return allergyDAO.delete(allergy);
    }

    // LiveData gets reference from DAO without the use of databaseWriteExecutor
    public LiveData<List<Allergy>> getAllAllergies(){
        return allergyDAO.getAllAllergies();
    }

    @Override
    public Single<Allergy> getAllergyByID(int id) {             // Needs testing

        return allergyDAO.getAllergyByID(id);
    }

    @Override
    public Single<Allergy> getAllergyByName(String name) {
        return allergyDAO.getAllergyByName(name);
    }
}
