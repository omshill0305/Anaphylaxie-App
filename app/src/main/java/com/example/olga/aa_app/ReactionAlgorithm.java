package com.example.olga.aa_app;

import java.util.ArrayList;

public class ReactionAlgorithm {
    private ArrayList<String> symptoms = new ArrayList<>();

    public ArrayList<String> getSymptoms() {
        return symptoms;
    }

    public void addSymptom(String symptom) {
        if (!symptoms.contains(symptom)) symptoms.add(symptom);
    }

    public void addSymptoms(String[] strings) {
        for (String symptom : strings) {
            addSymptom(symptom);
        }
    }

    public void removeSymptom(String symptom) {
        symptoms.remove(symptom);
    }

    public String evaluateAlgorithm() {
        return "";
    }
}
