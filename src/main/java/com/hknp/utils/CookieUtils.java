package com.hknp.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {
   public static void addCookie(HttpServletResponse resp, String name, String value, int expiryInSecond) {
      Cookie cookie = new Cookie(name, value);
      cookie.setMaxAge(expiryInSecond);
      resp.addCookie(cookie);
   }

   public static Cookie getCookie(HttpServletRequest req, String name) {
      Cookie[] cookies = req.getCookies();
      for (Cookie cookie : cookies) {
         if (cookie.getName().equals(name)) {
            return cookie;
         }
      }
      return null;
   }

   public static void updateCookie(HttpServletRequest req, HttpServletResponse resp, String name, String newValue, Integer newExpiryInSecond) {
      Cookie cookie = getCookie(req, name);
      if (cookie != null) {
         cookie.setValue(newValue);
         if (newExpiryInSecond != null) {
            cookie.setMaxAge(newExpiryInSecond);
         }
         resp.addCookie(cookie);
      }
      else {
         cookie = new Cookie(name, newValue);
         if (newExpiryInSecond != null) {
            cookie.setMaxAge(newExpiryInSecond);
         }
         resp.addCookie(cookie);
      }
   }

   public static void deleteCookie(HttpServletRequest req, HttpServletResponse resp, String name) {
      Cookie cookie = getCookie(req, name);
      if (cookie != null) {
         cookie.setMaxAge(0);
         cookie.setValue(null);
         resp.addCookie(cookie);
      }
   }

   public static String getCookieValue(HttpServletRequest req, String name) {
      Cookie cookie = getCookie(req, name);
      if (cookie != null) {
         return cookie.getValue();
      }
      return "";
   }
}
