package com.example.olga.aa_app.database.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.olga.aa_app.database.entities.Profile;
import com.example.olga.aa_app.database.jointables.AllergiesOfProfile;
import com.example.olga.aa_app.database.jointables.SetsOfProfile;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;


@Dao
public interface AllergiesOfProfileDAO {

    @Insert
    Single<Long> insert(AllergiesOfProfile allergiesOfProfile);

    @Query("DELETE FROM allergies_of_profiles_table")
    Completable clearAll();

}
