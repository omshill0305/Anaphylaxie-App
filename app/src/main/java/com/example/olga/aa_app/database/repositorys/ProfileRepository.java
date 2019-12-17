package com.example.olga.aa_app.database.repositorys;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.olga.aa_app.database.ReactionDatabase;
import com.example.olga.aa_app.database.daos.ProfileDAO;
import com.example.olga.aa_app.database.entities.Profile;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ProfileRepository implements ProfileDAO {

    private ProfileDAO profileDAO;


    public ProfileRepository(Application application){
        ReactionDatabase database = ReactionDatabase.getInstance(application);
        profileDAO = database.profileDAO();
    }


    /* ----------------------------------DAO queries--------------------------------------------*/


    public Completable insert(Profile allergy) {
        return profileDAO.insert(allergy).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Completable update(Profile allergy) {
        return profileDAO.update(allergy).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Completable delete(Profile allergy) {
        return profileDAO.delete(allergy).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public LiveData<List<Profile>> getAllProfiles() {
        return profileDAO.getAllProfiles();
    }
}
