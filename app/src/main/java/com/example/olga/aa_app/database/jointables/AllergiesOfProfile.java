package com.example.olga.aa_app.database.jointables;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

import com.example.olga.aa_app.database.entities.Allergy;
import com.example.olga.aa_app.database.entities.Profile;

@Entity(tableName = "allergies_of_profiles_table",
    primaryKeys = {"profileId" , "allergyId"},
    foreignKeys = {
        @ForeignKey(entity = Allergy.class,
                    parentColumns = "allergyId",
                    childColumns = "allergyId"),
        @ForeignKey(entity = Profile.class,
                    parentColumns = "profileId",
                    childColumns = "profileId")
        }, indices =
        {@Index(value = "profileId"), @Index(value =  "allergyId")})
public class AllergiesOfProfile {
    public final long profileId;
    public final long allergyId;

    public AllergiesOfProfile(final long profileId, final long allergyId){
        this.profileId = profileId;
        this.allergyId = allergyId;
    }
}
