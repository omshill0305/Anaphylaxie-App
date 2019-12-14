package com.example.olga.aa_app.database.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;
import static androidx.room.ForeignKey.RESTRICT;

/**
 * A database entity has to be annotated as @Entity for Room to recognize it as a
 * database entity. In a entity class, all member variables need to be set as fields
 * with getters and setters accordingly. The entity can be seen as a normal class, but
 * database specific fields such as a primary key have to be annotated separately.
 */
@Entity(tableName = "emergencyset_table", foreignKeys = {
        @ForeignKey(
                entity = BrandName.class,
                parentColumns = "brandname_id",
                childColumns = "brandNameId",
                onDelete = RESTRICT,
                onUpdate = CASCADE
        ),
        @ForeignKey(entity = Medicine.class,
                parentColumns = "medicine_id",
                childColumns = "medicineId",
                onDelete = RESTRICT,
                onUpdate = CASCADE)
}) // Don't forget to change the table name when copy and pasting
public class EmergencySet {

    // autoGenerate equals a serial or autoincrement
    @PrimaryKey(autoGenerate = true)
    private int emergencySetId;

    private long brandNameId;
    private long medicineId;

    private float dosage;
    private String dosageUnit;

    // Things needed: Constructor, Getters for all fields and one setter for ID

    public EmergencySet(int emergencySetId, long brandNameId, long medicineId, float dosage, String dosageUnit) {
        this.emergencySetId = emergencySetId;
        this.brandNameId = brandNameId;
        this.medicineId = medicineId;
        this.dosage = dosage;
        this.dosageUnit = dosageUnit;
    }

    public float getDosage() {
        return dosage;
    }

    public String getDosageUnit() {
        return dosageUnit;
    }

    public int getEmergencySetId() {
        return emergencySetId;
    }

    public long getBrandNameId() {
        return brandNameId;
    }

    public long getMedicineId() {
        return medicineId;
    }

    public void setEmergencySetId(int emergencySetId) {
        this.emergencySetId = emergencySetId;
    }
}
