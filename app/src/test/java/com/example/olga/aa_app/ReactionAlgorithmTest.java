package com.example.olga.aa_app;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class ReactionAlgorithmTest {
    private ReactionAlgorithm testObject;

    @Before
    public void before() {
        testObject = new ReactionAlgorithm();
    }

    @Test
    public void addOneSymptom() {
        testObject.addSymptom("quaddeln");
        assertEquals(new ArrayList<>(Arrays.asList("quaddeln")), testObject.getSymptoms());
    }

    @Test
    public void addMoreSymptomsAtOnce() {
        testObject.addSymptoms(new String[] {"quaddeln", "schwindel", "atemnot"});
        assertEquals(new ArrayList<>(Arrays.asList("quaddeln", "schwindel", "atemnot")), testObject.getSymptoms());
    }

    @Test
    public void addMoreSymptomsSequentially() {
        testObject.addSymptom("quaddeln");
        testObject.addSymptom("schwindel");
        testObject.addSymptom("atemnot");
        assertEquals(new ArrayList<>(Arrays.asList("quaddeln", "schwindel", "atemnot")), testObject.getSymptoms());
    }

    @Test
    public void addSymptomThatIsAlreadyThere() {
        testObject.addSymptom("quaddeln");
        testObject.addSymptom("schwindel");
        testObject.addSymptom("quaddeln");
        assertEquals(new ArrayList<>(Arrays.asList("quaddeln", "schwindel")), testObject.getSymptoms());
    }

    @Test
    public void removeSymptom() {
        testObject.addSymptoms(new String[] {"quaddeln", "schwindel", "atemnot"});
        testObject.removeSymptom("quaddeln");
        assertEquals(new ArrayList<>(Arrays.asList("schwindel", "atemnot")), testObject.getSymptoms());
    }

    @Test
    public void removeSymptomThatIsNotThere() {
        testObject.addSymptoms(new String[] {"quaddeln", "schwindel", "atemnot"});
        testObject.removeSymptom("husten");
        assertEquals(new ArrayList<>(Arrays.asList("quaddeln", "schwindel", "atemnot")), testObject.getSymptoms());
    }

    @Test
    public void evaluateAlgorithm1_1() {
        testObject.addSymptom("quaddeln");
        assertEquals("Es handelt sich wahrscheinlich um eine beginnende anaphylaktische Reaktion\n" +
                        "Bitte verabreichen Sie: <AntihistaminikumDosierung> des Antihistaminikums <AntihistaminikumName> und <SteroidDosierung> des Steroids <SteroidName>\n" +
                        "Bitte AA bereithalten, im Zweifelsfall bitte auch benutzen" +
                        "Hinweis zum Notruf, grüner Rahmen",
                     testObject.evaluateAlgorithm());
    }

    @Test
    public void evaluateAlgorithm1_2() {
        testObject.addSymptoms(new String[] {"quaddeln", "schwellung von lippen und gesicht", "jucken"});
        assertEquals("Es handelt sich wahrscheinlich um eine beginnende anaphylaktische Reaktion\n" +
                        "Bitte verabreichen Sie: <AntihistaminikumDosierung> des Antihistaminikums <AntihistaminikumName> und <SteroidDosierung> des Steroids <SteroidName>\n" +
                        "Bitte AA bereithalten, im Zweifelsfall bitte auch benutzen" +
                        "Hinweis zum Notruf, grüner Rahmen",
                testObject.evaluateAlgorithm());
    }

    @Test
    public void evaluateAlgorithm2_1() {
        testObject.addSymptom("husten");
        assertEquals("Es bestehen Anzeichen für eine schwere Reaktion\n" +
                        "Bitte Fastjekt verabreichen (Grafik, wo + Hinweis auf Video ganz unten)\n" +
                        "Bitte verabreichen Sie: <AntihistaminikumDosierung> des Antihistaminikums <AntihistaminikumName> und <SteroidDosierung> des Steroids <SteroidName>\n" +
                        "Hinweis zum Notruf, roter Rahmen",
                testObject.evaluateAlgorithm());
    }

    @Test
    public void evaluateAlgorithm2_2() {
        testObject.addSymptoms(new String[] {"quaddeln", "durchfall"});
        assertEquals("Es bestehen Anzeichen für eine schwere Reaktion\n" +
                        "Bitte Fastjekt verabreichen (Grafik, wo + Hinweis auf Video ganz unten)\n" +
                        "Bitte verabreichen Sie: <AntihistaminikumDosierung> des Antihistaminikums <AntihistaminikumName> und <SteroidDosierung> des Steroids <SteroidName>\n" +
                        "Hinweis zum Notruf, roter Rahmen",
                testObject.evaluateAlgorithm());
    }

    @Test
    public void evaluateAlgorithm2_3() {
        testObject.addSymptom("plötzliche heiserkeit");
        assertEquals("Es bestehen Anzeichen für eine schwere Reaktion\n" +
                        "Bitte Fastjekt verabreichen (Grafik, wo + Hinweis auf Video ganz unten)\n" +
                        "Bitte verabreichen Sie: <AntihistaminikumDosierung> des Antihistaminikums <AntihistaminikumName> und <SteroidDosierung> des Steroids <SteroidName>\n" +
                        "Hinweis zum Notruf, roter Rahmen",
                testObject.evaluateAlgorithm());
    }

    @Test
    public void evaluateAlgorithm2_4() {
        testObject.addSymptoms(new String[] {"quaddeln", "plötzliche heiserkeit"});
        assertEquals("Es bestehen Anzeichen für eine schwere Reaktion\n" +
                        "Bitte Fastjekt verabreichen (Grafik, wo + Hinweis auf Video ganz unten)\n" +
                        "Bitte verabreichen Sie: <AntihistaminikumDosierung> des Antihistaminikums <AntihistaminikumName> und <SteroidDosierung> des Steroids <SteroidName>\n" +
                        "Hinweis zum Notruf, roter Rahmen",
                testObject.evaluateAlgorithm());
    }

    @Test
    public void evaluateAlgorithm3_1() {
        testObject.addSymptom("atemnot");

        assertEquals("Es bestehen Anzeichen für eine schwere Reaktion\n" +
                        "Bitte Fastjekt verabreichen (Grafik, wo + Hinweis auf Video ganz unten)\n" +
                        "Patient_in mit dem Oberkörper nach oben lagern\n" +
                        "IF SALBUTAMOL(Bitte Salbutamol verabreichen)\n" +
                        "Bitte verabreichen Sie: <AntihistaminikumDosierung> des Antihistaminikums <AntihistaminikumName> und <SteroidDosierung> des Steroids <SteroidName>\n" +
                        "Hinweis zum Notruf, roter Rahmen",
                testObject.evaluateAlgorithm());
    }

    @Test
    public void evaluateAlgorithm3_2() {
        testObject.addSymptoms(new String[] {"quaddeln", "atemnot"});
        assertEquals("Es bestehen Anzeichen für eine schwere Reaktion\n" +
                        "Bitte Fastjekt verabreichen (Grafik, wo + Hinweis auf Video ganz unten)\n" +
                        "Patient_in mit dem Oberkörper nach oben lagern\n" +
                        "IF SALBUTAMOL(Bitte Salbutamol verabreichen)\n" +
                        "Bitte verabreichen Sie: <AntihistaminikumDosierung> des Antihistaminikums <AntihistaminikumName> und <SteroidDosierung> des Steroids <SteroidName>\n" +
                        "Hinweis zum Notruf, roter Rahmen",
                testObject.evaluateAlgorithm());
    }

    @Test
    public void evaluateAlgorithm3_3() {
        testObject.addSymptoms(new String[] {"quaddeln", "plötzliche heiserkeit", "atemnot"});
        assertEquals("Es bestehen Anzeichen für eine schwere Reaktion\n" +
                        "Bitte Fastjekt verabreichen (Grafik, wo + Hinweis auf Video ganz unten)\n" +
                        "Patient_in mit dem Oberkörper nach oben lagern\n" +
                        "IF SALBUTAMOL(Bitte Salbutamol verabreichen)\n" +
                        "Bitte verabreichen Sie: <AntihistaminikumDosierung> des Antihistaminikums <AntihistaminikumName> und <SteroidDosierung> des Steroids <SteroidName>\n" +
                        "Hinweis zum Notruf, roter Rahmen",
                testObject.evaluateAlgorithm());
    }

    @Test
    public void evaluateAlgorithm4_1() {
        testObject.addSymptom("blutdruckabfall");
        assertEquals("Es bestehen Anzeichen für eine schwere Reaktion\n" +
                        "Bitte Fastjekt verabreichen (Grafik, wo + Hinweis auf Video ganz unten)\n" +
                        "Patient_in mit dem Oberkörper nach oben lagern\n" +
                        "Bitte verabreichen Sie: <AntihistaminikumDosierung> des Antihistaminikums <AntihistaminikumName> und <SteroidDosierung> des Steroids <SteroidName>\n" +
                        "Hinweis zum Notruf, roter Rahmen",
                testObject.evaluateAlgorithm());
    }

    @Test
    public void evaluateAlgorithm4_2() {
        testObject.addSymptoms(new String[] {"quaddeln", "blutdruckabfall"});
        assertEquals("Es bestehen Anzeichen für eine schwere Reaktion\n" +
                        "Bitte Fastjekt verabreichen (Grafik, wo + Hinweis auf Video ganz unten)\n" +
                        "Patient_in mit dem Oberkörper nach oben lagern\n" +
                        "Bitte verabreichen Sie: <AntihistaminikumDosierung> des Antihistaminikums <AntihistaminikumName> und <SteroidDosierung> des Steroids <SteroidName>\n" +
                        "Hinweis zum Notruf, roter Rahmen",
                testObject.evaluateAlgorithm());
    }

    @Test
    public void evaluateAlgorithm4_3() {
        testObject.addSymptoms(new String[] {"plötzliche heiserkeit", "blutdruckabfall"}); //?
        assertEquals("Es bestehen Anzeichen für eine schwere Reaktion\n" +
                        "Bitte Fastjekt verabreichen (Grafik, wo + Hinweis auf Video ganz unten)\n" +
                        "Patient_in mit dem Oberkörper nach oben lagern\n" +
                        "Bitte verabreichen Sie: <AntihistaminikumDosierung> des Antihistaminikums <AntihistaminikumName> und <SteroidDosierung> des Steroids <SteroidName>\n" +
                        "Hinweis zum Notruf, roter Rahmen",
                testObject.evaluateAlgorithm());
    }

    @Test
    public void evaluateAlgorithm4_4() {
        testObject.addSymptoms(new String[] {"atemnot", "blutdruckabfall"}); //?
        assertEquals("Es bestehen Anzeichen für eine schwere Reaktion\n" +
                        "Bitte Fastjekt verabreichen (Grafik, wo + Hinweis auf Video ganz unten)\n" +
                        "Patient_in mit dem Oberkörper nach oben lagern\n" +
                        "Bitte verabreichen Sie: <AntihistaminikumDosierung> des Antihistaminikums <AntihistaminikumName> und <SteroidDosierung> des Steroids <SteroidName>\n" +
                        "Hinweis zum Notruf, roter Rahmen",
                testObject.evaluateAlgorithm());
    }

    @Test
    public void evaluateAlgorithm5_1() {
        testObject.addSymptom("bewusstlosigkeit");
        assertEquals("Es bestehen Anzeichen für eine schwere Reaktion\n" +
                        "Bitte Fastjekt verabreichen (Grafik, wo + Hinweis auf Video ganz unten)\n" +
                        "Patient_in in der stabilen Seitenlage lagern\n" +
                        "Bitte verabreichen Sie: <AntihistaminikumDosierung> des Antihistaminikums <AntihistaminikumName> und <SteroidDosierung> des Steroids <SteroidName>\n" +
                        "Hinweis zum Notruf, roter Rahmen",
                testObject.evaluateAlgorithm());
    }

    @Test
    public void evaluateAlgorithm5_2() {
        testObject.addSymptoms(new String[] {"quaddeln", "blutdruckabfall", "bewusstlosigkeit"});
        assertEquals("Es bestehen Anzeichen für eine schwere Reaktion\n" +
                        "Bitte Fastjekt verabreichen (Grafik, wo + Hinweis auf Video ganz unten)\n" +
                        "Patient_in in der stabilen Seitenlage lagern\n" +
                        "Bitte verabreichen Sie: <AntihistaminikumDosierung> des Antihistaminikums <AntihistaminikumName> und <SteroidDosierung> des Steroids <SteroidName>\n" +
                        "Hinweis zum Notruf, roter Rahmen",
                testObject.evaluateAlgorithm());
    }

    @Test
    public void evaluateAlgorithm5_3() {
        testObject.addSymptoms(new String[] {"plötzliche heiserkeit", "blutdruckabfall", "bewusstlosigkeit"});
        assertEquals("Es bestehen Anzeichen für eine schwere Reaktion\n" +
                        "Bitte Fastjekt verabreichen (Grafik, wo + Hinweis auf Video ganz unten)\n" +
                        "Patient_in in der stabilen Seitenlage lagern\n" +
                        "Bitte verabreichen Sie: <AntihistaminikumDosierung> des Antihistaminikums <AntihistaminikumName> und <SteroidDosierung> des Steroids <SteroidName>\n" +
                        "Hinweis zum Notruf, roter Rahmen",
                testObject.evaluateAlgorithm());
    }

    @Test
    public void evaluateAlgorithm5_4() {
        testObject.addSymptoms(new String[] {"atemnot", "blutdruckabfall", "bewusstlosigkeit"});
        assertEquals("Es bestehen Anzeichen für eine schwere Reaktion\n" +
                        "Bitte Fastjekt verabreichen (Grafik, wo + Hinweis auf Video ganz unten)\n" +
                        "Patient_in in der stabilen Seitenlage lagern\n" +
                        "Bitte verabreichen Sie: <AntihistaminikumDosierung> des Antihistaminikums <AntihistaminikumName> und <SteroidDosierung> des Steroids <SteroidName>\n" +
                        "Hinweis zum Notruf, roter Rahmen",
                testObject.evaluateAlgorithm());
    }
}