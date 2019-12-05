package com.example.olga.aa_app.database;

import android.app.Application;

public class AllergyRepository {

    private AllergyDAO allergyDAO;

    public AllergyRepository(Application application){
        ReactionDatabase database = ReactionDatabase.getInstance(application);
        allergyDAO = database.allergyDAO();

    }
}
