package com.example.olga.aa_app.database.entities;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;


@Entity(tableName = "profile_table",
        indices = {@Index(value = "profileId", unique = true)}) // Don't forget to change the table name when copy and pasting
public class Profile implements Serializable {

    public enum Gender {
        Male, Female, Diverse
    }

    // autoGenerate equals a serial or autoincrement
    @PrimaryKey(autoGenerate = true)
    private long profileId;

    private String name;
    private Date birthday;
    private int age;
    private Gender gender;
    private boolean asthma;
    private boolean salbutamol;

    public Profile(String name, Date birthday, int age, Gender gender, boolean asthma, boolean salbutamol) {
        this.name = name;
        this.birthday = birthday;
        this.age = age;
        this.asthma = asthma;
        this.salbutamol = salbutamol;
        this.gender = gender;
    }

    public long getProfileId() {
        return profileId;
    }

    public void setProfileId(long profileId) {
        this.profileId = profileId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public boolean hasAsthma() {
        return asthma;
    }

    public void setAsthma(boolean asthma) {
        this.asthma = asthma;
    }

    public boolean isSalbutamol() {
        return salbutamol;
    }

    public void setSalbutamol(boolean salbutamol) {
        this.salbutamol = salbutamol;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
