package com.example.olga.aa_app.database.daos;

import androidx.room.Dao;
import androidx.room.Insert;

import com.example.olga.aa_app.database.entities.ReactionTimestamp;

import io.reactivex.Completable;


@Dao
public interface ReactionTimestampDAO {

    // Base Operations

    @Insert
    Completable insert(ReactionTimestamp reactionTimestamp);

    // Queries

}
