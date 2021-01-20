package com.hknp.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class provide methods that help you to work with <b>String</b>
 */
public class StringUtils {
   /**
    * Remove non-digit character from string
    *
    * @param str     string to remove non-digit character
    * @return        <code>digit string</code> (without non-digit char)<br>
    *                <code>0</code> if null or empty
    */
   public static String removeNonDigit(String str) {
      if (str == null || str.isEmpty()) {
         str = "0";
      }

      str = str.replaceAll("[^0-9]", "");

      if (str == null || str.isEmpty()) {
         str = "0";
      }
      return str;
   }

   /**
    * Convert String to BigDecimal
    *
    * @param str     string to convert
    * @return        <code>BigDecimal value</code> if converted successfully<br>
    *                <code>0</code> otherwise
    */
   public static BigDecimal toBigDecimal(String str) {
      BigDecimal result;
      //str = removeNonDigit(str);

      try {
         result = new BigDecimal(str);
      } catch (NumberFormatException e) {
         result = BigDecimal.valueOf(0);
      }

      return result;
   }

   /**
    * Convert String to Long
    *
    * @param str     string to convert
    * @return        <code>Long value</code> if converted successfully<br>
    *                <code>0</code> otherwise
    */
   public static Long toLong(String str) {
      Long result;
      str = removeNonDigit(str);

      try {
         result = Long.parseLong(str);
      } catch (NumberFormatException e) {
         result = 0L;
      }

      return result;
   }

   /**
    * Convert String to Long
    *
    * @param str     string to convert
    * @return        <code>Integer value</code> if converted successfully<br>
    *                <code>0</code> otherwise
    */
   public static Integer toInt(String str) {
      Integer result;
      str = removeNonDigit(str);

      try {
         result = Integer.parseInt(str);
      } catch (NumberFormatException e) {
         result = 0;
      }

      return result;
   }

   /**
    * Split string to {@link List}&#60;{@link String}&#62;
    *
    * @param str     string to split
    * @param regex   the delimiting regular expression
    * @return        <code>{@link List}&#60;{@link String}&#62;</code> if split successfully<br>
    *                <code>Empty list</code> otherwise
    */
   public static List<String> splitToList(String str, String regex) {
      if (str == null || str.isEmpty()) {
         return Collections.emptyList();
      }

      String[] array = str.split(regex);
      List<String> result = new ArrayList<String>();
      for (int i = 0; i < array.length; i++) {
         result.add(array[i]);
      }
      return result;
   }
}
