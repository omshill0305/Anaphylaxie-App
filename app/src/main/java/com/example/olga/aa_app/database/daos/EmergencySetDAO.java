package com.example.olga.aa_app.database.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.olga.aa_app.database.entities.EmergencySet;

import java.util.List;

import io.reactivex.Single;


@Dao
public interface EmergencySetDAO {

    // Base Operations

    @Insert
    Single<Long> insert(EmergencySet emergencySet);

    @Update
    Single<Integer> update(EmergencySet emergencySet);

    @Delete
    Single<Integer> delete(EmergencySet emergencySet);

    // Custom queries
    @Query("SELECT * FROM emergencyset_table")
    LiveData<List<EmergencySet>> getAllEmergencySets();
}
