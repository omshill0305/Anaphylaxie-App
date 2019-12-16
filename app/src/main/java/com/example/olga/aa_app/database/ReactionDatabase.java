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
import com.example.olga.aa_app.database.entities.Allergy;
import com.example.olga.aa_app.database.entities.BrandName;
import com.example.olga.aa_app.database.entities.EmergencySet;
import com.example.olga.aa_app.database.entities.Medicine;
import com.example.olga.aa_app.database.entities.Profile;

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
        Profile.class, BrandName.class, Medicine.class}, version = 1, exportSchema = false)
public abstract class ReactionDatabase extends RoomDatabase {

    private static volatile ReactionDatabase instance;

    // Provide access to entity DAOs here. Room is handling the rest, hence can be abstract.
    public abstract AllergyDAO allergyDAO();
    public abstract EmergencySetDAO emergencySetDAO();
    public abstract ProfileDAO profileDAO();
    public abstract BrandNameDAO brandNameDAO();
    public abstract MedicineDAO medicineDAO();

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


        }
    };

}
