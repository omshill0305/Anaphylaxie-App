package com.example.olga.aa_app.database.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.olga.aa_app.database.daos.BrandNameDAO;
import com.example.olga.aa_app.database.daos.MedicineDAO;
import com.example.olga.aa_app.database.entities.BrandName;
import com.example.olga.aa_app.database.entities.Medicine;
import com.example.olga.aa_app.database.repositorys.BrandNameRepository;
import com.example.olga.aa_app.database.repositorys.MedicineRepository;

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
public class MedicineViewModel extends AndroidViewModel implements MedicineDAO {

    private MedicineRepository repository;

    public MedicineViewModel(@NonNull Application application) {
        super(application);
        repository = new MedicineRepository(application);
    }

    public void insert(Medicine medicineRepository){
        repository.insert(medicineRepository);
    }

    public void update(Medicine medicineRepository){
        repository.update(medicineRepository);
    }

    public void delete(Medicine medicineRepository){
        repository.delete(medicineRepository);
    }

    @Override
    public Medicine getMedicineByID(int id) {
        return repository.getMedicineByID(id);
    }

    @Override
    public Medicine getMedicineByName(String name) {
        return repository.getMedicineByName(name);
    }

    @Override
    public LiveData<List<Medicine>> getAllMedicine() {
        return repository.getAllMedicine();
    }
}
