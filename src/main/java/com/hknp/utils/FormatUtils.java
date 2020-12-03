package com.hknp.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Date;

public class FormatUtils {
   /**
    * Convert Date to String by pattern
    *
    * @param date          Date to format
    * @param formatPattern the pattern describing the date and time format, ex "yyyy-MM-dd HH:mm:ss"
    * @return String
    */
   public static String dateToString(Date date, String formatPattern) {
      DateFormat dateFormat = new SimpleDateFormat(formatPattern);
      return dateFormat.format(date);
   }

   /**
    * Convert String to Date
    *
    * @param str           String to convert
    * @param formatPattern the pattern describing the date and time format, ex "yyyy-MM-dd HH:mm:ss"
    * @return Date
    */
   public static Date stringToDate(String str, String formatPattern) {
      SimpleDateFormat format = new SimpleDateFormat(formatPattern);
      String dateString = format.format(new Date());
      try {
         return format.parse(str);
      } catch (ParseException e) {
         e.printStackTrace();
      }
      return new Date();
   }
}
