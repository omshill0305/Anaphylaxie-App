package com.example.olga.aa_app.database.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.olga.aa_app.database.daos.AllergiesOfProfileDAO;
import com.example.olga.aa_app.database.daos.SetsOfProfileDAO;
import com.example.olga.aa_app.database.entities.Profile;
import com.example.olga.aa_app.database.jointables.AllergiesOfProfile;
import com.example.olga.aa_app.database.jointables.SetsOfProfile;
import com.example.olga.aa_app.database.repositorys.AllergiesOfProfileRepository;
import com.example.olga.aa_app.database.repositorys.SetsOfProfileRepository;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public class AllergiesOfProfileViewModel extends AndroidViewModel implements AllergiesOfProfileDAO {

    private AllergiesOfProfileRepository repository;

    public AllergiesOfProfileViewModel(@NonNull Application application) {
        super(application);
        repository = new AllergiesOfProfileRepository(application);
    }

    @Override
    public Single<Long> insert(AllergiesOfProfile allergiesOfProfile) {
        return repository.insert(allergiesOfProfile);
    }

    @Override
    public Completable clearAll() {
        return repository.clearAll();
    }
}
