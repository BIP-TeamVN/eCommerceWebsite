package com.hknp.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.hknp.model.domain.CartItemDomain;
import com.hknp.utils.CookieUtils;
import com.hknp.utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/api/carts"})
public class CartServlet extends HttpServlet {
   private static final String COOKIE_NAME = "carts";
   private static final Integer COOKIE_AGE = 10 * 3600 * 24;


   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

      String value = CookieUtils.getCookieValue(req, COOKIE_NAME);
      if (value.equals("")) {
         ServletUtils.printWrite(resp, "");
      } else {

         Gson gson = new Gson();
         value = gson.toJson(value);
         ServletUtils.printWrite(resp, value);
      }
   }

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String id = req.getParameter("product-type-id");
      String quantity = req.getParameter("quantity");
      String result = "";

      String value = CookieUtils.getCookieValue(req, COOKIE_NAME);

      ArrayList<CartItemDomain> listCartItemDomain = new ArrayList<>();
      CartItemDomain cartItemDomain = new CartItemDomain();
      Gson gson = new Gson();
      if(value.equals("")) {

         cartItemDomain.setProductTypeId(id);
         cartItemDomain.setQuantity(1);

         listCartItemDomain.add(cartItemDomain);
         value = gson.toJson(listCartItemDomain);


         result += "true";
         CookieUtils.updateCookie(req, resp, COOKIE_NAME, value, COOKIE_AGE);
         ServletUtils.printWrite(resp, result);
      } else {
         final ObjectMapper objectMapper = new ObjectMapper();
         CartItemDomain[] listCartItem = objectMapper.readValue(value, CartItemDomain[].class);

         for (CartItemDomain cartItem : listCartItem) {
            listCartItemDomain.add(cartItem);
         }

         int flag = 0;
         for (int i = 0; i < listCartItem.length; i++) {
            if (listCartItem[i].getProductTypeId().equals(id)) {
               flag = 1;
               Integer currentQuantity = listCartItem[i].getQuantity();
               listCartItem[i].setQuantity(currentQuantity + 1);
               break;
            }
         }
         if (flag == 0) {
            cartItemDomain.setProductTypeId(id);
            cartItemDomain.setQuantity(1);

            listCartItemDomain.add(cartItemDomain);
            value = gson.toJson(listCartItemDomain);
         } else {
            value = gson.toJson(listCartItem);
         }

         result += "true";
         CookieUtils.updateCookie(req, resp, COOKIE_NAME, value, COOKIE_AGE);
         ServletUtils.printWrite(resp, result);
      }
   }

   @Override
   protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String id = req.getParameter("product-type-id");
      String quantity = req.getParameter("quantity");

      String value = CookieUtils.getCookieValue(req, COOKIE_NAME);

      final ObjectMapper objectMapper = new ObjectMapper();
      CartItemDomain[] listCartItem = objectMapper.readValue(value, CartItemDomain[].class);

      for (int i = 0; i < listCartItem.length; i++) {
         if (listCartItem[i].getProductTypeId().equals(id)) {
            Integer num = Integer.parseInt(quantity);
            if (num > 0) {
               listCartItem[i].setQuantity(num);
               break;
            }
         }
      }
      Gson gson = new Gson();
      value = gson.toJson(listCartItem);
      CookieUtils.updateCookie(req, resp, COOKIE_NAME, value, COOKIE_AGE);
   }

   @Override
   protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String id = req.getParameter("product-type-id");

      String value = CookieUtils.getCookieValue(req, COOKIE_NAME);
      ArrayList<CartItemDomain> listCartItemDomain = new ArrayList<>();

      final ObjectMapper objectMapper = new ObjectMapper();
      CartItemDomain[] listCartItem = objectMapper.readValue(value, CartItemDomain[].class);

      for (CartItemDomain cartItem : listCartItem) {
         listCartItemDomain.add(cartItem);
      }
      for (int i = 0; i < listCartItemDomain.size(); i++) {
         if(listCartItem[i].getProductTypeId().equals(id)){
            listCartItemDomain.remove(i);
            break;
         }
      }
      Gson gson = new Gson();
      value = gson.toJson(listCartItemDomain);
      CookieUtils.updateCookie(req, resp, COOKIE_NAME, value, COOKIE_AGE);
   }
}
