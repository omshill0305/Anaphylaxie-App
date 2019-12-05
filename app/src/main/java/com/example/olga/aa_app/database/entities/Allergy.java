package com.example.olga.aa_app.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "allergy_table")
public class Allergy {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private String description;

    public Allergy(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
