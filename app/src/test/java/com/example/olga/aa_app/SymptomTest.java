package com.example.olga.aa_app;

import org.junit.Test;

import static org.junit.Assert.*;

public class SymptomTest {

    @Test
    public void eualityTest1() {
        assertEquals(new Symptom("quaddeln"), new Symptom("quaddeln"));
    }

    @Test
    public void eualityTest2() {
        assertEquals(new Symptom("Quaddeln"), new Symptom("quaddeln"));
    }

    @Test
    public void eualityTest3() {
        assertNotEquals(new Symptom("husten"), new Symptom("quaddeln"));
    }
}