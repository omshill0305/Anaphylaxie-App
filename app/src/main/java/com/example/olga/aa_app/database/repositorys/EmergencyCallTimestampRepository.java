package com.example.olga.aa_app.database.repositorys;

import android.app.Application;

import com.example.olga.aa_app.database.ReactionDatabase;
import com.example.olga.aa_app.database.daos.EmergencyCallTimestampDAO;
import com.example.olga.aa_app.database.daos.ReactionTimestampDAO;
import com.example.olga.aa_app.database.entities.EmergencyCallTimestamp;
import com.example.olga.aa_app.database.entities.ReactionTimestamp;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class EmergencyCallTimestampRepository implements EmergencyCallTimestampDAO {

    private EmergencyCallTimestampDAO emergencyCallTimestampDAO;


    public EmergencyCallTimestampRepository(Application application){
        ReactionDatabase database = ReactionDatabase.getInstance(application);
        emergencyCallTimestampDAO = database.emergencyCallTimestampDAO();
    }


    /* ----------------------------------DAO queries--------------------------------------------*/

    @Override
    public Single<Long> insert(EmergencyCallTimestamp emergencyCallTimestamp) {
        return emergencyCallTimestampDAO.insert(emergencyCallTimestamp).subscribeOn(Schedulers.io());
    }
}
