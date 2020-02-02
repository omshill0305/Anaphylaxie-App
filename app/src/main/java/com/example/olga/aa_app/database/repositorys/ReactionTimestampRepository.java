package com.example.olga.aa_app.database.repositorys;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.olga.aa_app.database.ReactionDatabase;
import com.example.olga.aa_app.database.daos.ReactionTimestampDAO;
import com.example.olga.aa_app.database.daos.SetsOfProfileDAO;
import com.example.olga.aa_app.database.entities.Profile;
import com.example.olga.aa_app.database.entities.ReactionTimestamp;
import com.example.olga.aa_app.database.jointables.SetsOfProfile;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class ReactionTimestampRepository implements ReactionTimestampDAO {

    private ReactionTimestampDAO reactionTimestampDAO;


    public ReactionTimestampRepository(Application application){
        ReactionDatabase database = ReactionDatabase.getInstance(application);
        reactionTimestampDAO = database.reactionTimestampDAO();
    }


    /* ----------------------------------DAO queries--------------------------------------------*/

    @Override
    public Single<Long> insert(ReactionTimestamp reactionTimestamp) {
        return reactionTimestampDAO.insert(reactionTimestamp).subscribeOn(Schedulers.io());
    }
}
