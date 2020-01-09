package com.example.olga.aa_app.database.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.sql.Timestamp;
import java.util.Date;

import static androidx.room.ForeignKey.CASCADE;
import static androidx.room.ForeignKey.RESTRICT;


@Entity(tableName = "emergency_call_timestamp_table", foreignKeys = {
        @ForeignKey(entity = Session.class,
                parentColumns = "sessionId",
                childColumns = "sessionId",
                onDelete = RESTRICT,
                onUpdate = RESTRICT),
        @ForeignKey(entity = Profile.class,
                parentColumns = "profileId",
                childColumns = "profileId",
                onDelete = RESTRICT,
                onUpdate = CASCADE
        )
}, indices =
        {@Index(value = "sessionId"), @Index(value =  "profileId")})    // Don't forget to change the table name when copy and pasting
public class EmergencyCallTimestamp {

    @PrimaryKey(autoGenerate = true)
    private int emergencyTimestampId;
    private Timestamp timestamp;

    private int profileId;
    private int sessionId;

    public int getEmergencyTimestampId() {
        return emergencyTimestampId;
    }

    public void setEmergencyTimestampId(int emergencyTimestampId) {
        this.emergencyTimestampId = emergencyTimestampId;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
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
