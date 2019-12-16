package com.example.olga.aa_app.database.repositorys;

import android.app.Application;

import com.example.olga.aa_app.database.ReactionDatabase;
import com.example.olga.aa_app.database.daos.ProfileDAO;
import com.example.olga.aa_app.database.entities.Profile;

import io.reactivex.Completable;

/**
 * The repository class is a class that is created for every entity. It is used to wrap the queries
 * and to separate it from the DAOs. Because database operation cannot be performed directly on the
 * database itself, we will use the "databaseWriteExecutor" that we declared in the database, to
 * execute all database operations. LiveData is a exception. LiveData needs a getter in the DAO, but
 *  * don't need a query to get it. Simply call it to get the reference.
 */
public class ProfileRepository implements ProfileDAO {

    private ProfileDAO profileDAO;


    public ProfileRepository(Application application){
        ReactionDatabase database = ReactionDatabase.getInstance(application);
        profileDAO = database.profileDAO();
    }


    /* ----------------------------------DAO queries--------------------------------------------*/


    public Completable insert(Profile allergy) {
        return profileDAO.insert(allergy);
    }

    public Completable update(Profile allergy) {
        return profileDAO.update(allergy);
    }

    public Completable delete(Profile allergy) {
        return profileDAO.delete(allergy);
    }
}
