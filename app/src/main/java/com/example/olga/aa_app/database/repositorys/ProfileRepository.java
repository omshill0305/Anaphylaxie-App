package com.example.olga.aa_app.database.repositorys;

import android.app.Application;

import com.example.olga.aa_app.database.ReactionDatabase;
import com.example.olga.aa_app.database.daos.ProfileDAO;
import com.example.olga.aa_app.database.entities.Profile;

/**
 * The repository class is a class that is created for every entity. It is used to wrap the queries
 * and to separate it from the DAOs. Because database operation cannot be performed directly on the
 * database itself, we will use the "databaseWriteExecutor" that we declared in the database, to
 * execute all database operations.
 */
public class ProfileRepository implements ProfileDAO {

    private ProfileDAO profileDAO;


    public ProfileRepository(Application application){
        ReactionDatabase database = ReactionDatabase.getInstance(application);
        profileDAO = database.profileDAO();
    }


    /* ----------------------------------DAO queries--------------------------------------------*/


    public void insert(Profile allergy) {
        ReactionDatabase.databaseWriteExecutor.execute(() ->{
            profileDAO.insert(allergy);
        });
    }

    public void update(Profile allergy) {
        ReactionDatabase.databaseWriteExecutor.execute(() ->{
            profileDAO.update(allergy);
        });
    }

    public void delete(Profile allergy) {
        ReactionDatabase.databaseWriteExecutor.execute(() ->{
            profileDAO.delete(allergy);
        });
    }
}
