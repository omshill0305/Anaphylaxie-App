package com.example.olga.aa_app.database.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.example.olga.aa_app.database.entities.Symptom;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;


@Dao
public interface SymptomDAO {

    // Base Operations

    @Insert
    Completable insert(Symptom symptom);

    @Update
    Completable update(Symptom symptom);

    @Delete
    Completable delete(Symptom symptom);

    // Queries

    @Query("SELECT * FROM symptom_table")
    LiveData<List<Symptom>> getAllSymptoms();

    @Query("SELECT * FROM symptom_table WHERE symptomId = :id")
    Single<Symptom> getAllergyByID(int id);

    @Query("SELECT * FROM symptom_table WHERE symptomName = :name")
    Single<Symptom> getAllergyByName(String name);
}
