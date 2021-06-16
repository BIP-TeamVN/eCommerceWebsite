package com.hknp.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * This class provide methods that help you to <b>hash text</b>
 */
public class HashUtils {
   /**
    * Hash plain text to MD5 code
    *
    * @param plainText String for hash
    * @return MD5 string which has 32 characters
    * @throws NoSuchAlgorithmException This exception is thrown when a particular
    *                                  cryptographic algorithm is requested
    *                                  but is not available in the environment.
    * @see java.security.NoSuchAlgorithmException
    */
   public static String getMd5(String plainText) {
      try {
         // Static getInstance method is called with hashing MD5
         MessageDigest md = MessageDigest.getInstance("MD5");

         // digest() method is called to calculate message digest
         //  of an input digest() return array of byte
         byte[] messageDigest = md.digest(plainText.getBytes());

         // Convert byte array into signum representation
         BigInteger no = new BigInteger(1, messageDigest);

         // Convert message digest into hex value
         String hashText = no.toString(16);
         while (hashText.length() < 32) {
            hashText = "0" + hashText;
         }

         return hashText;
      } catch (NoSuchAlgorithmException e) {
         throw new RuntimeException(e);
      }
   }
}
