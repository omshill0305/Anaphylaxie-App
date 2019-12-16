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

/**
 * The repository class is a class that is created for every entity. It is used to wrap the queries
 * and to separate it from the DAOs. Because database operation cannot be performed directly on the
 * database itself, we will use the "databaseWriteExecutor" that we declared in the database, to
 * execute all database operations. LiveData is a exception. LiveData needs a getter in the DAO, but
 * don't need a query to get it. Simply call it to get the reference.
 */
public class MedicineRepository implements MedicineDAO {

    private MedicineDAO medicineDAO;


    public MedicineRepository(Application application){
        ReactionDatabase database = ReactionDatabase.getInstance(application);
        medicineDAO = database.medicineDAO();
    }


    /* ----------------------------------DAO queries--------------------------------------------*/


    public Completable insert(Medicine medicine){
        return medicineDAO.insert(medicine);
    }

    public Completable update(Medicine medicine){
        return medicineDAO.update(medicine);
    }

    public Completable delete(Medicine medicine){
        return medicineDAO.delete(medicine);
    }

    @Override
    public Single<Medicine> getMedicineByID(int id) {
        return medicineDAO.getMedicineByID(id);
    }

    @Override
    public Single<Medicine> getMedicineByName(String name) {

        return medicineDAO.getMedicineByName(name);
    }

    @Override
    public LiveData<List<Medicine>> getAllMedicine() {
        return medicineDAO.getAllMedicine();
    }
}
