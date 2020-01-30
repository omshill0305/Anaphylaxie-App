package com.example.olga.aa_app.database.daos;

import androidx.room.Dao;
import androidx.room.Insert;

import com.example.olga.aa_app.database.entities.EmergencyCallTimestamp;
import com.example.olga.aa_app.database.entities.ReactionTimestamp;

import io.reactivex.Completable;
import io.reactivex.Single;


@Dao
public interface EmergencyCallTimestampDAO {

    // Base Operations

    @Insert
    Single<Long> insert(EmergencyCallTimestamp emergencyCallTimestamp);

    // Queries

}
