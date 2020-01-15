package com.example.olga.aa_app;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Profile implements Serializable {
    private ArrayList<Reaction> reactions = new ArrayList<>();
    private Reaction currentReaction = null;

    //Profilinformationen
    private String profilName;
    private Date birthday;
    private String sex;
    private ArrayList<String> allergies;
    private boolean asthma;

    //Notfallset
    private String antihistaminikumName;
    private String antihistaminikumDosierung;
    private String steroidName;
    private String steroidDosierung;
    private String autoinjektorName;
    private boolean salbutamol;

    public Profile(String profilName, int birthYear, int birthMonth, int birthDay, String sex, ArrayList<String> allergies,
                   boolean asthma, String antihistaminikumName, String antihistaminikumDosierung,
                   String steroidName, String steroidDosierung, String autoinjektorName, boolean salbutamol) {
        this.profilName = profilName;
        Calendar calendar = new GregorianCalendar(birthYear, birthMonth, birthDay);
        this.birthday = calendar.getTime();
        this.sex = sex;
        this.allergies = allergies;
        this.asthma = asthma;
        this.antihistaminikumName = antihistaminikumName;
        this.antihistaminikumDosierung = antihistaminikumDosierung;
        this.steroidName = steroidName;
        this.steroidDosierung = steroidDosierung;
        this.autoinjektorName = autoinjektorName;
        this.salbutamol = salbutamol;
    }

    public void startReaction(Reaction reaction) {
        reactions.add(reaction);
        currentReaction = reaction;
    }

    public void endReaction() {
        currentReaction = null;
    }

    public Reaction getCurrentReaction() {
        return currentReaction;
    }

    public String getProfilName() {
        return profilName;
    }

    public void changeProfilName(String profilName) {
        this.profilName = profilName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void changeBirthday(Date birthday) {
        if (birthday.before(new Date(System.currentTimeMillis()))) this.birthday = birthday;
    }

    public int getAge() {
        //from https://howtodoinjava.com/java/calculate-age-from-date-of-birth/
        int years = 0;
        int months = 0;
        int days = 0;

        //create calendar object for birth day
        Calendar birthDay = Calendar.getInstance();
        birthDay.setTimeInMillis(this.birthday.getTime());

        //create calendar object for current day
        long currentTime = System.currentTimeMillis();
        Calendar now = Calendar.getInstance();
        now.setTimeInMillis(currentTime);

        //Get difference between years
        years = now.get(Calendar.YEAR) - birthDay.get(Calendar.YEAR);
        int currMonth = now.get(Calendar.MONTH) + 1;
        int birthMonth = birthDay.get(Calendar.MONTH) + 1;

        //Get difference between months
        months = currMonth - birthMonth;

        //if month difference is in negative then reduce years by one
        //and calculate the number of months.
        if (months < 0) {
            years--;
            months = 12 - birthMonth + currMonth;
            if (now.get(Calendar.DATE) < birthDay.get(Calendar.DATE))
                months--;
        } else if (months == 0 && now.get(Calendar.DATE) < birthDay.get(Calendar.DATE)) {
            years--;
            months = 11;
        }

        //Calculate the days
        if (now.get(Calendar.DATE) > birthDay.get(Calendar.DATE))
            days = now.get(Calendar.DATE) - birthDay.get(Calendar.DATE);
        else if (now.get(Calendar.DATE) < birthDay.get(Calendar.DATE)) {
            int today = now.get(Calendar.DAY_OF_MONTH);
            now.add(Calendar.MONTH, -1);
            days = now.getActualMaximum(Calendar.DAY_OF_MONTH) - birthDay.get(Calendar.DAY_OF_MONTH) + today;
        } else {
            days = 0;
            if (months == 12) {
                years++;
                months = 0;
            }
        }
        return years;
    }

    public String getSex() {
        return sex;
    }

    public void changeSex(String sex) {
        if (sex.equalsIgnoreCase("m") ||
                sex.equalsIgnoreCase("w") ||
                sex.equalsIgnoreCase("d"))
            this.sex = sex;
    }

    public ArrayList<String> getAllergies() {
        return allergies;
    }

    public void changeAllergies(ArrayList<String> allergies) {
        this.allergies = allergies;
    }

    public void addAllergy(String allergy) {
        if (!(allergies.contains(allergy))) allergies.add(allergy);
    }

    public void removeAllergy(String allergy) {
        if (allergies.contains(allergy)) allergies.remove(allergy);
    }

    public boolean hasAsthma() {
        return asthma;
    }

    public void changeAsthma(boolean asthma) {
        this.asthma = asthma;
    }

    public String getAntihistaminikumName() {
        return antihistaminikumName;
    }

    public void changeAntihistaminikumName(String antihistaminikumName) {
        this.antihistaminikumName = antihistaminikumName;
    }

    public String getAntihistaminikumDosierung() {
        return antihistaminikumDosierung;
    }

    public void changeAntihistaminikumDosierung(String antihistaminikumDosierung) {
        this.antihistaminikumDosierung = antihistaminikumDosierung;
    }

    public String getSteroidName() {
        return steroidName;
    }

    public void changeSteroidName(String steroidName) {
        this.steroidName = steroidName;
    }

    public String getSteroidDosierung() {
        return steroidDosierung;
    }

    public void changeSteroidDosierung(String steroidDosierung) {
        this.steroidDosierung = steroidDosierung;
    }

    public String getAutoinjektorName() {
        return autoinjektorName;
    }

    public void changeAutoinjektorName(String autoinjektorName) {
        this.autoinjektorName = autoinjektorName;
    }

    public boolean takesSalbutamol() {
        return salbutamol;
    }

    public void changeSalbutamol(boolean salbutamol) {
        this.salbutamol = salbutamol;
    }

    @Override
    public String toString() {
        String allergiesString = "";
        for (String allergy : allergies) allergiesString += allergy + " ";
        String asthmaString = asthma ? "JA" : "NEIN";
        String profilinfoString = "Name: " + profilName +
                "\nAlter: " + getAge() +
                "\nGeschlecht: " + sex +
                "\nAllergien: " + allergiesString +
                "\nAsthma: " + asthmaString;
        String salbutamolString = salbutamol ? "JA" : "NEIN";
        String notfallsetString = "Antihistaminikum: " + antihistaminikumName + ", " + antihistaminikumDosierung +
                "\nSteroid: " + steroidName + ", " + steroidDosierung +
                "\nAutoinjektor: " + autoinjektorName +
                "\nSalbutamol: " + salbutamolString;
        return profilinfoString + "\n" + notfallsetString;
    }

    public boolean equalProfileInformation(Profile p) {
        if (p != null) {
            return false;
        }
        return  profilName == p.profilName && birthday == p.birthday && sex == p.sex
                && allergies == p.allergies && asthma == p.asthma
                && antihistaminikumName == p.antihistaminikumName
                && antihistaminikumDosierung == p.antihistaminikumDosierung
                && steroidName == p.steroidName && steroidDosierung == p.steroidDosierung
                && autoinjektorName == p.autoinjektorName && salbutamol == p.salbutamol;
    }
}
