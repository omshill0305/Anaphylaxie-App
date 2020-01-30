package com.example.olga.aa_app.database.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;


import java.sql.Timestamp;

import static androidx.room.ForeignKey.CASCADE;
import static androidx.room.ForeignKey.RESTRICT;


@Entity(tableName = "reaction_timestamp_table", foreignKeys = {
        @ForeignKey(
                entity = Symptom.class,
                parentColumns = "symptomId",
                childColumns = "symptomId",
                onDelete = RESTRICT,
                onUpdate = CASCADE
        ),
        @ForeignKey(entity = Profile.class,
                parentColumns = "profileId",
                childColumns = "profileId",
                onDelete = RESTRICT,
                onUpdate = CASCADE
        ),
        @ForeignKey(entity = Session.class,
                parentColumns = "sessionId",
                childColumns = "sessionId",
                onDelete = RESTRICT,
                onUpdate = RESTRICT)
}, indices =
        {@Index(value = "symptomId"), @Index(value =  "profileId"), @Index(value =  "sessionId")})    // Don't forget to change the table name when copy and pasting
public class ReactionTimestamp {

    @PrimaryKey(autoGenerate = true)
    private long reactionTimestampId;
    private Timestamp timestamp;

    private int symptomId;
    private int profileId;
    private int sessionId;

    public long getReactionTimestampId() {
        return reactionTimestampId;
    }

    public void setReactionTimestampId(long reactionTimestampId) {
        this.reactionTimestampId = reactionTimestampId;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public int getSymptomId() {
        return symptomId;
    }

    public void setSymptomId(int symptomId) {
        this.symptomId = symptomId;
    }

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }
}
