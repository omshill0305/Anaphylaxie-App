package com.example.olga.aa_app.database.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.olga.aa_app.database.entities.Profile;

import java.util.List;

import io.reactivex.Completable;

@Dao
public interface ProfileDAO {

    // Base Operations

    @Insert
    Completable insert(Profile profile);

    @Update
    Completable update(Profile profile);

    @Delete
    Completable delete(Profile profile);

    // Custom queries

    @Query("SELECT * FROM profile_table")
    LiveData<List<Profile>> getAllProfiles();

}
