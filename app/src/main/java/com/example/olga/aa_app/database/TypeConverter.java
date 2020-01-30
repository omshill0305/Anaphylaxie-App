package com.example.olga.aa_app.database;

import com.example.olga.aa_app.database.entities.Profile;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TypeConverter {

    static private String isoDatePattern = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    static private String datePattern = "dd-MMM-yyyy";

    private static SimpleDateFormat timestampFormatter = new SimpleDateFormat(isoDatePattern);
    private static SimpleDateFormat dateFormatter = new SimpleDateFormat(isoDatePattern);

    @androidx.room.TypeConverter
    public static Timestamp fromStringToTimestamp(String value){

        try {
            return value == null ? null : new Timestamp(timestampFormatter.parse(value).getTime());
        }
        catch (ParseException e){
            e.printStackTrace();
        }

        return null;
    }

    @androidx.room.TypeConverter
    public static String timeStampToString(Timestamp timestamp){
        return timestamp == null ? null : timestampFormatter.format(timestamp);
    }

    @androidx.room.TypeConverter
    public static Date fromStringToDate(String value){
        try {
            return value == null ? null : dateFormatter.parse(value);
        }
        catch (ParseException e){
            e.printStackTrace();
        }

        return null;
    }

    @androidx.room.TypeConverter
    public static String dateToString(Date date){
        return date == null ? null : dateFormatter.format(date);
    }

    @androidx.room.TypeConverter
    public static Profile.Gender stringToGender(String value){
        switch (value) {
            case "male":
                return Profile.Gender.Male;
            case "female":
                return Profile.Gender.Female;
            case "diverse":
                return Profile.Gender.Diverse;
            default:
                return null;
        }
    }

    @androidx.room.TypeConverter
    public static String genderToString(Profile.Gender gender){
        switch (gender) {
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
}
