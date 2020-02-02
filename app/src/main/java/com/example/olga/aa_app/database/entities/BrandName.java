package com.example.olga.aa_app.database.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;


@Entity(tableName = "brandname_table", indices = {@Index(value = "brandNameId", unique = true)})  // Don't forget to change the table name when copy and pasting
public class BrandName {

    // autoGenerate equals a serial or autoincrement

    @PrimaryKey(autoGenerate = true)
    private long brandNameId;

    public String name;

    // Things needed: Constructor, Getters for all fields and one setter for ID


    public BrandName(String name) {
        this.name = name;
    }

    public long getBrandNameId() {
        return brandNameId;
    }

    public void setBrandNameId(long brandNameId) {
        this.brandNameId = brandNameId;
    }

}
