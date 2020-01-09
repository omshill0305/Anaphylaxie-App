package com.example.olga.aa_app.database.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.olga.aa_app.database.entities.Profile;
import com.example.olga.aa_app.database.jointables.AllergiesOfProfile;
import com.example.olga.aa_app.database.jointables.SetsOfProfile;

import java.util.List;

import io.reactivex.Completable;


@Dao
public interface AllergiesOfProfileDAO {

    @Insert
    Completable insert(AllergiesOfProfile allergiesOfProfile);

    // Queries

    @Query("select * from profile_table inner join allergies_of_profiles_table using (profileId) where allergies_of_profiles_table.allergyId = :allergyId")
    LiveData<List<Profile>> getProfilesWithAllergies(final int allergyId);

    @Query("select * from profile_table left outer join allergies_of_profiles_table on " +
            "profile_table.profileId = allergies_of_profiles_table.profileId where" +
            " allergies_of_profiles_table.profileId = :profileId")
    LiveData<List<Profile>> getAllergiesOfProfile(final int profileId);

}
