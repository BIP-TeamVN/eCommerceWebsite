package com.hknp.utils;

import java.util.Random;

/**
 * This class provide methods that help you to <b>generate text</b>
 */
public class GenerateUtils {
   private static final String RANDOM_SOUR = "1234567890";
   private static final Random randomInstance = new Random();

   /**
    * Generate one time password
    *
    * @param length length of OTP code
    * @return OTP code
    */
   public static String oneTimePassword(int length) {
      String otpCode = "";
      for (int i = 0; i < length; i++) {
         otpCode += RANDOM_SOUR.charAt(
                 randomInstance.nextInt(RANDOM_SOUR.length())
         );
      }
      return otpCode;
   }
}