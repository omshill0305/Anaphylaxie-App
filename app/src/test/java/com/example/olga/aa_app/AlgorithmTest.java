package com.example.olga.aa_app;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AlgorithmTest {
    Reaction reaction;

    @Before
    public void before() {
        reaction = new Reaction();
    }

    @Test
    public void evaluateAlgorithm1_1() {
        reaction.addSymptom(new Symptom(R.string.wheals));
        assertEquals(Algorithm.ALGORITHM_1, Algorithm.evaluate(reaction));
    }

    @Test
    public void evaluateAlgorithm1_2() {
        reaction.addSymptoms(new Symptom[] {
                new Symptom(R.string.wheals), new Symptom(R.string.swollen_lip_face), new Symptom(R.string.pruritus)
        });
        assertEquals(Algorithm.ALGORITHM_1, Algorithm.evaluate(reaction));
    }

    @Test
    public void evaluateAlgorithm2_1() {
        reaction.addSymptom(new Symptom(R.string.cough));
        assertEquals(Algorithm.ALGORITHM_2, Algorithm.evaluate(reaction));
    }

    @Test
    public void evaluateAlgorithm2_2() {
        reaction.addSymptoms(new Symptom[] {
                new Symptom(R.string.wheals), new Symptom(R.string.diarrhea)
        });
        assertEquals(Algorithm.ALGORITHM_2, Algorithm.evaluate(reaction));
    }

    @Test
    public void evaluateAlgorithm2_3() {
        reaction.addSymptom(new Symptom(R.string.hoarseness));
        assertEquals(Algorithm.ALGORITHM_2, Algorithm.evaluate(reaction));
    }

    @Test
    public void evaluateAlgorithm2_4() {
        reaction.addSymptoms(new Symptom[] {
                new Symptom(R.string.wheals), new Symptom(R.string.hoarseness)
        });
        assertEquals(Algorithm.ALGORITHM_2, Algorithm.evaluate(reaction));
    }

    @Test
    public void evaluateAlgorithm3_1() {
        reaction.addSymptom(new Symptom(R.string.difficulty_in_breathing));

        assertEquals(Algorithm.ALGORITHM_3, Algorithm.evaluate(reaction));
    }

    @Test
    public void evaluateAlgorithm3_2() {
        reaction.addSymptoms(new Symptom[] {
                new Symptom(R.string.wheals), new Symptom(R.string.difficulty_in_breathing)
        });
        assertEquals(Algorithm.ALGORITHM_3, Algorithm.evaluate(reaction));
    }

    @Test
    public void evaluateAlgorithm3_3() {
        reaction.addSymptoms(new Symptom[] {
                new Symptom(R.string.wheals),
                new Symptom(R.string.hoarseness),
                new Symptom(R.string.difficulty_in_breathing)
        });
        assertEquals(Algorithm.ALGORITHM_3, Algorithm.evaluate(reaction));
    }

    @Test
    public void evaluateAlgorithm4_1() {
        reaction.addSymptom(new Symptom(R.string.blood_pressure_drop));
        assertEquals(Algorithm.ALGORITHM_4, Algorithm.evaluate(reaction));
    }

    @Test
    public void evaluateAlgorithm4_2() {
        reaction.addSymptoms(new Symptom[] {
                new Symptom(R.string.wheals), new Symptom(R.string.blood_pressure_drop)
        });
        assertEquals(Algorithm.ALGORITHM_4, Algorithm.evaluate(reaction));
    }

    @Test
    public void evaluateAlgorithm4_3() {
        reaction.addSymptoms(new Symptom[] {
                new Symptom(R.string.hoarseness), new Symptom(R.string.blood_pressure_drop)
        });
        assertEquals(Algorithm.ALGORITHM_4, Algorithm.evaluate(reaction));
    }

    @Test
    public void evaluateAlgorithm4_4() {
        reaction.addSymptoms(new Symptom[] {
                new Symptom(R.string.difficulty_in_breathing), new Symptom(R.string.blood_pressure_drop)
        });
        assertEquals(Algorithm.ALGORITHM_4, Algorithm.evaluate(reaction));
    }

    @Test
    public void evaluateAlgorithm5_1() {
        reaction.addSymptom(new Symptom(R.string.unconsciousness));
        assertEquals(Algorithm.ALGORITHM_5, Algorithm.evaluate(reaction));
    }

    @Test
    public void evaluateAlgorithm5_2() {
        reaction.addSymptoms(new Symptom[] {
                new Symptom(R.string.wheals),
                new Symptom(R.string.blood_pressure_drop),
                new Symptom(R.string.unconsciousness)
        });
        assertEquals(Algorithm.ALGORITHM_5, Algorithm.evaluate(reaction));
    }

    @Test
    public void evaluateAlgorithm5_3() {
        reaction.addSymptoms(new Symptom[] {
                new Symptom(R.string.hoarseness),
                new Symptom(R.string.blood_pressure_drop),
                new Symptom(R.string.unconsciousness)
        });
        assertEquals(Algorithm.ALGORITHM_5, Algorithm.evaluate(reaction));
    }

    @Test
    public void evaluateAlgorithm5_4() {
        reaction.addSymptoms(new Symptom[] {
                new Symptom(R.string.difficulty_in_breathing),
                new Symptom(R.string.blood_pressure_drop),
                new Symptom(R.string.unconsciousness)
        });
        assertEquals(Algorithm.ALGORITHM_5, Algorithm.evaluate(reaction));
    }


}