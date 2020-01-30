package com.example.olga.aa_app;

import java.util.ArrayList;

/**
 * All symptoms of a reaction
 * <p>
 * Several symptoms can appear several times in a day which aren't necessarily related with each other due to the
 * intervals between them. A reaction can be thought of as a collection of related symptoms happening in one interval.
 */
public class Reaction {
    private ArrayList<Symptom> symptoms = new ArrayList<>();

    public ArrayList<Symptom> getSymptoms() {
        return symptoms;
    }

    public void addSymptom(Symptom symptom) {
        if (!symptoms.contains(symptom)) {
            symptoms.add(symptom);
        }
    }

    public void addSymptoms(Symptom[] strings) {
        for (Symptom symptom : strings) {
            addSymptom(symptom);
        }
    }

    public void removeSymptom(Symptom symptom) {
        symptoms.remove(symptom);
    }
}
