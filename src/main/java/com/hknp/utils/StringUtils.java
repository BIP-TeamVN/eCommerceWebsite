package com.hknp.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

/**
 * This class provide methods that help you to work with <b>String</b>
 */
public class StringUtils {
   /**
    * Remove non-digit character from string
    *
    * @param str string to remove non-digit character
    * @return <code>digit string</code> (without non-digit char)<br>
    * <code>0</code> if null or empty
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
    * @param str string to convert
    * @return <code>BigDecimal value</code> if converted successfully<br>
    * <code>0</code> otherwise
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
    * @param str string to convert
    * @return <code>Long value</code> if converted successfully<br>
    * <code>0</code> otherwise
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
    * @param str string to convert
    * @return <code>Integer value</code> if converted successfully<br>
    * <code>0</code> otherwise
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
    * @param str   string to split
    * @param regex the delimiting regular expression
    * @return <code>{@link List}&#60;{@link String}&#62;</code> if split successfully<br>
    * <code>Empty list</code> otherwise
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

   public static String replaceAll(String str, String strToReplace, String replacement) {
      while (str.contains(strToReplace)) {
         str = str.replace(strToReplace, replacement);
      }
      return str;
   }

   /**
    * @param value value to strip
    * @return
    */
   public static String stripXSS(String value) {
      if (value != null) {
         // Avoid null characters
         value = value.replaceAll("", "");

         // Avoid anything between script tags
         Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>",
                 Pattern.CASE_INSENSITIVE);
         value = scriptPattern.matcher(value).replaceAll("");

         // Avoid anything in a src='...' type of expression
         scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'",
                 Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
         value = scriptPattern.matcher(value).replaceAll("");

         scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"",
                 Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
         value = scriptPattern.matcher(value).replaceAll("");

         // Remove any lonesome </script> tag
         scriptPattern = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);
         value = scriptPattern.matcher(value).replaceAll("");

         // Remove any lonesome <script ...> tag
         scriptPattern = Pattern.compile("<script(.*?)>",
                 Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
         value = scriptPattern.matcher(value).replaceAll("");

         // Avoid eval(...) expressions
         scriptPattern = Pattern.compile("eval\\((.*?)\\)",
                 Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
         value = scriptPattern.matcher(value).replaceAll("");

         // Avoid expression(...) expressions
         scriptPattern = Pattern.compile("expression\\((.*?)\\)",
                 Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
         value = scriptPattern.matcher(value).replaceAll("");

         // Avoid javascript:... expressions
         scriptPattern = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);
         value = scriptPattern.matcher(value).replaceAll("");

         // Avoid vbscript:... expressions
         scriptPattern = Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE);
         value = scriptPattern.matcher(value).replaceAll("");

         // Avoid onload= expressions
         scriptPattern = Pattern.compile("onload(.*?)=",
                 Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
         value = scriptPattern.matcher(value).replaceAll("");
      }
      return value;
   }
}
