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

import io.reactivex.Completable;
import io.reactivex.Single;


public class MedicineViewModel extends AndroidViewModel implements MedicineDAO {

    private MedicineRepository repository;

    public MedicineViewModel(@NonNull Application application) {
        super(application);
        repository = new MedicineRepository(application);
    }

    public Completable insert(Medicine medicineRepository){
        return repository.insert(medicineRepository);
    }

    public Completable update(Medicine medicineRepository){
        return repository.update(medicineRepository);
    }

    public Completable delete(Medicine medicineRepository){
        return repository.delete(medicineRepository);
    }

    @Override
    public Single<Medicine> getMedicineByID(int id) {
        return repository.getMedicineByID(id);
    }

    @Override
    public Single<Medicine> getMedicineByName(String name) {
        return repository.getMedicineByName(name);
    }

    @Override
    public LiveData<List<Medicine>> getAllMedicine() {
        return repository.getAllMedicine();
    }
}
