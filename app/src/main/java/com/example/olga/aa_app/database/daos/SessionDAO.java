package com.example.olga.aa_app.database.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.olga.aa_app.database.entities.Session;
import com.example.olga.aa_app.database.entities.Symptom;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;


@Dao
public interface SessionDAO {

    // Base Operations

    @Insert
    Completable insert(Session session);

    @Delete
    Completable delete(Session session);

    // Queries

    @Query("SELECT * FROM session_table")
    LiveData<List<Session>> getAllSessions();

}
