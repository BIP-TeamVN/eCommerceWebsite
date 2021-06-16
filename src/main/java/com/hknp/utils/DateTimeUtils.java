package com.hknp.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

/**
 * This class provide methods that help you to work with <b>Date time</b>
 */
public class DateTimeUtils {
   /**
    * Convert Date to String by pattern
    *
    * @param date          Date to format
    * @param formatPattern the pattern describing the date and time format, ex "yyyy-MM-dd HH:mm:ss"
    * @return Date String in format
    * @see SimpleDateFormat#SimpleDateFormat(String)
    * @see DateFormat#format(Date)
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
    * @return <code>java.util.Date</code> for converted successfully<br>
    * <code>null</code> otherwise
    * @see SimpleDateFormat#SimpleDateFormat(String)
    * @see SimpleDateFormat#format(Date)
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

   /**
    * Get current date in datatype {@link java.sql.Date}
    *
    * @return <code>java.util.Date</code> for converted successfully<br>
    * <code>null</code> otherwise
    * @see java.sql.Date#valueOf(String)
    * @see LocalDate#now()
    */
   public static java.sql.Date currentDate() {
      return java.sql.Date.valueOf(LocalDate.now());
   }
}
