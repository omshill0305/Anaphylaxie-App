package com.example.olga.aa_app.database.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.olga.aa_app.database.entities.Organsystem;
import com.example.olga.aa_app.database.entities.Symptom;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;


@Dao
public interface OrgansystemDAO {

    // Base Operations

    @Insert
    Single<Long> insert(Organsystem organsystem);

    @Update
    Single<Integer> update(Organsystem organsystem);

    @Delete
    Single<Integer> delete(Organsystem organsystem);

    // Queries

    @Query("SELECT * FROM organsystem_table")
    LiveData<List<Organsystem>> getAllOrgansystems();

    @Query("SELECT * FROM organsystem_table WHERE organsystemId = :id")
    Single<Organsystem> getOrgansystemById(long id);

    @Query("SELECT * FROM organsystem_table WHERE organsystemName = :name")
    Single<Organsystem> getOrgansystemByName(String name);
}
