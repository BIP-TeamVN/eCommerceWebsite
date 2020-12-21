package com.hknp.utils;

import org.apache.commons.codec.binary.Base64;

public class Base64Utils {
   public static String encodeFromString(String planText) {
      byte[] bytesEncoded = Base64.encodeBase64(planText.getBytes());
      return new String(bytesEncoded);
   }

   public static String decodeToString(String base64Text) {
      byte[] valueDecoded = Base64.decodeBase64(base64Text);
      return new String(valueDecoded);
   }
}
