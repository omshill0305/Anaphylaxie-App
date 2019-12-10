package com.example.olga.aa_app;

import java.util.ArrayList;

public class Reaction {
    private ArrayList<Symptom> symptoms = new ArrayList<>();

    public ArrayList<Symptom> getSymptoms() {
        return symptoms;
    }

    public void addSymptom(Symptom symptom) {
        if (!symptoms.contains(symptom)) symptoms.add(symptom);
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
