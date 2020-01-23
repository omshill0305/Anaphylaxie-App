package com.example.olga.aa_app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Algorithm {

    private static HashMap<String,String> organSystems = new HashMap<String,String>(){{
        put("quaddeln", "haut");
        put("schwellung", "haut");
        put("jucken", "haut");

        put("schwindel", "sonstiges");
        put("fliessschnupfen", "sonstiges");
        put("angstgefuehl", "sonstiges");

        put("durchfall", "magendarm");
        put("bauchschmerzen", "magendarm");
        put("uebelkeit", "magendarm");
        put("kribbeln", "magendarm");
        put("erbrechen", "magendarm");

        put("heiserkeit", "atemwege");
        put("pfeifen", "atemwege");
        put("husten", "atemwege");
    }};

    public static String evaluate(Reaction reaction) {

        ArrayList<Symptom> symptoms = reaction.getSymptoms();
        if (isUnconcious(symptoms)) {
            return algorithm5();
        } else if (hasBloodPressureDrop(symptoms)) {
            return algorithm4();
        } else if (hasShortnessOfBreath(symptoms)) {
            return algorithm3();
        } else if (twoOrganSystems(symptoms)) {
            return algorithm2();
        } else if (oneOrganSystem(symptoms)) {
            return algorithm1();
        }
        return "";
    }



    private static boolean isUnconcious(ArrayList<Symptom> symptoms) {
        //bewusstlosigkeit
        return symptoms.contains(new Symptom("bewusstlosigkeit"));
    }

    private static boolean hasBloodPressureDrop(ArrayList<Symptom> symptoms) {
        // kreislauf ohne bewussstlosigkeit, so blutdruckabfall only
        return symptoms.contains(new Symptom("blutdruckabfall"));
    }

    private static boolean hasShortnessOfBreath(ArrayList<Symptom> symptoms) {
        //atemnot
        return symptoms.contains(new Symptom("atemnot"));
    }

    private static boolean twoOrganSystems(ArrayList<Symptom> symptoms) {
        //2 Organsysteme oder atemwege
        HashSet<String> os = getOrganSystems(symptoms);
        return os.size() > 1 || os.contains("atemwege");
    }

    private static boolean oneOrganSystem(ArrayList<Symptom> symptoms) {
        // ein organsystem, nicht kreislauf oder atemwege
        return getOrganSystems(symptoms).size() <= 1;
    }

    private static HashSet<String> getOrganSystems(ArrayList<Symptom> symptoms) {
        HashSet<String> os = new HashSet<>();
        for(Symptom symptom : symptoms) {
            String system = organSystems.get(symptom.toString());
            os.add(system);
        }
        return os;
    }



    private static String algorithm1() {
        return "algorithm1";
    }

    private static String algorithm2() {
        return "algorithm2";
    }

    private static String algorithm3() {
        return "algorithm3";
    }

    private static String algorithm4() {
        return "algorithm4";
    }

    private static String algorithm5() {
        return "algorithm5";
    }
}
