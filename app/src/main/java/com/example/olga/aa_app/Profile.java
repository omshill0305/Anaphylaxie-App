package com.example.olga.aa_app;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Profile implements Serializable {

    public enum Gender {
        Male, Female, Diverse
    }

    public static Profile currentProfile = null;

    private ArrayList<Reaction> reactions = new ArrayList<>();
    private Reaction currentReaction = null;

    //Profilinformationen
    private String name;
    private Date birthday;
    private Gender sex;
    private ArrayList<String> allergies;
    private boolean asthma;

    //Notfallset
    private String antihistamine;
    private String antihistamineDosage;
    private String steroid;
    private String steroidDosage;
    private String autoinjector;
    private boolean salbutamol;

    public Profile(String name, int birthYear, int birthMonth, int birthDay, Gender sex, ArrayList<String> allergies,
                   boolean asthma, String antihistamine, String antihistamineDosage, String steroid,
                   String steroidDosage, String autoinjector, boolean salbutamol) {
        this.name = name;
        Calendar calendar = new GregorianCalendar(birthYear, birthMonth, birthDay);
        this.birthday = calendar.getTime();
        this.sex = sex;
        this.allergies = allergies;
        this.asthma = asthma;
        this.antihistamine = antihistamine;
        this.antihistamineDosage = antihistamineDosage;
        this.steroid = steroid;
        this.steroidDosage = steroidDosage;
        this.autoinjector = autoinjector;
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
        if (currentReaction == null) startReaction(new Reaction());
        return currentReaction;
    }

    public String getName() {
        return name;
    }

    public void changeProfilName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void changeBirthday(Date birthday) {
        if (birthday.before(new Date(System.currentTimeMillis()))) {
            this.birthday = birthday;
        }
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
            if (now.get(Calendar.DATE) < birthDay.get(Calendar.DATE)) {
                months--;
            }
        } else if (months == 0 && now.get(Calendar.DATE) < birthDay.get(Calendar.DATE)) {
            years--;
            months = 11;
        }

        //Calculate the days
        if (now.get(Calendar.DATE) > birthDay.get(Calendar.DATE)) {
            days = now.get(Calendar.DATE) - birthDay.get(Calendar.DATE);
        } else if (now.get(Calendar.DATE) < birthDay.get(Calendar.DATE)) {
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

    public Gender getSex() {
        return sex;
    }

    public void changeSex(Gender sex) {
        this.sex = sex;

    }

    public ArrayList<String> getAllergies() {
        return allergies;
    }

    public void changeAllergies(ArrayList<String> allergies) {
        this.allergies = allergies;
    }

    public void addAllergy(String allergy) {
        if (!(allergies.contains(allergy))) {
            allergies.add(allergy);
        }
    }

    public void removeAllergy(String allergy) {
        if (allergies.contains(allergy)) {
            allergies.remove(allergy);
        }
    }

    public boolean hasAsthma() {
        return asthma;
    }

    public void changeAsthma(boolean asthma) {
        this.asthma = asthma;
    }

    public String getAntihistamine() {
        return antihistamine;
    }

    public void changeAntihistaminikumName(String antihistamine) {
        this.antihistamine = antihistamine;
    }

    public String getAntihistamineDosage() {
        return antihistamineDosage;
    }

    public void changeAntihistaminikumDosierung(String antihistamineDosage) {
        this.antihistamineDosage = antihistamineDosage;
    }

    public String getSteroid() {
        return steroid;
    }

    public void changeSteroidName(String steroidName) {
        this.steroid = steroidName;
    }

    public String getSteroidDosage() {
        return steroidDosage;
    }

    public void changeSteroidDosierung(String steroid) {
        this.steroidDosage = steroid;
    }

    public String getAutoinjector() {
        return autoinjector;
    }

    public void changeAutoinjektorName(String autoinjector) {
        this.autoinjector = autoinjector;
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
        for (String allergy : allergies) {
            allergiesString += allergy + " ";
        }
        String asthmaString = asthma ? "JA" : "NEIN";
        String profilinfoString = "Name: " + name
            + "\nAlter: " + getAge()
            + "\nGeschlecht: " + sex
            + "\nAllergien: " + allergiesString
            + "\nAsthma: " + asthmaString;
        String salbutamolString = salbutamol ? "JA" : "NEIN";
        String notfallsetString = "Antihistaminikum: " + antihistamine + ", " + antihistamineDosage
            + "\nSteroid: " + steroid + ", " + steroidDosage
            + "\nAutoinjektor: " + autoinjector
            + "\nSalbutamol: " + salbutamolString;
        return profilinfoString + "\n" + notfallsetString;
    }

    /**
     * Compares the field values of the profiles.
     * <p>
     * When sending data from one activity to another through serialization and deserialization, the object will be
     * different, meaning there will be two different references. In this case object equality would always return
     * false. That's why this method 'deep' compares the actual values of the objects.
     *
     * @param profile The other profile object.
     * @return True if all fields have the same value, else it will return false.
     */
    public boolean same(Profile profile) {
        if (profile != null) {
            return false;
        }
        return name.equals(profile.name)
               && birthday.compareTo(profile.birthday) == 0
               && sex == profile.sex
               && allergies.equals(profile.allergies)
               && asthma == profile.asthma
               && antihistamine.equals(profile.antihistamine)
               && antihistamineDosage.equals(profile.antihistamineDosage)
               && steroid.equals(profile.steroid)
               && steroidDosage.equals(profile.steroidDosage)
               && autoinjector.equals(profile.autoinjector)
               && salbutamol == profile.salbutamol;
    }
}
