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
        reaction.addSymptom(new Symptom("quaddeln"));
        assertEquals("algorithm1", Algorithm.evaluate(reaction));
    }

    @Test
    public void evaluateAlgorithm1_2() {
        reaction.addSymptoms(
                new Symptom[] {
                        new Symptom("quaddeln"),
                        new Symptom("schwellung"),
                        new Symptom("jucken")
                });
        assertEquals("algorithm1", Algorithm.evaluate(reaction));
    }

    @Test
    public void evaluateAlgorithm2_1() {
        reaction.addSymptom(new Symptom("husten"));
        assertEquals("algorithm2", Algorithm.evaluate(reaction));
    }

    @Test
    public void evaluateAlgorithm2_2() {
        reaction.addSymptoms(
                new Symptom[] {
                        new Symptom("quaddeln"),
                        new Symptom("durchfall")
                });
        assertEquals("algorithm2", Algorithm.evaluate(reaction));
    }

    @Test
    public void evaluateAlgorithm2_3() {
        reaction.addSymptom(new Symptom("heiserkeit"));
        assertEquals("algorithm2", Algorithm.evaluate(reaction));
    }

    @Test
    public void evaluateAlgorithm2_4() {
        reaction.addSymptoms(
                new Symptom[] {
                        new Symptom("quaddeln"),
                        new Symptom("heiserkeit")
                });
        assertEquals("algorithm2", Algorithm.evaluate(reaction));
    }

    @Test
    public void evaluateAlgorithm3_1() {
        reaction.addSymptom(new Symptom("atemnot"));

        assertEquals("algorithm3", Algorithm.evaluate(reaction));
    }

    @Test
    public void evaluateAlgorithm3_2() {
        reaction.addSymptoms(
                new Symptom[] {
                        new Symptom("quaddeln"),
                        new Symptom("atemnot")
                });
        assertEquals("algorithm3", Algorithm.evaluate(reaction));
    }

    @Test
    public void evaluateAlgorithm3_3() {
        reaction.addSymptoms(
                new Symptom[] {
                        new Symptom("quaddeln"),
                        new Symptom("heiserkeit"),
                        new Symptom("atemnot")
                });
        assertEquals("algorithm3", Algorithm.evaluate(reaction));
    }

    @Test
    public void evaluateAlgorithm4_1() {
        reaction.addSymptom(new Symptom("blutdruckabfall"));
        assertEquals("algorithm4", Algorithm.evaluate(reaction));
    }

    @Test
    public void evaluateAlgorithm4_2() {
        reaction.addSymptoms(
                new Symptom[] {
                        new Symptom("quaddeln"),
                        new Symptom("blutdruckabfall")
                });
        assertEquals("algorithm4", Algorithm.evaluate(reaction));
    }

    @Test
    public void evaluateAlgorithm4_3() {
        reaction.addSymptoms(
                new Symptom[] {
                        new Symptom("heiserkeit"),
                        new Symptom("blutdruckabfall")
                });
        assertEquals("algorithm4", Algorithm.evaluate(reaction));
    }

    @Test
    public void evaluateAlgorithm4_4() {
        reaction.addSymptoms(
                new Symptom[] {
                        new Symptom("atemnot"),
                        new Symptom("blutdruckabfall")
                });
        assertEquals("algorithm4", Algorithm.evaluate(reaction));
    }

    @Test
    public void evaluateAlgorithm5_1() {
        reaction.addSymptom(new Symptom("bewusstlosigkeit"));
        assertEquals("algorithm5", Algorithm.evaluate(reaction));
    }

    @Test
    public void evaluateAlgorithm5_2() {
        reaction.addSymptoms(
                new Symptom[] {
                        new Symptom("quaddeln"),
                        new Symptom("blutdruckabfall"),
                        new Symptom("bewusstlosigkeit")
                });
        assertEquals("algorithm5", Algorithm.evaluate(reaction));
    }

    @Test
    public void evaluateAlgorithm5_3() {
        reaction.addSymptoms(
                new Symptom[] {
                        new Symptom("heiserkeit"),
                        new Symptom("blutdruckabfall"),
                        new Symptom("bewusstlosigkeit")
                });
        assertEquals("algorithm5", Algorithm.evaluate(reaction));
    }

    @Test
    public void evaluateAlgorithm5_4() {
        reaction.addSymptoms(
                new Symptom[] {
                        new Symptom("atemnot"),
                        new Symptom("blutdruckabfall"),
                        new Symptom("bewusstlosigkeit")
                });
        assertEquals("algorithm5", Algorithm.evaluate(reaction));
    }


}