package com.example.olga.aa_app.database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.olga.aa_app.database.entities.Allergy;

import java.lang.ref.WeakReference;
import java.util.List;

public class AllergyRepository {

    private AllergyDAO allergyDAO;

    private LiveData<List<Allergy>> allAllergies;

    public AllergyRepository(Application application){
        ReactionDatabase database = ReactionDatabase.getInstance(application);
        allergyDAO = database.allergyDAO();
        allAllergies = allergyDAO.getAllAllergies();
    }


    public void insert(final Allergy allergy){

    }

    public void update(final Allergy allergy){
    }

    public void delete(final Allergy allergy){

    }

    public LiveData<List<Allergy>> getAllAllergies(){
        return null;
    }

    // Async tasks

    private static class InsertTask extends AsyncTask<Allergy, Void, Void>{
        AllergyDAO allergyDAO;

        public InsertTask(AllergyDAO allergyDAO){
            this.allergyDAO = allergyDAO;
        }

        @Override
        protected Void doInBackground(Allergy... allergies) {
            allergyDAO.insert(allergies[0]);
            return null;
        }
    }

    private static class UpdateTask extends AsyncTask<Allergy, Void, Void>{
        AllergyDAO allergyDAO;

        public UpdateTask(AllergyDAO allergyDAO){
            this.allergyDAO = allergyDAO;
        }

        @Override
        protected Void doInBackground(Allergy... allergies) {
            allergyDAO.update(allergies[0]);
            return null;
        }
    }

    private static class DeleteTask extends AsyncTask<Allergy, Void, Void>{
        AllergyDAO allergyDAO;

        public DeleteTask(AllergyDAO allergyDAO){
            this.allergyDAO = allergyDAO;
        }

        @Override
        protected Void doInBackground(Allergy... allergies) {
            allergyDAO.delete(allergies[0]);
            return null;
        }
    }

    // Not sure if this will be used

    /*private static class RetrieveAllAllergiesTask extends AsyncTask<Void, Void, LiveData<List<Allergy>>>{
        private AllergyDAO allergyDAO;

        private WeakReference<AllergyRepository> repositoryWeakReference;
        RetrieveAllAllergiesTask(AllergyRepository context){
            repositoryWeakReference = new WeakReference<>(context);
        }

        public RetrieveAllAllergiesTask(AllergyDAO allergyDAO){
            this.allergyDAO = allergyDAO;
        }

        @Override
        protected void onPostExecute(LiveData<List<Allergy>> listLiveData) {

            AllergyRepository allergyRepository = repositoryWeakReference.get();
            if( allergyRepository == null ) return;

            allergyRepository.allAllergies = listLiveData;
        }

        @Override
        protected LiveData<List<Allergy>> doInBackground(Void... voids) {

            return allergyDAO.getAllAllergies();
        }


    }*/
}
