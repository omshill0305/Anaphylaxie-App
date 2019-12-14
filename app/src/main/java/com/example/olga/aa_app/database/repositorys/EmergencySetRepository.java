package com.example.olga.aa_app.database.repositorys;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.olga.aa_app.database.ReactionDatabase;
import com.example.olga.aa_app.database.daos.EmergencySetDAO;
import com.example.olga.aa_app.database.entities.Allergy;
import com.example.olga.aa_app.database.entities.EmergencySet;

import java.util.List;

/**
 * The repository class is a class that is created for every entity. It is used to wrap the queries
 * and to separate it from the DAOs. Because database operation cannot be performed directly on the
 * database itself, we will use the "databaseWriteExecutor" that we declared in the database, to
 * execute all database operations. LiveData is a exception. LiveData needs a getter in the DAO, but
 *  * don't need a query to get it. Simply call it to get the reference.
 */
public class EmergencySetRepository implements EmergencySetDAO{

    private EmergencySetDAO emergencySetDAO;


    public EmergencySetRepository(Application application){
        ReactionDatabase database = ReactionDatabase.getInstance(application);
        emergencySetDAO = database.emergencySetDAO();
    }


    /* ----------------------------------DAO queries--------------------------------------------*/


    public void insert(EmergencySet emergencySet){
        ReactionDatabase.databaseWriteExecutor.execute(() ->{
            emergencySetDAO.insert(emergencySet);
        });
    }

    public void update(EmergencySet emergencySet){
        ReactionDatabase.databaseWriteExecutor.execute(() ->{
            emergencySetDAO.update(emergencySet);
        });
    }

    public void delete(EmergencySet emergencySet){
        ReactionDatabase.databaseWriteExecutor.execute(() ->{
            emergencySetDAO.delete(emergencySet);
        });
    }

    @Override
    public LiveData<List<EmergencySet>> getAllEmergencySets() {
        return emergencySetDAO.getAllEmergencySets();
    }
}
