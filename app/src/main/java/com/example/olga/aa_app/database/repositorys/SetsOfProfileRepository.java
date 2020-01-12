package com.example.olga.aa_app.database.repositorys;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.olga.aa_app.database.ReactionDatabase;
import com.example.olga.aa_app.database.daos.AllergyDAO;
import com.example.olga.aa_app.database.daos.SetsOfProfileDAO;
import com.example.olga.aa_app.database.entities.Allergy;
import com.example.olga.aa_app.database.entities.Profile;
import com.example.olga.aa_app.database.jointables.SetsOfProfile;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class SetsOfProfileRepository implements SetsOfProfileDAO {

    private SetsOfProfileDAO setsOfProfileDAO;


    public SetsOfProfileRepository(Application application){
        ReactionDatabase database = ReactionDatabase.getInstance(application);
        setsOfProfileDAO = database.setsOfProfileDAO();
    }


    /* ----------------------------------DAO queries--------------------------------------------*/

    @Override
    public Completable insert(SetsOfProfile setsOfProfile) {
        return setsOfProfileDAO.insert(setsOfProfile).subscribeOn(Schedulers.io());
    }

    @Override
    public LiveData<List<Profile>> getProfilesWithEmergencySet(int emergencySetId) {
        return setsOfProfileDAO.getProfilesWithEmergencySet(emergencySetId);
    }

    @Override
    public LiveData<List<Profile>> getEmergencySetsOfProfile(int profileId) {
        return setsOfProfileDAO.getEmergencySetsOfProfile(profileId);
    }
}
