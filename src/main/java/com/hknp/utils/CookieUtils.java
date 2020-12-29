package com.hknp.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {
   public static void addCookie(HttpServletResponse resp, String name, String value, int expiryInSecond){
      Cookie cookie = new Cookie(name, value);
      cookie.setMaxAge(expiryInSecond);
      resp.addCookie(cookie);
   }

   public static String getCookie(HttpServletRequest req, String name){
      Cookie[] cookies = req.getCookies();
      for (Cookie cookie:cookies) {
         if(cookie.getName().equals(name)) {
            return cookie.getValue();
         }
      }
      return "";
   }
}
