package com.example.olga.aa_app.database.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.olga.aa_app.database.entities.Allergy;
import com.example.olga.aa_app.database.entities.EmergencySet;

import java.util.List;

/**
 * DAOs hold the queries for the SQLite database as interface methods. Basic queries
 * such as insert, update or delete can be annotated with the appropriate annotation
 * e.g @Insert (see below) or need to be marked as @Query( "SQL commands come here")
 */
@Dao
public interface EmergencySetDAO {

    // Base Operations

    @Insert
    void insert(EmergencySet emergencySet);

    @Update
    void update(EmergencySet emergencySet);

    @Delete
    void delete(EmergencySet emergencySet);

    // Custom queries

    /*@Query("SELECT * FROM allergy_table")
    LiveData<List<Allergy>> getAllAllergies();*/

}
