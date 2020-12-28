package com.hknp.utils;

import javax.servlet.http.Cookie;

public class CookieUtils {
   public static Cookie createCookie(String name, String value, int expiryInSecond){
      Cookie cookie = new Cookie(name, value);
      cookie.setMaxAge(expiryInSecond);
      return cookie;
   }
}
