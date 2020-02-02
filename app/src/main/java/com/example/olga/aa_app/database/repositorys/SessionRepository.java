package com.example.olga.aa_app.database.repositorys;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.olga.aa_app.database.ReactionDatabase;
import com.example.olga.aa_app.database.daos.MedicineDAO;
import com.example.olga.aa_app.database.daos.SessionDAO;
import com.example.olga.aa_app.database.entities.Medicine;
import com.example.olga.aa_app.database.entities.Session;
import com.example.olga.aa_app.database.entities.Symptom;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class SessionRepository implements SessionDAO {

    private SessionDAO sessionDAO;


    public SessionRepository(Application application){
        ReactionDatabase database = ReactionDatabase.getInstance(application);
        sessionDAO = database.sessionDAO();
    }


    /* ----------------------------------DAO queries--------------------------------------------*/

    @Override
    public Single<Long> insert(Session session) {
        return sessionDAO.insert(session).subscribeOn(Schedulers.io());
    }

    @Override
    public Single<Integer> delete(Session session) {
        return sessionDAO.delete(session).subscribeOn(Schedulers.io());
    }

    @Override
    public LiveData<List<Session>> getAllSessions() {
        return sessionDAO.getAllSessions();
    }

}
