package com.example.olga.aa_app.database;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TypeConverter {

    static private String isoDatePattern = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    private static SimpleDateFormat formatter = new SimpleDateFormat(isoDatePattern);

    @androidx.room.TypeConverter
    public static Timestamp fromStringTimestamp(String value){

        try {
            return value == null ? null : new Timestamp(formatter.parse(value).getTime());
        }
        catch (ParseException e){
            e.printStackTrace();
        }

        return null;
    }

    @androidx.room.TypeConverter
    public static String timeStampToString(Timestamp timestamp){
        return timestamp == null ? null: formatter.format(timestamp);
    }
}
