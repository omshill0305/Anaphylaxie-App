package com.example.olga.aa_app.database.entities;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;


@Entity(tableName = "organsystem_table", indices = {@Index(value = {"organsystemId"}, unique = true)})  // Don't forget to change the table name when copy and pasting
public class Organsystem {

    // autoGenerate equals a serial or autoincrement
    @PrimaryKey(autoGenerate = true)
    private int organsystemId;

    public String organsystemName;
    public String description;

    // Things needed: Constructor, Getters for all fields and one setter for ID

    public Organsystem(String organsystemName, String description) {
        this.organsystemName = organsystemName;
        this.description = description;
    }

    public int getOrgansystemId() {
        return organsystemId;
    }

    public void setOrgansystemId(int organsystemId) {
        this.organsystemId = organsystemId;
    }
}
