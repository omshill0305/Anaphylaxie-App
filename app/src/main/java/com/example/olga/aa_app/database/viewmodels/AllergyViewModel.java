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

/**
 * The ViewModel is a wrapper class for the repositories. The main purpose of this class is to call
 * methods from the repositories and provide the data that the user needs. The ViewModel exists, to
 * separate obscure the repository class and the underlying structure and classes. This is the class
 * that the Activities and Fragments will be interacting with. Again, LiveData has to be called and
 * referenced just once in the repository, as its contents are updated automatically and do not need
 * to be called again with a queries, this is why LiveData only has to be referenced once, and only
 * returned later.
 */
public class AllergyViewModel extends AndroidViewModel implements AllergyDAO {

    private AllergyRepository repository;

    public AllergyViewModel(@NonNull Application application) {
        super(application);
        repository = new AllergyRepository(application);
    }

    public Completable insert(Allergy allergy){
        return repository.insert(allergy);
    }

    public Completable update(Allergy allergy){
        return repository.update(allergy);
    }

    public Completable delete(Allergy allergy){
        return repository.delete(allergy);
    }

    public LiveData<List<Allergy>> getAllAllergies() {  // Only returns referenced live data
        return repository.getAllAllergies();
    }

    @Override
    public Single<Allergy> getAllergyByID(int id) {
        return repository.getAllergyByID(id);
    }

    @Override
    public Single<Allergy> getAllergyByName(String name) {
        return repository.getAllergyByName(name);
    }
}
