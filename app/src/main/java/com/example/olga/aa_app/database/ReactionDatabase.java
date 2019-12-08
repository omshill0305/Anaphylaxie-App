package com.example.olga.aa_app.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.olga.aa_app.database.daos.AllergyDAO;
import com.example.olga.aa_app.database.entities.Allergy;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The database class that will start the database.
 */
@Database(entities = {Allergy.class}, version = 1, exportSchema = false)
public abstract class ReactionDatabase extends RoomDatabase {

    private static volatile ReactionDatabase instance;

    // Provide access to entity DAOs here
    public abstract AllergyDAO allergyDAO();

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
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            databaseWriteExecutor.execute(() -> {
                AllergyDAO allergyDAO = instance.allergyDAO();

                Allergy allergy = new Allergy("bruh", "bruh2");
                allergyDAO.insert(allergy);
            });
        }
    };

}
