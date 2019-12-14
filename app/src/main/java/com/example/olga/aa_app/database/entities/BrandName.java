package com.example.olga.aa_app.database.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * A database entity has to be annotated as @Entity for Room to recognize it as a
 * database entity. In a entity class, all member variables need to be set as fields
 * with getters and setters accordingly. The entity can be seen as a normal class, but
 * database specific fields such as a primary key have to be annotated separately.
 */
@Entity(tableName = "brandname_table", indices = {@Index(value = "brandname_id", unique = true)})  // Don't forget to change the table name when copy and pasting
public class BrandName {

    // autoGenerate equals a serial or autoincrement

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "brandname_id")
    private int brandNameId;

    private String name;

    // Things needed: Constructor, Getters for all fields and one setter for ID


    public BrandName(String name) {
        this.name = name;
    }

    public int getBrandNameId() {
        return brandNameId;
    }

    public void setBrandNameId(int brandNameId) {
        this.brandNameId = brandNameId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
