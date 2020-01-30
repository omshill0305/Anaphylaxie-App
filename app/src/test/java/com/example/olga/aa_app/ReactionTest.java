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
        reaction.addSymptom(new Symptom(R.string.wheals));

        ArrayList<Symptom> expected = new ArrayList<>();
        expected.add(new Symptom(R.string.wheals));

        assertEquals(expected, reaction.getSymptoms());
    }

    @Test
    public void addMoreSymptomsAtOnce() {
        reaction.addSymptoms(new Symptom[] {
                new Symptom(R.string.wheals),
                new Symptom(R.string.dizziness),
                new Symptom(R.string.difficulty_in_breathing)
        });

        ArrayList<Symptom> expected = new ArrayList<>();
        expected.add(new Symptom(R.string.wheals));
        expected.add(new Symptom(R.string.dizziness));
        expected.add(new Symptom(R.string.difficulty_in_breathing));

        assertEquals(expected, reaction.getSymptoms());
    }

    @Test
    public void addMoreSymptomsSequentially() {
        reaction.addSymptom(new Symptom(R.string.wheals));
        reaction.addSymptom(new Symptom(R.string.dizziness));
        reaction.addSymptom(new Symptom(R.string.difficulty_in_breathing));

        ArrayList<Symptom> expected = new ArrayList<>();
        expected.add(new Symptom(R.string.wheals));
        expected.add(new Symptom(R.string.dizziness));
        expected.add(new Symptom(R.string.difficulty_in_breathing));

        assertEquals(expected, reaction.getSymptoms());
    }

    @Test
    public void addSymptomThatIsAlreadyThere() {
        reaction.addSymptom(new Symptom(R.string.wheals));
        reaction.addSymptom(new Symptom(R.string.dizziness));
        reaction.addSymptom(new Symptom(R.string.wheals));

        ArrayList<Symptom> expected = new ArrayList<>();
        expected.add(new Symptom(R.string.wheals));
        expected.add(new Symptom(R.string.dizziness));

        assertEquals(expected, reaction.getSymptoms());
    }

    @Test
    public void removeSymptom() {
        reaction.addSymptoms(new Symptom[] {
                new Symptom(R.string.wheals),
                new Symptom(R.string.dizziness),
                new Symptom(R.string.difficulty_in_breathing)
        });
        reaction.removeSymptom(new Symptom(R.string.wheals));

        ArrayList<Symptom> expected = new ArrayList<>();
        expected.add(new Symptom(R.string.dizziness));
        expected.add(new Symptom(R.string.difficulty_in_breathing));

        assertEquals(expected, reaction.getSymptoms());
    }

    @Test
    public void removeSymptomThatIsNotThere() {
        reaction.addSymptoms(new Symptom[] {
                new Symptom(R.string.wheals),
                new Symptom(R.string.dizziness),
                new Symptom(R.string.difficulty_in_breathing)
        });
        reaction.removeSymptom(new Symptom(R.string.cough));

        ArrayList<Symptom> expected = new ArrayList<>();
        expected.add(new Symptom(R.string.wheals));
        expected.add(new Symptom(R.string.dizziness));
        expected.add(new Symptom(R.string.difficulty_in_breathing));

        assertEquals(expected, reaction.getSymptoms());
    }
}