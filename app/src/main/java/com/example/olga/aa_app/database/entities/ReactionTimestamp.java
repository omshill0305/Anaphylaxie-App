package com.example.olga.aa_app.database.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;
import static androidx.room.ForeignKey.RESTRICT;


@Entity(tableName = "reaction_timestamp_table"foreignKeys = {
        @ForeignKey(
                entity = Symptom.class,
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
        {@Index(value = "brandNameId"), @Index(value =  "medicineId")})    // Don't forget to change the table name when copy and pasting
public class ReactionTimestamp {

    // autoGenerate equals a serial or autoincrement
    @PrimaryKey(autoGenerate = true)
    private int reactionTimestampId;
    private int timestamp;

    private int symptomId;
    private int profileId;
    private int sessionId;

}
