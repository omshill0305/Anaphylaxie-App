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

/**
 * DAOs hold the queries for the SQLite database as interface methods. Basic queries
 * such as insert, update or delete can be annotated with the appropriate annotation
 * e.g @Insert (see below) or need to be marked as @Query( "SQL commands come here")
 */
@Dao
public interface MedicineDAO {

    // Base Operations

    @Insert
    void insert(Medicine medicine);

    @Update
    void update(Medicine medicine);

    @Delete
    void delete(Medicine medicine);

    // Custom queries

    @Query("SELECT * FROM medicine_table WHERE medicine_id = :id")
    Medicine getMedicineByID(int id);

    @Query("SELECT * FROM medicine_table WHERE name = :name")
    Medicine getMedicineByName(String name);

    @Query("SELECT * FROM medicine_table")
    LiveData<List<Medicine>> getAllMedicine();
}
