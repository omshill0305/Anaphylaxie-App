package com.example.olga.aa_app.database.repositorys;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.olga.aa_app.database.ReactionDatabase;
import com.example.olga.aa_app.database.daos.ProfileDAO;
import com.example.olga.aa_app.database.entities.Allergy;
import com.example.olga.aa_app.database.entities.EmergencySet;
import com.example.olga.aa_app.database.entities.Profile;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ProfileRepository implements ProfileDAO {

    private ProfileDAO profileDAO;


    public ProfileRepository(Application application){
        ReactionDatabase database = ReactionDatabase.getInstance(application);
        profileDAO = database.profileDAO();
    }


    /* ----------------------------------DAO queries--------------------------------------------*/


    public Single<Long> insert(Profile allergy) {
        return profileDAO.insert(allergy).subscribeOn(Schedulers.io());
    }

    public Single<Integer> update(Profile allergy) {
        return profileDAO.update(allergy).subscribeOn(Schedulers.io());
    }

    public Single<Integer> delete(Profile allergy) {
        return profileDAO.delete(allergy).subscribeOn(Schedulers.io());
    }

    @Override
    public LiveData<List<Profile>> getAllProfiles() {
        return profileDAO.getAllProfiles();
    }

    @Override
    public Single<List<EmergencySet>> getAllEmergencySetsOfProfileByProfileId(long profileId) {
        return profileDAO.getAllEmergencySetsOfProfileByProfileId(profileId);
    }

    @Override
    public Single<List<Allergy>> getAllAllergiesOfProfileByProfileId(long profileId) {
        return profileDAO.getAllAllergiesOfProfileByProfileId(profileId);
    }

    @Override
    public Single<Profile> getProfileByProfileId(long profileId) {
        return profileDAO.getProfileByProfileId(profileId).subscribeOn(Schedulers.io());
    }

    @Override
    public Completable deleteAllEntries() {
        return profileDAO.deleteAllEntries().subscribeOn(Schedulers.io());
    }
}
