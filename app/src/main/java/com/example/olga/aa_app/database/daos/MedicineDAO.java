package com.example.olga.aa_app.database.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.olga.aa_app.database.entities.Medicine;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;


@Dao
public interface MedicineDAO {

    // Base Operations

    @Insert
    Single<Long> insert(Medicine medicine);

    @Update
    Single<Integer> update(Medicine medicine);

    @Delete
    Single<Integer> delete(Medicine medicine);

    // Custom queries

    @Query("SELECT * FROM medicine_table WHERE medicineId = :id")
    Single<Medicine> getMedicineByID(long id);

    @Query("SELECT * FROM medicine_table WHERE name = :name")
    Single<Medicine> getMedicineByName(String name);

    @Query("SELECT * FROM medicine_table")
    LiveData<List<Medicine>> getAllMedicine();
}
