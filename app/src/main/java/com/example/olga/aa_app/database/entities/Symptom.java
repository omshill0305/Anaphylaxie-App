package com.example.olga.aa_app.database.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;
import static androidx.room.ForeignKey.RESTRICT;

@Entity(tableName = "symptom_table", foreignKeys = {
        @ForeignKey(
                entity = Organsystem.class,
                parentColumns = "organsystemId",
                childColumns = "organsystemId",
                onDelete = RESTRICT,
                onUpdate = CASCADE
        )
}, indices =
    {@Index (value = "organsystemId")}) // Don't forget to change the table name when copy and pasting
public class Symptom {

    // autoGenerate equals a serial or autoincrement
    @PrimaryKey(autoGenerate = true)
    private int symptomId;

    public String symptomName;
    public String description;

    public int organsystemId;

    // Things needed: Constructor, Getters for all fields and one setter for ID

    public Symptom(String symptomName, String description, int organsystemId) {
        this.symptomName = symptomName;
        this.description = description;
        this.organsystemId = organsystemId;
    }

    public int getSymptomId() {
        return symptomId;
    }

    public void setSymptomId(int symptomId) {
        this.symptomId = symptomId;
    }
}
