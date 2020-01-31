package com.example.olga.aa_app;

import org.junit.Test;

import static org.junit.Assert.*;

public class SymptomTest {

    @Test
    public void equalityTest1() {
        assertEquals(new Symptom(R.string.wheals), new Symptom(R.string.wheals));
    }

    @Test
    public void equalityTest3() {
        assertNotEquals(new Symptom(R.string.cough), new Symptom(R.string.wheals));
    }
}