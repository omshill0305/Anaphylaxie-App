package com.example.olga.aa_app.database.viewmodels;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.olga.aa_app.database.daos.AllergyDAO;
import com.example.olga.aa_app.database.entities.Allergy;
import com.example.olga.aa_app.database.repositorys.AllergyRepository;

import java.util.List;

public class AllergyViewModel extends AndroidViewModel implements AllergyDAO {

    private AllergyRepository repository;
    private LiveData<List<Allergy>> listLiveData;

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

    public LiveData<List<Allergy>> getAllAllergies() {
        return listLiveData;
    }
}
