package com.example.olga.aa_app.database.repositorys;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.olga.aa_app.database.ReactionDatabase;
import com.example.olga.aa_app.database.daos.AllergiesOfProfileDAO;
import com.example.olga.aa_app.database.daos.AllergyDAO;
import com.example.olga.aa_app.database.daos.SetsOfProfileDAO;
import com.example.olga.aa_app.database.entities.Profile;
import com.example.olga.aa_app.database.jointables.AllergiesOfProfile;
import com.example.olga.aa_app.database.jointables.SetsOfProfile;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class AllergiesOfProfileRepository implements AllergiesOfProfileDAO {

    private AllergiesOfProfileDAO alleriesOfProfileDAO;


    public AllergiesOfProfileRepository(Application application){
        ReactionDatabase database = ReactionDatabase.getInstance(application);
        alleriesOfProfileDAO = database.allergiesOfProfileDAO();
    }


    /* ----------------------------------DAO queries--------------------------------------------*/

    @Override
    public Single<Long> insert(AllergiesOfProfile allergiesOfProfile) {
        return alleriesOfProfileDAO.insert(allergiesOfProfile).subscribeOn(Schedulers.io());
    }

    @Override
    public Completable clearAll() {
        return alleriesOfProfileDAO.clearAll().subscribeOn(Schedulers.io());
    }
}
