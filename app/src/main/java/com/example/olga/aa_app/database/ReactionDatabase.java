package com.example.olga.aa_app.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.olga.aa_app.database.daos.AllergyDAO;
import com.example.olga.aa_app.database.daos.BrandNameDAO;
import com.example.olga.aa_app.database.daos.EmergencySetDAO;
import com.example.olga.aa_app.database.daos.MedicineDAO;
import com.example.olga.aa_app.database.daos.ProfileDAO;
import com.example.olga.aa_app.database.daos.SetsOfProfileDAO;
import com.example.olga.aa_app.database.entities.Allergy;
import com.example.olga.aa_app.database.entities.BrandName;
import com.example.olga.aa_app.database.entities.EmergencySet;
import com.example.olga.aa_app.database.entities.Medicine;
import com.example.olga.aa_app.database.entities.Profile;
import com.example.olga.aa_app.database.jointables.SetsOfProfile;

import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.reactivex.CompletableObserver;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * The database class that will start the database. All entities have to be written inside
 * the entities field in @Database annotation.
 */
@Database(entities = {Allergy.class, EmergencySet.class,
        Profile.class, BrandName.class, Medicine.class, SetsOfProfile.class}, version = 1, exportSchema = false)
public abstract class ReactionDatabase extends RoomDatabase {

    private static volatile ReactionDatabase instance;

    // Provide access to entity DAOs here. Room is handling the rest, hence can be abstract.
    public abstract AllergyDAO allergyDAO();
    public abstract EmergencySetDAO emergencySetDAO();
    public abstract ProfileDAO profileDAO();
    public abstract BrandNameDAO brandNameDAO();
    public abstract MedicineDAO medicineDAO();
    public abstract SetsOfProfileDAO setsOfProfileDAO();

    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static ReactionDatabase getInstance(final Context context){
        if(instance == null){
            synchronized (ReactionDatabase.class) {
                if(instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            ReactionDatabase.class, "reaction_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(roomCallback)
                            .build();
                }
            }
        }
        return instance;
    }

    // Here we populate the database on first time app launch
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            AllergyDAO allergyDAO = instance.allergyDAO();
            BrandNameDAO brandNameDAO = instance.brandNameDAO();
            MedicineDAO medicineDAO = instance.medicineDAO();
            EmergencySetDAO emergencySetDAO = instance.emergencySetDAO();
            ProfileDAO profileDAO = instance.profileDAO();
            SetsOfProfileDAO setsOfProfileDAO = instance.setsOfProfileDAO();

            BrandName b1 = new BrandName("FromSoftware");
            BrandName b2 = new BrandName("Activision");
            brandNameDAO.insert(b1);
            brandNameDAO.insert(b2);

            Medicine m1 = new Medicine("Auto Injektor");
            Medicine m2 = new Medicine("Paracetamol");
            medicineDAO.insert(m1);
            medicineDAO.insert(m2);

            EmergencySet emergencySet = new EmergencySet(1, 1, 5, "unit");
            EmergencySet emergencySet2 = new EmergencySet(2, 2, 30, "mg");
            emergencySetDAO.insert(emergencySet);
            emergencySetDAO.insert(emergencySet2);

            Profile profile = new Profile("juul", 15, 'm', true, true);
            Profile profile2 = new Profile("bruh", 12, 'm', false, false);
            profileDAO.insert(profile);
            profileDAO.insert(profile2);

            SetsOfProfile setsOfProfile = new SetsOfProfile(1, 2);
            SetsOfProfile setsOfProfile2 = new SetsOfProfile(1, 1);
            SetsOfProfile setsOfProfile3 = new SetsOfProfile(2, 2);
            setsOfProfileDAO.insert(setsOfProfile);
            setsOfProfileDAO.insert(setsOfProfile2);
            setsOfProfileDAO.insert(setsOfProfile3);

        }
    };

}
