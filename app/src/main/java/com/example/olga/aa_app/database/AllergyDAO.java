package com.example.olga.aa_app.database;

import androidx.room.Dao;
import androidx.room.Insert;

import com.example.olga.aa_app.database.entities.Allergy;

@Dao
public interface AllergyDAO {

    // Base Operations

    @Insert
    void insert(Allergy allergy);

    @Insert
    void update(Allergy allergy);

    @Insert
    void delete(Allergy allergy);

    // Custom queries
}
