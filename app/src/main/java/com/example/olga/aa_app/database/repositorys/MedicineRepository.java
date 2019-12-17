package com.example.olga.aa_app.database.repositorys;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.olga.aa_app.database.ReactionDatabase;
import com.example.olga.aa_app.database.daos.MedicineDAO;
import com.example.olga.aa_app.database.entities.Medicine;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class MedicineRepository implements MedicineDAO {

    private MedicineDAO medicineDAO;


    public MedicineRepository(Application application){
        ReactionDatabase database = ReactionDatabase.getInstance(application);
        medicineDAO = database.medicineDAO();
    }


    /* ----------------------------------DAO queries--------------------------------------------*/


    public Completable insert(Medicine medicine){
        return medicineDAO.insert(medicine).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Completable update(Medicine medicine){
        return medicineDAO.update(medicine).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Completable delete(Medicine medicine){
        return medicineDAO.delete(medicine).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<Medicine> getMedicineByID(int id) {
        return medicineDAO.getMedicineByID(id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<Medicine> getMedicineByName(String name) {
        return medicineDAO.getMedicineByName(name).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public LiveData<List<Medicine>> getAllMedicine() {
        return medicineDAO.getAllMedicine();
    }
}
