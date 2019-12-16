package com.example.olga.aa_app.database.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.olga.aa_app.database.entities.Allergy;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * DAOs hold the queries for the SQLite database as interface methods. Basic queries
 * such as insert, update or delete can be annotated with the appropriate annotation
 * e.g @Insert (see below) or need to be marked as @Query
 */
@Dao
public interface AllergyDAO {

    // Base Operations

    @Insert
    Completable insert(Allergy allergy);

    @Update
    Completable update(Allergy allergy);

    @Delete
    Completable delete(Allergy allergy);

    // Queries

    @Query("SELECT * FROM allergy_table")
    LiveData<List<Allergy>> getAllAllergies();

    @Query("SELECT * FROM allergy_table WHERE allergyId = :id")
    Single<Allergy> getAllergyByID(int id);

    @Query("SELECT * FROM allergy_table WHERE name = :name")
    Single<Allergy> getAllergyByName(String name);
}
