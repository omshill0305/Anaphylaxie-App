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
        assertEquals("Es handelt sich wahrscheinlich um eine beginnende anaphylaktische Reaktion\n" +
                        "Bitte verabreichen Sie: <AntihistaminikumDosierung> des Antihistaminikums <AntihistaminikumName> und <SteroidDosierung> des Steroids <SteroidName>\n" +
                        "Bitte AA bereithalten, im Zweifelsfall bitte auch benutzen" +
                        "Hinweis zum Notruf, grüner Rahmen",
                Algorithm.evaluate());
    }

    @Test
    public void evaluateAlgorithm1_2() {
        reaction.addSymptoms(
                new Symptom[] {
                        new Symptom("quaddeln"),
                        new Symptom("schwellung von lippen und gesicht"),
                        new Symptom("jucken")
                });
        assertEquals("Es handelt sich wahrscheinlich um eine beginnende anaphylaktische Reaktion\n" +
                        "Bitte verabreichen Sie: <AntihistaminikumDosierung> des Antihistaminikums <AntihistaminikumName> und <SteroidDosierung> des Steroids <SteroidName>\n" +
                        "Bitte AA bereithalten, im Zweifelsfall bitte auch benutzen" +
                        "Hinweis zum Notruf, grüner Rahmen",
                Algorithm.evaluate());
    }

    @Test
    public void evaluateAlgorithm2_1() {
        reaction.addSymptom(new Symptom("husten"));
        assertEquals("Es bestehen Anzeichen für eine schwere Reaktion\n" +
                        "Bitte Fastjekt verabreichen (Grafik, wo + Hinweis auf Video ganz unten)\n" +
                        "Bitte verabreichen Sie: <AntihistaminikumDosierung> des Antihistaminikums <AntihistaminikumName> und <SteroidDosierung> des Steroids <SteroidName>\n" +
                        "Hinweis zum Notruf, roter Rahmen",
                Algorithm.evaluate());
    }

    @Test
    public void evaluateAlgorithm2_2() {
        reaction.addSymptoms(
                new Symptom[] {
                        new Symptom("quaddeln"),
                        new Symptom("durchfall")
                });
        assertEquals("Es bestehen Anzeichen für eine schwere Reaktion\n" +
                        "Bitte Fastjekt verabreichen (Grafik, wo + Hinweis auf Video ganz unten)\n" +
                        "Bitte verabreichen Sie: <AntihistaminikumDosierung> des Antihistaminikums <AntihistaminikumName> und <SteroidDosierung> des Steroids <SteroidName>\n" +
                        "Hinweis zum Notruf, roter Rahmen",
                Algorithm.evaluate());
    }

    @Test
    public void evaluateAlgorithm2_3() {
        reaction.addSymptom(new Symptom("plötzliche heiserkeit"));
        assertEquals("Es bestehen Anzeichen für eine schwere Reaktion\n" +
                        "Bitte Fastjekt verabreichen (Grafik, wo + Hinweis auf Video ganz unten)\n" +
                        "Bitte verabreichen Sie: <AntihistaminikumDosierung> des Antihistaminikums <AntihistaminikumName> und <SteroidDosierung> des Steroids <SteroidName>\n" +
                        "Hinweis zum Notruf, roter Rahmen",
                Algorithm.evaluate());
    }

    @Test
    public void evaluateAlgorithm2_4() {
        reaction.addSymptoms(
                new Symptom[] {
                        new Symptom("quaddeln"),
                        new Symptom("plötzliche heiserkeit")
                });
        assertEquals("Es bestehen Anzeichen für eine schwere Reaktion\n" +
                        "Bitte Fastjekt verabreichen (Grafik, wo + Hinweis auf Video ganz unten)\n" +
                        "Bitte verabreichen Sie: <AntihistaminikumDosierung> des Antihistaminikums <AntihistaminikumName> und <SteroidDosierung> des Steroids <SteroidName>\n" +
                        "Hinweis zum Notruf, roter Rahmen",
                Algorithm.evaluate());
    }

    @Test
    public void evaluateAlgorithm3_1() {
        reaction.addSymptom(new Symptom("atemnot"));

        assertEquals("Es bestehen Anzeichen für eine schwere Reaktion\n" +
                        "Bitte Fastjekt verabreichen (Grafik, wo + Hinweis auf Video ganz unten)\n" +
                        "Patient_in mit dem Oberkörper nach oben lagern\n" +
                        "IF SALBUTAMOL(Bitte Salbutamol verabreichen)\n" +
                        "Bitte verabreichen Sie: <AntihistaminikumDosierung> des Antihistaminikums <AntihistaminikumName> und <SteroidDosierung> des Steroids <SteroidName>\n" +
                        "Hinweis zum Notruf, roter Rahmen",
                Algorithm.evaluate());
    }

    @Test
    public void evaluateAlgorithm3_2() {
        reaction.addSymptoms(
                new Symptom[] {
                        new Symptom("quaddeln"),
                        new Symptom("atemnot")
                });
        assertEquals("Es bestehen Anzeichen für eine schwere Reaktion\n" +
                        "Bitte Fastjekt verabreichen (Grafik, wo + Hinweis auf Video ganz unten)\n" +
                        "Patient_in mit dem Oberkörper nach oben lagern\n" +
                        "IF SALBUTAMOL(Bitte Salbutamol verabreichen)\n" +
                        "Bitte verabreichen Sie: <AntihistaminikumDosierung> des Antihistaminikums <AntihistaminikumName> und <SteroidDosierung> des Steroids <SteroidName>\n" +
                        "Hinweis zum Notruf, roter Rahmen",
                Algorithm.evaluate());
    }

    @Test
    public void evaluateAlgorithm3_3() {
        reaction.addSymptoms(
                new Symptom[] {
                        new Symptom("quaddeln"),
                        new Symptom("plötzliche heiserkeit"),
                        new Symptom("atemnot")
                });
        assertEquals("Es bestehen Anzeichen für eine schwere Reaktion\n" +
                        "Bitte Fastjekt verabreichen (Grafik, wo + Hinweis auf Video ganz unten)\n" +
                        "Patient_in mit dem Oberkörper nach oben lagern\n" +
                        "IF SALBUTAMOL(Bitte Salbutamol verabreichen)\n" +
                        "Bitte verabreichen Sie: <AntihistaminikumDosierung> des Antihistaminikums <AntihistaminikumName> und <SteroidDosierung> des Steroids <SteroidName>\n" +
                        "Hinweis zum Notruf, roter Rahmen",
                Algorithm.evaluate());
    }

    @Test
    public void evaluateAlgorithm4_1() {
        reaction.addSymptom(new Symptom("blutdruckabfall"));
        assertEquals("Es bestehen Anzeichen für eine schwere Reaktion\n" +
                        "Bitte Fastjekt verabreichen (Grafik, wo + Hinweis auf Video ganz unten)\n" +
                        "Patient_in mit dem Oberkörper nach oben lagern\n" +
                        "Bitte verabreichen Sie: <AntihistaminikumDosierung> des Antihistaminikums <AntihistaminikumName> und <SteroidDosierung> des Steroids <SteroidName>\n" +
                        "Hinweis zum Notruf, roter Rahmen",
                Algorithm.evaluate());
    }

    @Test
    public void evaluateAlgorithm4_2() {
        reaction.addSymptoms(
                new Symptom[] {
                        new Symptom("quaddeln"),
                        new Symptom("blutdruckabfall")
                });
        assertEquals("Es bestehen Anzeichen für eine schwere Reaktion\n" +
                        "Bitte Fastjekt verabreichen (Grafik, wo + Hinweis auf Video ganz unten)\n" +
                        "Patient_in mit dem Oberkörper nach oben lagern\n" +
                        "Bitte verabreichen Sie: <AntihistaminikumDosierung> des Antihistaminikums <AntihistaminikumName> und <SteroidDosierung> des Steroids <SteroidName>\n" +
                        "Hinweis zum Notruf, roter Rahmen",
                Algorithm.evaluate());
    }

    @Test
    public void evaluateAlgorithm4_3() {
        reaction.addSymptoms(
                new Symptom[] {
                        new Symptom("plötzliche heiserkeit"),
                        new Symptom("blutdruckabfall")
                });
        assertEquals("Es bestehen Anzeichen für eine schwere Reaktion\n" +
                        "Bitte Fastjekt verabreichen (Grafik, wo + Hinweis auf Video ganz unten)\n" +
                        "Patient_in mit dem Oberkörper nach oben lagern\n" +
                        "Bitte verabreichen Sie: <AntihistaminikumDosierung> des Antihistaminikums <AntihistaminikumName> und <SteroidDosierung> des Steroids <SteroidName>\n" +
                        "Hinweis zum Notruf, roter Rahmen",
                Algorithm.evaluate());
    }

    @Test
    public void evaluateAlgorithm4_4() {
        reaction.addSymptoms(
                new Symptom[] {
                        new Symptom("atemnot"),
                        new Symptom("blutdruckabfall")
                });
        assertEquals("Es bestehen Anzeichen für eine schwere Reaktion\n" +
                        "Bitte Fastjekt verabreichen (Grafik, wo + Hinweis auf Video ganz unten)\n" +
                        "Patient_in mit dem Oberkörper nach oben lagern\n" +
                        "Bitte verabreichen Sie: <AntihistaminikumDosierung> des Antihistaminikums <AntihistaminikumName> und <SteroidDosierung> des Steroids <SteroidName>\n" +
                        "Hinweis zum Notruf, roter Rahmen",
                Algorithm.evaluate());
    }

    @Test
    public void evaluateAlgorithm5_1() {
        reaction.addSymptom(new Symptom("bewusstlosigkeit"));
        assertEquals("Es bestehen Anzeichen für eine schwere Reaktion\n" +
                        "Bitte Fastjekt verabreichen (Grafik, wo + Hinweis auf Video ganz unten)\n" +
                        "Patient_in in der stabilen Seitenlage lagern\n" +
                        "Bitte verabreichen Sie: <AntihistaminikumDosierung> des Antihistaminikums <AntihistaminikumName> und <SteroidDosierung> des Steroids <SteroidName>\n" +
                        "Hinweis zum Notruf, roter Rahmen",
                Algorithm.evaluate());
    }

    @Test
    public void evaluateAlgorithm5_2() {
        reaction.addSymptoms(
                new Symptom[] {
                        new Symptom("quaddeln"),
                        new Symptom("blutdruckabfall"),
                        new Symptom("bewusstlosigkeit")
                });
        assertEquals("Es bestehen Anzeichen für eine schwere Reaktion\n" +
                        "Bitte Fastjekt verabreichen (Grafik, wo + Hinweis auf Video ganz unten)\n" +
                        "Patient_in in der stabilen Seitenlage lagern\n" +
                        "Bitte verabreichen Sie: <AntihistaminikumDosierung> des Antihistaminikums <AntihistaminikumName> und <SteroidDosierung> des Steroids <SteroidName>\n" +
                        "Hinweis zum Notruf, roter Rahmen",
                Algorithm.evaluate());
    }

    @Test
    public void evaluateAlgorithm5_3() {
        reaction.addSymptoms(
                new Symptom[] {
                        new Symptom("plötzliche heiserkeit"),
                        new Symptom("blutdruckabfall"),
                        new Symptom("bewusstlosigkeit")
                });
        assertEquals("Es bestehen Anzeichen für eine schwere Reaktion\n" +
                        "Bitte Fastjekt verabreichen (Grafik, wo + Hinweis auf Video ganz unten)\n" +
                        "Patient_in in der stabilen Seitenlage lagern\n" +
                        "Bitte verabreichen Sie: <AntihistaminikumDosierung> des Antihistaminikums <AntihistaminikumName> und <SteroidDosierung> des Steroids <SteroidName>\n" +
                        "Hinweis zum Notruf, roter Rahmen",
                Algorithm.evaluate());
    }

    @Test
    public void evaluateAlgorithm5_4() {
        reaction.addSymptoms(
                new Symptom[] {
                        new Symptom("atemnot"),
                        new Symptom("blutdruckabfall"),
                        new Symptom("bewusstlosigkeit")
                });
        assertEquals("Es bestehen Anzeichen für eine schwere Reaktion\n" +
                        "Bitte Fastjekt verabreichen (Grafik, wo + Hinweis auf Video ganz unten)\n" +
                        "Patient_in in der stabilen Seitenlage lagern\n" +
                        "Bitte verabreichen Sie: <AntihistaminikumDosierung> des Antihistaminikums <AntihistaminikumName> und <SteroidDosierung> des Steroids <SteroidName>\n" +
                        "Hinweis zum Notruf, roter Rahmen",
                Algorithm.evaluate());
    }


}