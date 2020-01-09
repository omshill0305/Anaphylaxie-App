package com.example.olga.aa_app.database.daos;

import androidx.room.Dao;
import androidx.room.Insert;

import com.example.olga.aa_app.database.entities.EmergencyCallTimestamp;
import com.example.olga.aa_app.database.entities.ReactionTimestamp;

import io.reactivex.Completable;


@Dao
public interface EmergencyCallTimestampDAO {

    // Base Operations

    @Insert
    Completable insert(EmergencyCallTimestamp emergencyCallTimestamp);

    // Queries

}
