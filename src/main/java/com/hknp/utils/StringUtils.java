package com.hknp.utils;

import java.math.BigDecimal;

public class StringUtils {
   /**
    * Remove non-digit character from string
    *
    * @param str string to remove non-digit character
    * @return digit string or "0"
    */
   public static String removeNonDigit(String str) {
      str = str.replaceAll("[^0-9]", "");

      if (str == null || str.isEmpty()) {
         str = "0";
      }
      return str;
   }

   /**
    * Convert String to BigDecimal
    *
    * @param str string to remove non-digit character
    * @return 0 or BigDecimal value
    */
   public static BigDecimal toBigDecimal(String str) {
      BigDecimal result;
      str = removeNonDigit(str);

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
    * @param str string to remove non-digit character
    * @return 0 or Long value
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
    * Convert String to Integer
    *
    * @param str string to remove non-digit character
    * @return 0 or Integer value
    */
   public static Integer toInt(String str){
      Integer result;
      str = removeNonDigit(str);

      try {
         result = Integer.parseInt(str);
      } catch (NumberFormatException e) {
         result = 0;
      }

      return result;
   }
}
