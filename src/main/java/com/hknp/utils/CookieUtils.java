package com.hknp.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class provide methods that help you to work with <b>Cookie</b>
 */
public class CookieUtils {
   /**
    * Adds the specified cookie to the response.
    * This method can be called multiple times to set more than one cookie.
    *
    * @param resp           {@link HttpServletResponse}
    *                       HTTP-specific functionality in sending a response.
    * @param name           the name of the cookie
    * @param value          the value of the cookie
    * @param expiryInSecond an integer specifying the maximum age of the cookie in seconds;
    *                       if negative, means the cookie is not stored;
    *                       if zero, deletes the cookie
    * @see Cookie#Cookie(String, String)
    * @see Cookie#setMaxAge(int)
    * @see HttpServletResponse#addCookie(Cookie)
    */
   public static void addCookie(HttpServletResponse resp, String name, String value, int expiryInSecond) {
      Cookie cookie = new Cookie(name, value);
      cookie.setMaxAge(expiryInSecond);
      resp.addCookie(cookie);
   }

   /**
    * Get cookie object from HttpServletRequest
    *
    * @param req  {@link HttpServletRequest}
    *             request information for HTTP servlets.
    * @param name the name of the cookie
    * @return <code>Cookie object</code> if request has cookie equal name<br>
    * <code>null</code> otherwise
    */
   public static Cookie getCookie(HttpServletRequest req, String name) {
      Cookie[] cookies = req.getCookies();
      for (Cookie cookie : cookies) {
         if (cookie.getName().equals(name)) {
            return cookie;
         }
      }
      return null;
   }

   /**
    * Update cookie value and age
    * If cookie not exits, add new cookie to HttpServletResponse
    *
    * @param req               {@link HttpServletRequest}
    *                          request information for HTTP servlets.
    * @param resp              {@link HttpServletResponse}
    *                          HTTP-specific functionality in sending a response.
    * @param name              the name of the cookie
    * @param newValue          the new value to update the cookie
    * @param newExpiryInSecond new an integer specifying the maximum age of the cookie in seconds;
    *                          if negative, means the cookie is not stored;
    *                          if zero, deletes the cookie
    */
   public static void updateCookie(HttpServletRequest req, HttpServletResponse resp, String name, String newValue, Integer newExpiryInSecond) {
      Cookie cookie = getCookie(req, name);
      if (cookie != null) {
         cookie.setValue(newValue);
         if (newExpiryInSecond != null) {
            cookie.setMaxAge(newExpiryInSecond);
         }
         resp.addCookie(cookie);
      } else {
         cookie = new Cookie(name, newValue);
         if (newExpiryInSecond != null) {
            cookie.setMaxAge(newExpiryInSecond);
         }
         resp.addCookie(cookie);
      }
   }

   /**
    * Delete cookie form HttpServletRequest
    *
    * @param req  {@link HttpServletRequest}
    *             request information for HTTP servlets.
    * @param resp {@link HttpServletResponse}
    *             HTTP-specific functionality in sending a response.
    * @param name the name of the cookie
    */
   public static void deleteCookie(HttpServletRequest req, HttpServletResponse resp, String name) {
      Cookie cookie = getCookie(req, name);
      if (cookie != null) {
         cookie.setMaxAge(0);
         cookie.setValue(null);
         resp.addCookie(cookie);
      }
   }

   /**
    * Get cookie value from HttpServletRequest
    *
    * @param req  {@link HttpServletRequest}
    *             request information for HTTP servlets.
    * @param name the name of the cookie
    * @return <code>Cookie value</code> if get successfully<br>
    * <code>empty String</code> otherwise
    */
   public static String getCookieValue(HttpServletRequest req, String name) {
      Cookie cookie = getCookie(req, name);
      if (cookie != null) {
         return cookie.getValue();
      }
      return "";
   }
}
