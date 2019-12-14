package com.example.olga.aa_app.database.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import com.example.olga.aa_app.database.entities.Profile;

/**
 * DAOs hold the queries for the SQLite database as interface methods. Basic queries
 * such as insert, update or delete can be annotated with the appropriate annotation
 * e.g @Insert (see below) or need to be marked as @Query( "SQL commands come here")
 */
@Dao
public interface ProfileDAO {

    // Base Operations

    @Insert
    void insert(Profile profile);

    @Update
    void update(Profile profile);

    @Delete
    void delete(Profile profile);

    // Custom queries

    /*@Query("SELECT * FROM allergy_table")
    LiveData<List<Allergy>> getAllAllergies();*/

}
