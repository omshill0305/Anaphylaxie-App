package com.example.olga.aa_app.database.viewmodels;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.olga.aa_app.database.daos.AllergyDAO;
import com.example.olga.aa_app.database.entities.Allergy;
import com.example.olga.aa_app.database.repositorys.AllergyRepository;

import java.util.List;

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
    private LiveData<List<Allergy>> listLiveData;   // LiveData reference

    public AllergyViewModel(@NonNull Application application) {
        super(application);
        repository = new AllergyRepository(application);
        listLiveData = repository.getAllAllergies();
    }

    public void insert(Allergy allergy){
        repository.insert(allergy);
    }

    public void update(Allergy allergy){
        repository.update(allergy);
    }

    public void delete(Allergy allergy){
        repository.delete(allergy);
    }

    public LiveData<List<Allergy>> getAllAllergies() {  // Only returns referenced live data
        return listLiveData;
    }

    @Override
    public Allergy getAllergyByID(int id) {
        return repository.getAllergyByID(id);
    }

    @Override
    public Allergy getAllergyByName(String name) {
        return repository.getAllergyByName(name);
    }
}
