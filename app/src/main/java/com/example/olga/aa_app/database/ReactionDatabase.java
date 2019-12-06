package com.example.olga.aa_app.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.olga.aa_app.database.entities.Allergy;

@Database(entities = {Allergy.class}, version = 1)
public abstract class ReactionDatabase extends RoomDatabase {

    // Basic thread-safe singleton implementation
    private static ReactionDatabase instance;

    // Provide access to entity DAOs here
    public abstract AllergyDAO allergyDAO();

    public static synchronized ReactionDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    ReactionDatabase.class, "reaction_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        // Gets called "on create"
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();  // Starts the database population async task.
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        // DAO fields
        private AllergyDAO allergyDAO;

        // Constructor that populates DAO field of every entity.
        private PopulateDbAsyncTask(ReactionDatabase db){
            allergyDAO = db.allergyDAO();
        }

        // Place where database is actually populated with data
        @Override
        protected Void doInBackground(Void... voids) {
            // Example insert
            allergyDAO.insert(new Allergy("Allergie", "Beliebige Beschreibung"));
            return null;
        }
    }
}
