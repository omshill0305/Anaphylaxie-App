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
    private long emergencyTimestampId;
    private Timestamp timestamp;

    private long profileId;
    private long sessionId;

    public long getEmergencyTimestampId() {
        return emergencyTimestampId;
    }

    public void setEmergencyTimestampId(long emergencyTimestampId) {
        this.emergencyTimestampId = emergencyTimestampId;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public long getProfileId() {
        return profileId;
    }

    public void setProfileId(long profileId) {
        this.profileId = profileId;
    }

    public long getSessionId() {
        return sessionId;
    }

    public void setSessionId(long sessionId) {
        this.sessionId = sessionId;
    }
}
