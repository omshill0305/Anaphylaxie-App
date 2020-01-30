package com.example.olga.aa_app.database.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.olga.aa_app.database.entities.Allergy;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;


@Dao
public interface AllergyDAO {

    // Base Operations

    @Insert
    Single<Long> insert(Allergy allergy);

    @Insert
    Single<Long[]> insertAll(List<Allergy> allergies);

    @Update
    Single<Integer> update(Allergy allergy);

    @Delete
    Single<Integer> delete(Allergy allergy);

    // Queries

    @Query("SELECT * FROM allergy_table")
    LiveData<List<Allergy>> getAllAllergies();

    @Query("SELECT * FROM allergy_table WHERE allergyId = :id")
    Single<Allergy> getAllergyByID(long id);

    @Query("SELECT * FROM allergy_table WHERE name = :name")
    Single<Allergy> getAllergyByName(String name);
}
