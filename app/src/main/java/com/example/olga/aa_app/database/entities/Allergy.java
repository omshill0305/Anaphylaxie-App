package com.example.olga.aa_app.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * A database entity has to be annotated as @Entity for Room to recognize it as a
 * database entity. In a entity class, all member variables need to be set as fields
 * with getters and setters accordingly. The entity can be seen as a normal class, but
 * database specific fields such as a primary key have to be annotated separately.
 */
@Entity(tableName = "allergy_table")    // Don't forget to change the table name when copy and pasting
public class Allergy {

    // autoGenerate equals a serial or autoincrement
    @PrimaryKey(autoGenerate = true)
    private int allergyId;

    private String name;

    // Things needed: Constructor, Getters for all fields and one setter for ID

    public Allergy(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getAllergyId() {
        return allergyId;
    }

    public void setAllergyId(int allergyId) {
        this.allergyId = allergyId;
    }

    public void setName(String name) {
        this.name = name;
    }
}
