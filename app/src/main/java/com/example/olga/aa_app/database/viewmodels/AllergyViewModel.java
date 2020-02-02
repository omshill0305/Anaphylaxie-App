package com.example.olga.aa_app.database.viewmodels;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.olga.aa_app.database.daos.AllergyDAO;
import com.example.olga.aa_app.database.entities.Allergy;
import com.example.olga.aa_app.database.repositorys.AllergyRepository;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public class AllergyViewModel extends AndroidViewModel implements AllergyDAO {

    private AllergyRepository repository;

    public AllergyViewModel(@NonNull Application application) {
        super(application);
        repository = new AllergyRepository(application);
    }

    public Single<Long> insert(Allergy allergy){
        return repository.insert(allergy);
    }

    @Override
    public Single<Long[]> insertAll(List<Allergy> allergies) {
        return repository.insertAll(allergies);
    }

    public Single<Integer> update(Allergy allergy){
        return repository.update(allergy);
    }

    public Single<Integer> delete(Allergy allergy){
        return repository.delete(allergy);
    }

    public LiveData<List<Allergy>> getAllAllergies() {  // Only returns referenced live data
        return repository.getAllAllergies();
    }

    public Single<Allergy> getAllergyByID(long id) {
        return repository.getAllergyByID(id);
    }

    public Single<Allergy> getAllergyByName(String name) {
        return repository.getAllergyByName(name);
    }

    @Override
    public Completable clearAllergies() {
        return repository.clearAllergies();
    }
}
