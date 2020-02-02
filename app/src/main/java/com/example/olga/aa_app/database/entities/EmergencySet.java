package com.example.olga.aa_app.database.entities;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;


@Entity(tableName = "emergencyset_table", indices = {@Index(value = "emergencySetId", unique = true)}) // Don't forget to change the table name when copy and pasting
public class EmergencySet implements Serializable {

    // autoGenerate equals a serial or autoincrement
    @PrimaryKey(autoGenerate = true)
    private long emergencySetId;

    private String brandName;
    private String medicine;
    private String dosage;
    private String dosageUnit;

    // Things needed: Constructor, Getters for all fields and one setter for ID

    public EmergencySet(String brandName, String medicine, String dosage, @NonNull String dosageUnit) {
        this.brandName = brandName;
        this.medicine = medicine;
        this.dosage = dosage;
        this.dosageUnit = dosageUnit;
    }

    public long getEmergencySetId() {
        return emergencySetId;
    }

    public void setEmergencySetId(long emergencySetId) {
        this.emergencySetId = emergencySetId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getDosageUnit() {
        return dosageUnit;
    }

    public void setDosageUnit(String dosageUnit) {
        this.dosageUnit = dosageUnit;
    }
}
