package com.example.olga.aa_app.database.repositorys;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.olga.aa_app.database.ReactionDatabase;
import com.example.olga.aa_app.database.daos.MedicineDAO;
import com.example.olga.aa_app.database.entities.Medicine;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

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


    public void insert(Medicine medicine){
        ReactionDatabase.databaseWriteExecutor.execute(() ->{
            medicineDAO.insert(medicine);
        });
    }

    public void update(Medicine medicine){
        ReactionDatabase.databaseWriteExecutor.execute(() ->{
            medicineDAO.update(medicine);
        });
    }

    public void delete(Medicine medicine){
        ReactionDatabase.databaseWriteExecutor.execute(() ->{
            medicineDAO.delete(medicine);
        });
    }

    @Override
    public Medicine getMedicineByID(int id) {
        AtomicReference<Medicine> returnValue = new AtomicReference<>();

        ReactionDatabase.databaseWriteExecutor.execute(() ->{
            returnValue.set(medicineDAO.getMedicineByID(id));
        });

        return returnValue.get();
    }

    @Override
    public Medicine getMedicineByName(String name) {
        AtomicReference<Medicine> returnValue = new AtomicReference<>();

        ReactionDatabase.databaseWriteExecutor.execute(() ->{
            returnValue.set(medicineDAO.getMedicineByName(name));
        });

        return returnValue.get();
    }

    @Override
    public LiveData<List<Medicine>> getAllMedicine() {
        return medicineDAO.getAllMedicine();
    }
}
