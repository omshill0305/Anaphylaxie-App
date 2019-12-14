package com.example.olga.aa_app.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * A database entity has to be annotated as @Entity for Room to recognize it as a
 * database entity. In a entity class, all member variables need to be set as fields
 * with getters and setters accordingly. The entity can be seen as a normal class, but
 * database specific fields such as a primary key have to be annotated separately.
 */
@Entity(tableName = "profile_table") // Don't forget to change the table name when copy and pasting
public class Profile {

    // autoGenerate equals a serial or autoincrement
    @PrimaryKey(autoGenerate = true)
    private int profileId;

    private String name;
    private int age;
    private char gender;
    private boolean asthma;

    // Things needed: Constructor, Getters for all fields and one setter for ID

    // gender is not checked, right now
    public Profile(String name, int age, char gender, boolean asthma) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.asthma = asthma;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public int getProfileId() {
        return profileId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public char getGender() {
        return gender;
    }

    public boolean isAsthma() {
        return asthma;
    }
}
