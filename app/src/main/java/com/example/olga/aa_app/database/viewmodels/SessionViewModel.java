package com.example.olga.aa_app.database.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.olga.aa_app.database.daos.SessionDAO;
import com.example.olga.aa_app.database.daos.SymptomDAO;
import com.example.olga.aa_app.database.entities.Session;
import com.example.olga.aa_app.database.entities.Symptom;
import com.example.olga.aa_app.database.repositorys.SessionRepository;
import com.example.olga.aa_app.database.repositorys.SymptomRepository;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public class SessionViewModel extends AndroidViewModel implements SessionDAO {

    private SessionRepository repository;

    public SessionViewModel(@NonNull Application application) {
        super(application);
        repository = new SessionRepository(application);
    }

    public Single<Long> insert(Session session){
        return repository.insert(session);
    }

    public Single<Integer> delete(Session session){
        return repository.delete(session);
    }

    @Override
    public LiveData<List<Session>> getAllSessions() {
        return repository.getAllSessions();
    }

}
