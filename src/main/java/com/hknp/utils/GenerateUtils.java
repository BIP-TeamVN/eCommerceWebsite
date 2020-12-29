package com.hknp.utils;

import java.util.Random;

public class GenerateUtils {
   public static String oneTimePassword(int length){
      String numbers = "1234567890";
      Random random = new Random();
      char[] otp = new char[length];
      for (int i = 0; i < length; i++) {
         otp[i] = numbers.charAt(random.nextInt(numbers.length()));
      }
      return otp.toString();
   }
}
