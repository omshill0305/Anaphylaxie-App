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

/**
 * DAOs hold the queries for the SQLite database as interface methods. Basic queries
 * such as insert, update or delete can be annotated with the appropriate annotation
 * e.g @Insert (see below) or need to be marked as @Query( "SQL commands come here")
 */
@Dao
public interface BrandNameDAO {

    // Base Operations

    @Insert
    void insert(BrandName brandName);

    @Update
    void update(BrandName brandName);

    @Delete
    void delete(BrandName brandName);

    // Custom queries

    @Query("SELECT * FROM brandname_table WHERE brandname_id = :id")
    BrandName getBrandNameByID(int id);

    @Query("SELECT * FROM brandname_table WHERE name = :name")
    BrandName getBrandNameByName(String name);

    @Query("SELECT * FROM brandname_table")
    LiveData<List<BrandName>> getAllBrandNames();

}
