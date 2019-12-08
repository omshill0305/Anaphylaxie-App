package com.example.olga.aa_app.database.repositorys;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.olga.aa_app.database.ReactionDatabase;
import com.example.olga.aa_app.database.daos.AllergyDAO;
import com.example.olga.aa_app.database.entities.Allergy;

import java.util.List;

/**
 * The repository class is a class that is created for every entity. It is used to wrap the queries
 * and to separate it from the DAOs. Because database operation cannot be performed directly on the
 * database itself, we will use the "databaseWriteExecutor" that we declared in the database, to
 * execute all database operations.
 */
public class AllergyRepository {

    private AllergyDAO allergyDAO;

    private LiveData<List<Allergy>> allAllergies;

    public AllergyRepository(Application application){
        ReactionDatabase database = ReactionDatabase.getInstance(application);
        allergyDAO = database.allergyDAO();
        allAllergies = allergyDAO.getAllAllergies();
    }


    /* ----------------------------------DAO queries--------------------------------------------*/


    public void insert(Allergy allergy){
        ReactionDatabase.databaseWriteExecutor.execute(() ->{
            allergyDAO.insert(allergy);
        });
    }

    public void update(Allergy allergy){
        ReactionDatabase.databaseWriteExecutor.execute(() ->{
            allergyDAO.update(allergy);
        });
    }

    public void delete(Allergy allergy){
        ReactionDatabase.databaseWriteExecutor.execute(() ->{
            allergyDAO.delete(allergy);
        });
    }

    public LiveData<List<Allergy>> getAllAllergies(){
        return allAllergies;
    }


    //Deprecated
    /*private static class InsertTask extends AsyncTask<Allergy, Void, Void>{
        AllergyDAO allergyDAO;

        public InsertTask(AllergyDAO allergyDAO){
            this.allergyDAO = allergyDAO;
        }

        @Override
        protected Void doInBackground(Allergy... allergies) {
            allergyDAO.insert(allergies[0]);
            return null;
        }
    }*/
}
