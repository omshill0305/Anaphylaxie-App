package com.example.olga.aa_app.database.entities;

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
@Entity(tableName = "medicine_table", indices = {@Index(value = {"medicine_id"}, unique = true)})  // Don't forget to change the table name when copy and pasting
public class Medicine {

    // autoGenerate equals a serial or autoincrement
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "medicine_id")
    private int medicineId;

    private String name;

    // Things needed: Constructor, Getters for all fields and one setter for ID

    public Medicine( String name) {
        this.name = name;
    }

    public int getMedicineId() {
        return medicineId;
    }

    public String getName() {
        return name;
    }

    public void setMedicineId(int medicineId) {
        this.medicineId = medicineId;
    }
}
