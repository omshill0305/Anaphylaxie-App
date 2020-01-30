package com.example.olga.aa_app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Algorithm {

    public final static String ALGORITHM_1 = "algorithm1";
    public final static String ALGORITHM_2 = "algorithm2";
    public final static String ALGORITHM_3 = "algorithm3";
    public final static String ALGORITHM_4 = "algorithm4";
    public final static String ALGORITHM_5 = "algorithm5";

    private final static String SKIN = "haut";
    private final static String OTHERS = "sonstiges";
    private final static String GASTROINTESTINAL = "magendarm";
    private final static String AIRWAYS = "atemwege";
    private final static String CARDIOVASCULAR = "herzkreislauf";

    private static HashMap<Integer, String> organSystems = new HashMap<Integer, String>() {{
        put(R.string.wheals, SKIN);
        put(R.string.swollen_lip_face, SKIN);
        put(R.string.pruritus, SKIN);

        put(R.string.dizziness, OTHERS);
        put(R.string.runny_nose, OTHERS);
        put(R.string.panic, OTHERS);

        put(R.string.diarrhea, GASTROINTESTINAL);
        put(R.string.abdominal_pain, GASTROINTESTINAL);
        put(R.string.nausea, GASTROINTESTINAL);
        put(R.string.tingling_mouth_throat, GASTROINTESTINAL);
        put(R.string.vomiting, GASTROINTESTINAL);

        put(R.string.difficulty_in_breathing, AIRWAYS);
        put(R.string.hoarseness, AIRWAYS);
        put(R.string.wheezing, AIRWAYS);
        put(R.string.cough, AIRWAYS);

        put(R.string.unconsciousness, CARDIOVASCULAR);
        put(R.string.blood_pressure_drop, CARDIOVASCULAR);
    }};

    public static String evaluate(Reaction reaction) {
        ArrayList<Symptom> symptoms = reaction.getSymptoms();
        if (isUnconscious(symptoms)) {
            return ALGORITHM_5;
        } else if (hasBloodPressureDrop(symptoms)) {
            return ALGORITHM_4;
        } else if (hasShortnessOfBreath(symptoms)) {
            return ALGORITHM_3;
        } else if (twoOrganSystems(symptoms)) {
            return ALGORITHM_2;
        } else if (oneOrganSystem(symptoms)) {
            return ALGORITHM_1;
        }
        return "";
    }

    private static boolean isUnconscious(ArrayList<Symptom> symptoms) {
        //bewusstlosigkeit
        return symptoms.contains(new Symptom(R.string.unconsciousness));
    }

    private static boolean hasBloodPressureDrop(ArrayList<Symptom> symptoms) {
        // kreislauf ohne bewussstlosigkeit, so blutdruckabfall only
        return symptoms.contains(new Symptom(R.string.blood_pressure_drop));
    }

    private static boolean hasShortnessOfBreath(ArrayList<Symptom> symptoms) {
        //atemnot
        return symptoms.contains(new Symptom(R.string.difficulty_in_breathing));
    }

    private static boolean twoOrganSystems(ArrayList<Symptom> symptoms) {
        //2 Organsysteme oder atemwege
        HashSet<String> os = getOrganSystems(symptoms);
        return os.size() > 1 || os.contains(AIRWAYS);
    }

    private static boolean oneOrganSystem(ArrayList<Symptom> symptoms) {
        // ein organsystem, nicht kreislauf oder atemwege
        return getOrganSystems(symptoms).size() <= 1;
    }

    private static HashSet<String> getOrganSystems(ArrayList<Symptom> symptoms) {
        HashSet<String> os = new HashSet<>();
        for (Symptom symptom : symptoms) {
            String system = organSystems.get(symptom.getName());
            os.add(system);
        }
        return os;
    }
}
