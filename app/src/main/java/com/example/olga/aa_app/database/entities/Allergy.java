package com.example.olga.aa_app.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "allergy_table")    // Don't forget to change the table name when copy and pasting
public class Allergy {

    // autoGenerate equals a serial or autoincrement
    @PrimaryKey(autoGenerate = true)
    private int allergyId;

    public String name;

    // Things needed: Constructor, Getters for all fields and one setter for ID

    public Allergy(String name) {
        this.name = name;
    }

    public int getAllergyId() {
        return allergyId;
    }

    public void setAllergyId(int allergyId) {
        this.allergyId = allergyId;
    }

}
