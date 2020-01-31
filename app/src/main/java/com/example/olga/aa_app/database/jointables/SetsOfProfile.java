package com.example.olga.aa_app.database.jointables;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

import com.example.olga.aa_app.database.entities.EmergencySet;
import com.example.olga.aa_app.database.entities.Profile;

@Entity(tableName = "sets_of_profile_table",
    primaryKeys = {"profileId" , "emergencySetId"},
    foreignKeys = {
        @ForeignKey(entity = EmergencySet.class,
                    parentColumns = "emergencySetId",
                    childColumns = "emergencySetId"),
        @ForeignKey(entity = Profile.class,
                    parentColumns = "profileId",
                    childColumns = "profileId")
        }, indices =
        {@Index(value = "profileId"), @Index(value =  "emergencySetId")})
public class SetsOfProfile {

    public final long profileId;
    public final long emergencySetId;

    public SetsOfProfile(final long profileId, final long emergencySetId){
        this.profileId = profileId;
        this.emergencySetId = emergencySetId;
    }

}
