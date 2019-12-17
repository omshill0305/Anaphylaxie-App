package com.example.olga.aa_app.database.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.olga.aa_app.database.entities.Allergy;
import com.example.olga.aa_app.database.entities.Profile;
import com.example.olga.aa_app.database.jointables.SetsOfProfile;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;


@Dao
public interface SetsOfProfileDAO {

    // Base Operations

    @Insert
    Completable insert(SetsOfProfile setsOfProfile);

    // Queries

    @Query("select * from profile_table inner join sets_of_profile_table using (profileId) where sets_of_profile_table.emergencySetId = :emergencySetId")
    LiveData<List<Profile>> getProfilesWithEmergencySet(final int emergencySetId);

    @Query("select * from profile_table left outer join sets_of_profile_table on " +
            "profile_table.profileId = sets_of_profile_table.profileId where" +
            " sets_of_profile_table.profileId = :profileId")
    LiveData<List<Profile>> getEmergencySetsOfProfile(final int profileId);

}
