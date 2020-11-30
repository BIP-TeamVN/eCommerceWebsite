package com.hknp.utils;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class FormatUtils {
    public static String dateToString(Date date, String formatPattern) {
        DateFormat dateFormat = new SimpleDateFormat(formatPattern);
        return dateFormat.format(date);
    }
    public static Date stringToDate(String str, String formatPattern) {
        SimpleDateFormat format = new SimpleDateFormat(formatPattern);
        String dateString = format.format( new Date()   );
        try {
            return format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }
}
