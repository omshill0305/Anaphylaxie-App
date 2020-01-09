package com.example.olga.aa_app.database.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.olga.aa_app.database.daos.OrgansystemDAO;
import com.example.olga.aa_app.database.daos.SymptomDAO;
import com.example.olga.aa_app.database.entities.Organsystem;
import com.example.olga.aa_app.database.entities.Symptom;
import com.example.olga.aa_app.database.repositorys.OrgansystemRepository;
import com.example.olga.aa_app.database.repositorys.SymptomRepository;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public class OrgansystemViewModel extends AndroidViewModel implements OrgansystemDAO {

    private OrgansystemRepository repository;

    public OrgansystemViewModel(@NonNull Application application) {
        super(application);
        repository = new OrgansystemRepository(application);
    }

    public Completable insert(Organsystem organsystem){
        return repository.insert(organsystem);
    }

    public Completable update(Organsystem organsystem){
        return repository.update(organsystem);
    }

    public Completable delete(Organsystem organsystem){
        return repository.delete(organsystem);
    }

    public LiveData<List<Organsystem>> getAllOrgansystems() {
        return repository.getAllOrgansystems();
    }

    public Single<Organsystem> getOrgansystemById(int id) {
        return repository.getOrgansystemById(id);
    }

    public Single<Organsystem> getOrgansystemByName(String name) {
        return repository.getOrgansystemByName(name);
    }
}
