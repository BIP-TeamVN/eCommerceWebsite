package com.hknp.utils;

import org.apache.commons.codec.binary.Base64;

/**
 * This class provide methods that help you to work with <b><Base64 Encryption/b>
 */
public class Base64Utils {
   /**
    * Encode plain text to base64
    *
    * @param planText plan text to encode
    * @return base64 text
    * @see org.apache.commons.codec.binary.Base64#encodeBase64(byte[])
    */
   public static String encodeFromString(String planText) {
      byte[] bytesEncoded = Base64.encodeBase64(planText.getBytes());
      return new String(bytesEncoded);
   }

   /**
    * Decode Base64 text to plain text
    *
    * @param base64Text Base64 text to decode
    * @return plan text
    * @see org.apache.commons.codec.binary.Base64#decodeBase64(byte[])
    */
   public static String decodeToString(String base64Text) {
      byte[] valueDecoded = Base64.decodeBase64(base64Text);
      return new String(valueDecoded);
   }
}
