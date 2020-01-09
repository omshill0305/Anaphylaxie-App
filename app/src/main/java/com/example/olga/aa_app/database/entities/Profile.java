package com.example.olga.aa_app.database.entities;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;


@Entity(tableName = "profile_table",
        indices = {@Index(value = "profileId", unique = true)}) // Don't forget to change the table name when copy and pasting
public class Profile {

    // autoGenerate equals a serial or autoincrement
    @PrimaryKey(autoGenerate = true)
    private int profileId;

    public String name;
    public int age;
    public char gender;
    public boolean asthma;
    public boolean salbutamol;

    // gender is not checked, right now
    public Profile(String name, int age, char gender, boolean asthma, boolean salbutamol) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.asthma = asthma;
        this.salbutamol = salbutamol;
    }

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }
}
