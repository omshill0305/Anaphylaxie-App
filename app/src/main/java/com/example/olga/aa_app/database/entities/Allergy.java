package com.example.olga.aa_app.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;


@Entity(tableName = "allergy_table")    // Don't forget to change the table name when copy and pasting
public class Allergy implements Serializable {

    // autoGenerate equals a serial or autoincrement
    @PrimaryKey(autoGenerate = true)
    private long allergyId;

    public String name;

    // Things needed: Constructor, Getters for all fields and one setter for ID

    public Allergy(String name) {
        this.name = name;
    }

    public long getAllergyId() {
        return allergyId;
    }

    public void setAllergyId(long allergyId) {
        this.allergyId = allergyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return name;
    }
}
