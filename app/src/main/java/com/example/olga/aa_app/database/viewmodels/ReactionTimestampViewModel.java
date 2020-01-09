package com.example.olga.aa_app.database.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.olga.aa_app.database.daos.ReactionTimestampDAO;
import com.example.olga.aa_app.database.daos.SetsOfProfileDAO;
import com.example.olga.aa_app.database.entities.Profile;
import com.example.olga.aa_app.database.entities.ReactionTimestamp;
import com.example.olga.aa_app.database.jointables.SetsOfProfile;
import com.example.olga.aa_app.database.repositorys.ReactionTimestampRepository;
import com.example.olga.aa_app.database.repositorys.SetsOfProfileRepository;

import java.util.List;

import io.reactivex.Completable;

public class ReactionTimestampViewModel extends AndroidViewModel implements ReactionTimestampDAO {

    private ReactionTimestampRepository repository;

    public ReactionTimestampViewModel(@NonNull Application application) {
        super(application);
        repository = new ReactionTimestampRepository(application);
    }

    @Override
    public Completable insert(ReactionTimestamp reactionTimestamp) {
        return repository.insert(reactionTimestamp);
    }

}
