package com.example.olga.aa_app.database.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;
import static androidx.room.ForeignKey.RESTRICT;

@Entity(tableName = "emergencyset_table", foreignKeys = {
        @ForeignKey(
                entity = BrandName.class,
                parentColumns = "brandNameId",
                childColumns = "brandNameId",
                onDelete = RESTRICT,
                onUpdate = CASCADE
        ),
        @ForeignKey(entity = Medicine.class,
                parentColumns = "medicineId",
                childColumns = "medicineId",
                onDelete = RESTRICT,
                onUpdate = CASCADE)
}, indices =
    {@Index (value = "brandNameId"), @Index(value =  "medicineId")}) // Don't forget to change the table name when copy and pasting
public class EmergencySet {

    // autoGenerate equals a serial or autoincrement
    @PrimaryKey(autoGenerate = true)
    private int emergencySetId;

    public int brandNameId;
    public int medicineId;

    public float dosage;

    public String dosageUnit;

    // Things needed: Constructor, Getters for all fields and one setter for ID

    public EmergencySet(int brandNameId, int medicineId, float dosage, @NonNull String dosageUnit) {
        this.brandNameId = brandNameId;
        this.medicineId = medicineId;
        this.dosage = dosage;
        this.dosageUnit = dosageUnit;
    }

    public int getEmergencySetId() {
        return emergencySetId;
    }

    public void setEmergencySetId(int emergencySetId) {
        this.emergencySetId = emergencySetId;
    }
}
