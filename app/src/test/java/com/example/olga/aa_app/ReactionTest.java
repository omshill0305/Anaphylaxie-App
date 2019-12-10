package com.example.olga.aa_app;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ReactionTest {
    private Reaction reaction;

    @Before
    public void before() {
        reaction = new Reaction();
    }

    @Test
    public void addOneSymptom() {
        reaction.addSymptom(new Symptom("quaddeln"));

        ArrayList<Symptom> expected = new ArrayList<>();
        expected.add(new Symptom("quaddeln"));

        assertEquals(expected, reaction.getSymptoms());
    }

    @Test
    public void addMoreSymptomsAtOnce() {
        reaction.addSymptoms(
                new Symptom[] {
                        new Symptom("quaddeln"),
                        new Symptom("schwindel"),
                        new Symptom("atemnot")
                });

        ArrayList<Symptom> expected = new ArrayList<>();
        expected.add(new Symptom("quaddeln"));
        expected.add(new Symptom("schwindel"));
        expected.add(new Symptom("atemnot"));

        assertEquals(expected, reaction.getSymptoms());
    }

    @Test
    public void addMoreSymptomsSequentially() {
        reaction.addSymptom(new Symptom("quaddeln"));
        reaction.addSymptom(new Symptom("schwindel"));
        reaction.addSymptom(new Symptom("atemnot"));

        ArrayList<Symptom> expected = new ArrayList<>();
        expected.add(new Symptom("quaddeln"));
        expected.add(new Symptom("schwindel"));
        expected.add(new Symptom("atemnot"));

        assertEquals(expected, reaction.getSymptoms());
    }

    @Test
    public void addSymptomThatIsAlreadyThere() {
        reaction.addSymptom(new Symptom("quaddeln"));
        reaction.addSymptom(new Symptom("schwindel"));
        reaction.addSymptom(new Symptom("quaddeln"));

        ArrayList<Symptom> expected = new ArrayList<>();
        expected.add(new Symptom("quaddeln"));
        expected.add(new Symptom("schwindel"));

        assertEquals(expected, reaction.getSymptoms());
    }

    @Test
    public void removeSymptom() {
        reaction.addSymptoms(
                new Symptom[] {
                        new Symptom("quaddeln"),
                        new Symptom("schwindel"),
                        new Symptom("atemnot")
        });
        reaction.removeSymptom(new Symptom("quaddeln"));

        ArrayList<Symptom> expected = new ArrayList<>();
        expected.add(new Symptom("schwindel"));
        expected.add(new Symptom("atemnot"));

        assertEquals(expected, reaction.getSymptoms());
    }

    @Test
    public void removeSymptomThatIsNotThere() {
        reaction.addSymptoms(
                new Symptom[] {
                        new Symptom("quaddeln"),
                        new Symptom("schwindel"),
                        new Symptom("atemnot")
                });
        reaction.removeSymptom(new Symptom("husten"));

        ArrayList<Symptom> expected = new ArrayList<>();
        expected.add(new Symptom("quaddeln"));
        expected.add(new Symptom("schwindel"));
        expected.add(new Symptom("atemnot"));

        assertEquals(expected, reaction.getSymptoms());
    }
}