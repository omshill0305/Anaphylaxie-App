package com.example.olga.aa_app.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.olga.aa_app.database.entities.Allergy;

import java.util.List;

@Dao
public interface AllergyDAO {

    // Base Operations

    @Insert
    void insert(Allergy allergy);

    @Update
    void update(Allergy allergy);

    @Delete
    void delete(Allergy allergy);

    // Custom queries
    @Query("SELECT * FROM allergy_table")
    LiveData<List<Allergy>> getAllAllergies();

}
