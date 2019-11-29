package com.example.olga.aa_app;

import java.util.ArrayList;

public class Profile {
    private ArrayList<Reaction> reactions = new ArrayList<>();
    private Reaction currentReaction = null;
    //andere profilinfos

    public void startReaction(Reaction reaction) {
        reactions.add(reaction);
        currentReaction = reaction;
    }

    public void endReaction(Reaction reaction) {
        currentReaction = null;
    }

    public Reaction getCurrentReaction() {
        return currentReaction;
    }
}
