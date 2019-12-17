package com.example.olga.aa_app.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;


@Entity(tableName = "medicine_table", indices = {@Index(value = {"medicineId"}, unique = true)})  // Don't forget to change the table name when copy and pasting
public class Medicine {

    // autoGenerate equals a serial or autoincrement
    @PrimaryKey(autoGenerate = true)
    private int medicineId;

    public String name;

    // Things needed: Constructor, Getters for all fields and one setter for ID

    public Medicine( String name) {
        this.name = name;
    }

    public int getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(int medicineId) {
        this.medicineId = medicineId;
    }
}
