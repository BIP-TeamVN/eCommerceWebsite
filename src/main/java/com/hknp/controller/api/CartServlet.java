package com.hknp.controller.api;

import com.hknp.utils.CookieUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/api/carts"})
public class CartServlet extends HttpServlet {
   private static final String COOKIE_NAME = "carts";
   private static final Integer COOKIE_AGE = 10 * 3600 * 24;


   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String value = CookieUtils.getCookieValue(req, COOKIE_NAME);
   }

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String id = req.getParameter("product-type-id");
      String quantity = req.getParameter("quantity");

      String value = CookieUtils.getCookieValue(req, COOKIE_NAME);




      CookieUtils.updateCookie(req, resp, COOKIE_NAME, value, COOKIE_AGE);
   }
}
