package com.hknp.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class DateTimeUtils {
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
    * Convert String to java.util.Date
    *
    * @param str           String to convert
    * @param formatPattern the pattern describing the date and time format, ex "yyyy-MM-dd HH:mm:ss"
    * @return java.util.Date
    */
   public static Date stringToDate(String str, String formatPattern) {
      SimpleDateFormat format = new SimpleDateFormat(formatPattern);
      Date result = null;

      try {
         result = format.parse(str);
      } catch (ParseException e) {
         e.printStackTrace();
         result = new Date();
      }

      return result;
   }

   public static java.sql.Date currentDate() {
      return java.sql.Date.valueOf(LocalDate.now());
   }
}
