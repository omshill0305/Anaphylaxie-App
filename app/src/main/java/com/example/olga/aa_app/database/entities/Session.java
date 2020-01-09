package com.example.olga.aa_app.database.entities;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;


@Entity(tableName = "session_table", indices = {@Index(value = {"sessionId"}, unique = true)})  // Don't forget to change the table name when copy and pasting
public class Session {

    // autoGenerate equals a serial or autoincrement
    @PrimaryKey(autoGenerate = true)
    private int sessionId;

    public Timestamp sessionStart;
    public Timestamp sessionEnd;

    // Things needed: Constructor, Getters for all fields and one setter for ID

    public Session() {
        this.sessionStart = new Timestamp(new Date().getTime());
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }
}
