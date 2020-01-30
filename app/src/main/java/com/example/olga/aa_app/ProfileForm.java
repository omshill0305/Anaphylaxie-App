package com.example.olga.aa_app;

import com.example.olga.aa_app.database.entities.EmergencySet;
import com.example.olga.aa_app.database.entities.Profile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class ProfileForm implements Serializable {

    private ArrayList<Reaction> reactions = new ArrayList<>();
    private Reaction currentReaction = null;


    private Profile profile;
    private long id;
    private List<EmergencySet> emergencySets;

    //Profilinformationen
    private String name;
    private Date birthday;
    private Profile.Gender gender;
    private ArrayList<String> allergies;
    private boolean asthma;

    //Notfallset
    private String antihistamine;
    private String antihistamineDosage;
    private String steroid;
    private String steroidDosage;
    private String autoinjector;
    private boolean salbutamol;

    public ProfileForm(){

    }

    //TODO: Add allergies to profileForm
    public ProfileForm(Profile profile, List<EmergencySet> emergencySetList) {
        this.profile = profile;
        this.id = profile.getProfileId();
        this.emergencySets = emergencySetList;

        this.name = profile.getName();
        this.birthday = profile.getBirthday();
        this.gender = profile.getGender();
        // allergies
        this.asthma = profile.hasAsthma();

        this.antihistamine = emergencySetList.get(0).getBrandName();
        this.antihistamineDosage = String.valueOf(emergencySetList.get(0).getDosage());
        this.steroid = emergencySetList.get(1).getBrandName();
        this.steroidDosage = String.valueOf(emergencySetList.get(1).getDosage());
        this.autoinjector = emergencySetList.get(2).getBrandName();
        this.salbutamol = profile.isSalbutamol();
    }

    public ProfileForm(ProfileForm profileForm){
        this.profile = profileForm.profile;
        this.id = profileForm.getId();
        this.emergencySets = profileForm.getEmergencySets();

        this.name = profileForm.getName();
        this.birthday = profileForm.getBirthday();
        this.gender = profileForm.getGender();
        // allergies
        this.asthma = profileForm.hasAsthma();

        this.antihistamine = profileForm.getEmergencySets().get(0).getBrandName();
        this.antihistamineDosage = profileForm.getEmergencySets().get(0).getDosage();
        this.steroid = profileForm.getEmergencySets().get(1).getBrandName();
        this.steroidDosage = profileForm.getEmergencySets().get(1).getDosage();
        this.autoinjector = profileForm.getEmergencySets().get(2).getBrandName();
        this.salbutamol = profile.isSalbutamol();
    }

    public void changeProfileForm(String name, int birthYear, int birthMonth, int birthDay, Profile.Gender gender, ArrayList<String> allergies,
                                  boolean asthma, String antihistamine, String antihistamineDosage, String steroid,
                                  String steroidDosage, String autoinjector, boolean salbutamol)
    {
        this.name = name;
        Calendar calendar = new GregorianCalendar(birthYear, birthMonth, birthDay);
        this.birthday = calendar.getTime();
        this.gender = gender;
        this.allergies = allergies;
        this.asthma = asthma;
        this.antihistamine = antihistamine;
        this.antihistamineDosage = antihistamineDosage;
        this.steroid = steroid;
        this.steroidDosage = steroidDosage;
        this.autoinjector = autoinjector;
        this.salbutamol = salbutamol;
    }

    public Profile getProfile() {
        return profile;
    }

    public List<EmergencySet> getEmergencySets() {
        return emergencySets;
    }

    public void startReaction(Reaction reaction) {
        reactions.add(reaction);
        currentReaction = reaction;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void endReaction() {
        currentReaction = null;
    }

    public Reaction getCurrentReaction() {
        return currentReaction;
    }

    public String getName() {
        return name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public Profile.Gender getGender() {
        return gender;
    }

    public ArrayList<String> getAllergies() {
        return allergies;
    }

    public boolean hasAsthma() {
        return asthma;
    }

    public String getAntihistamine() {
        return antihistamine;
    }

    public String getAntihistamineDosage() {
        return antihistamineDosage;
    }

    public String getSteroid() {
        return steroid;
    }

    public String getSteroidDosage() {
        return steroidDosage;
    }

    public String getAutoinjector() {
        return autoinjector;
    }

    public boolean takesSalbutamol() {
        return salbutamol;
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

    public String genderToString(){
        switch (this.gender) {
            case Male:
                return "male";
            case Female:
                return "female";
            case Diverse:
                return "diverse";
            default:
                return "";
        }
    }

    /*@Override
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
    }*/

    /**
     * Compares the field values of the profiles.
     * <p>
     * When sending data from one activity to another through serialization and deserialization, the object will be
     * different, meaning there will be two different references. In this case object equality would always return
     * false. That's why this method 'deep' compares the actual values of the objects.
     *
     * @param profileForm The other profile object.
     * @return True if all fields have the same value, else it will return false.
     */
    public boolean same(ProfileForm profileForm) {
        if (profileForm != null) {
            return false;
        }
        return this.name.equals(profileForm.name)
                && this.birthday.compareTo(profileForm.birthday) == 0
                && this.gender == profileForm.gender
                && this.allergies.equals(profileForm.allergies)
                && this.asthma == profileForm.asthma
                && this.antihistamine.equals(profileForm.antihistamine)
                && this.antihistamineDosage.equals(profileForm.antihistamineDosage)
                && this.steroid.equals(profileForm.steroid)
                && this.steroidDosage.equals(profileForm.steroidDosage)
                && this.autoinjector.equals(profileForm.autoinjector)
                && this.salbutamol == profileForm.salbutamol;
    }
}
