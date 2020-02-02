package com.example.olga.aa_app.database.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.olga.aa_app.database.entities.Allergy;
import com.example.olga.aa_app.database.entities.EmergencySet;
import com.example.olga.aa_app.database.entities.Profile;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface ProfileDAO {

    // Base Operations

    @Insert
    Single<Long> insert(Profile profile);

    @Update
    Single<Integer> update(Profile profile);

    @Delete
    Single<Integer> delete(Profile profile);

    // Custom queries

    @Query("SELECT * FROM profile_table")
    LiveData<List<Profile>> getAllProfiles();

    @Query("select * from emergencyset_table where emergencySetId in " +
            "(select emergencySetId from sets_of_profile_table where profileId = :profileId)")
    Single<List<EmergencySet>> getAllEmergencySetsOfProfileByProfileId(final long profileId);

    @Query("select * from allergy_table where allergyId in " +
            "(select allergyId from allergies_of_profiles_table where profileId = :profileId)")
    Single<List<Allergy>> getAllAllergiesOfProfileByProfileId(final long profileId);

    @Query("select * from profile_table where profileId = :profileId")
    Single<Profile> getProfileByProfileId(final long profileId);

    @Query("delete from profile_table")
    Completable deleteAllEntries();

}
