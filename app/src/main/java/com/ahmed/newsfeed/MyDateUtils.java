package com.ahmed.newsfeed;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MyDateUtils {
    public static String convertDate(String fromFormat, String toFormat, String date, Locale fromLocale, Locale toLocale){
        SimpleDateFormat dateFormat = new SimpleDateFormat(fromFormat, fromLocale);
        try {
            Date parse = dateFormat.parse(date);
            dateFormat = new SimpleDateFormat(toFormat, toLocale);
            if(parse != null)
                return dateFormat.format(parse);
            else
                return date;
        } catch (ParseException e) {
            e.printStackTrace();
            return date;
        }
    }
}
