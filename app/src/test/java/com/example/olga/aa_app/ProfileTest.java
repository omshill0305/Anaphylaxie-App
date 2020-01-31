package com.example.olga.aa_app;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;

public class ProfileTest {
    Profile profile;

    //changeBirthday, changeSex, addAllergy, removeAllergy,

    @Before
    public void before() {
        ArrayList<String> allergies = new ArrayList<>();
        allergies.add("Erdnüsse");
        allergies.add("Hühnerei");
        allergies.add("Hausstaub");
        profile = new Profile("Emma", 1997, 10, 19, Profile.Gender.Female,
                allergies, true, "Antih", "3 mg",
                "Stero", "5 mg", "Auto", true);
    }

    @Test
    public void objectCreation() {
        String profilinfoString = "Name: Emma" +
                "\nAlter: 22" +
                "\nGeschlecht: Female" +
                "\nAllergien: Erdnüsse Hühnerei Hausstaub " +
                "\nAsthma: JA" +
                "\nAntihistaminikum: Antih, 3 mg" +
                "\nSteroid: Stero, 5 mg" +
                "\nAutoinjektor: Auto" +
                "\nSalbutamol: JA";
        assertEquals(profilinfoString, profile.toString());
    }

    @Test
    public void startReaction() {
        Reaction reaction = new Reaction();
        profile.startReaction(reaction);
        assertEquals(reaction, profile.getCurrentReaction());
    }

    @Test
    public void endReaction() {
        Reaction reaction = new Reaction();
        profile.startReaction(reaction);
        assertEquals(reaction, profile.getCurrentReaction());
        profile.endReaction();
        assertNotEquals(reaction, profile.getCurrentReaction());
    }
}