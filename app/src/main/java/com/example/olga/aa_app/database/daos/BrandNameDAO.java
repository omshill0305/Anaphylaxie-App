package com.example.olga.aa_app.database.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.olga.aa_app.database.entities.BrandName;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface BrandNameDAO {

    // Base Operations

    @Insert
    Completable insert(BrandName brandName);

    @Update
    Completable update(BrandName brandName);

    @Delete
    Completable delete(BrandName brandName);

    // Custom queries

    @Query("SELECT * FROM brandname_table WHERE brandNameId = :id")
    Single<BrandName> getBrandNameByID(int id);

    @Query("SELECT * FROM brandname_table WHERE name = :name")
    Single<BrandName> getBrandNameByName(String name);

    @Query("SELECT * FROM brandname_table")
    LiveData<List<BrandName>> getAllBrandNames();

}
