package com.hknp.controller.api.open;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.hknp.model.dao.ProductTypeDAO;
import com.hknp.model.domain.CartItemDomain;
import com.hknp.model.entity.ProductTypeEntity;
import com.hknp.utils.CookieUtils;
import com.hknp.utils.ServletUtils;
import com.hknp.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Map;

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
         String cookieValue = URLDecoder.decode(value, "utf-8");
         ServletUtils.printWrite(resp, cookieValue);
      }
   }

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String result = "";
      try {
         String idProduct = req.getParameter("product-id");
         Long productId = Long.parseLong(idProduct);

         ArrayList<ProductTypeEntity> listProductType = ProductTypeDAO.getInstance().getByProductId(null, null, productId);

         Integer maxQuantity = 0;
         ProductTypeEntity productTypeEntity = new ProductTypeEntity();
         String id = "";
         for (int i = 0; i < listProductType.size(); i++) {
            if (listProductType.get(i).getQuantity() > maxQuantity) {
               maxQuantity = listProductType.get(i).getQuantity();
               productTypeEntity = listProductType.get(i);
            }
         }
         id = productTypeEntity.getProductTypeId().toString();

         String value = CookieUtils.getCookieValue(req, COOKIE_NAME);

         value = URLDecoder.decode(value, "utf-8");

         ArrayList<CartItemDomain> listCartItemDomain = new ArrayList<>();
         CartItemDomain cartItemDomain = new CartItemDomain();
         Gson gson = new Gson();
         if (value.equals("")) {

            cartItemDomain.setProductTypeId(id);
            cartItemDomain.setQuantity(1);

            listCartItemDomain.add(cartItemDomain);
            String valueJson = gson.toJson(listCartItemDomain);
            String encodeCookie = URLEncoder.encode(valueJson, "utf-8");

            CookieUtils.addCookie(resp, COOKIE_NAME, encodeCookie, COOKIE_AGE);
            result += "true\n" + id;
         } else {
            final ObjectMapper objectMapper = new ObjectMapper();
            CartItemDomain[] listCartItem = objectMapper.readValue(value, CartItemDomain[].class);

            for (CartItemDomain cartItem : listCartItem) {
               listCartItemDomain.add(cartItem);
            }

            int flag = 0;
            for (int i = 0; i < listCartItem.length; i++) {
               if (listCartItem[i].getProductTypeId().equals(id)) {
                  result += "true\n" + listCartItem[i].getProductTypeId();
                  flag = 1;
                  Integer currentQuantity = listCartItem[i].getQuantity();
                  listCartItem[i].setQuantity(currentQuantity + 1);
                  break;
               }
            }
            if (flag == 0) {
               result += "true\n" + id;
               cartItemDomain.setProductTypeId(id);
               cartItemDomain.setQuantity(1);

               listCartItemDomain.add(cartItemDomain);
               value = gson.toJson(listCartItemDomain);
            } else {
               value = gson.toJson(listCartItem);
            }
            String encodeCookie = URLEncoder.encode(value, "utf-8");
            CookieUtils.updateCookie(req, resp, COOKIE_NAME, encodeCookie, COOKIE_AGE);
         }

      } catch (Exception e) {
         result = "false";
      }
      ServletUtils.printWrite(resp, result);
   }

   @Override
   protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String result = "";
      Map<String, Object> parameterMap = ServletUtils.getParametersMap(req);
      try {
         String id = (String) parameterMap.get("product-type-id");
         String quantity = (String) parameterMap.get("quantity");
         String boolAdd = (String) parameterMap.get("bool-add");

         String value = CookieUtils.getCookieValue(req, COOKIE_NAME);
         value = URLDecoder.decode(value, "utf-8");

         ArrayList<CartItemDomain> listCartItemDomain = new ArrayList<>();
         CartItemDomain cartItemDomain = new CartItemDomain();
         Gson gson = new Gson();

         if (boolAdd.equals("them")) {
            final ObjectMapper objectMapper = new ObjectMapper();
            CartItemDomain[] listCartItem = objectMapper.readValue(value, CartItemDomain[].class);

            for (CartItemDomain cartItem : listCartItem) {
               listCartItemDomain.add(cartItem);
            }
            int flag = 0;
            for (int i = 0; i < listCartItem.length; i++) {
               if (listCartItem[i].getProductTypeId().equals(id)) {
                  result += "true\n" + listCartItem[i].getProductTypeId();
                  listCartItem[i].setQuantity(StringUtils.toInt(quantity));

                  if (StringUtils.toInt(quantity) == 0) {
                     flag = 1;
                     listCartItemDomain.remove(i);
                  }
                  break;
               }
            }
            if (flag == 0) {
               value = gson.toJson(listCartItem);
            } else {
               value = gson.toJson(listCartItemDomain);
            }
            String encodeCookie = URLEncoder.encode(value, "utf-8");
            CookieUtils.updateCookie(req, resp, COOKIE_NAME, encodeCookie, COOKIE_AGE);
         } else {
            if (value.equals("")) {

               cartItemDomain.setProductTypeId(id);
               cartItemDomain.setQuantity(StringUtils.toInt(quantity));

               listCartItemDomain.add(cartItemDomain);
               String valueJson = gson.toJson(listCartItemDomain);
               String encodeCookie = URLEncoder.encode(valueJson, "utf-8");

               CookieUtils.addCookie(resp, COOKIE_NAME, encodeCookie, COOKIE_AGE);
               result += "true\n" + id;
            } else {
               final ObjectMapper objectMapper = new ObjectMapper();
               CartItemDomain[] listCartItem = objectMapper.readValue(value, CartItemDomain[].class);

               for (CartItemDomain cartItem : listCartItem) {
                  listCartItemDomain.add(cartItem);
               }

               int flag = 0;
               for (int i = 0; i < listCartItem.length; i++) {
                  if (listCartItem[i].getProductTypeId().equals(id)) {
                     result += "true\n" + listCartItem[i].getProductTypeId();
                     flag = 1;
                     Integer currentQuantity = listCartItem[i].getQuantity();
                     listCartItem[i].setQuantity(currentQuantity + StringUtils.toInt(quantity));
                     break;
                  }
               }
               if (flag == 0) {
                  result += "true\n" + id;
                  cartItemDomain.setProductTypeId(id);
                  cartItemDomain.setQuantity(StringUtils.toInt(quantity));

                  listCartItemDomain.add(cartItemDomain);
                  value = gson.toJson(listCartItemDomain);
               } else {
                  value = gson.toJson(listCartItem);
               }
               String encodeCookie = URLEncoder.encode(value, "utf-8");
               CookieUtils.updateCookie(req, resp, COOKIE_NAME, encodeCookie, COOKIE_AGE);
            }
         }
      } catch (Exception e) {
         result += "false\n" + e.getMessage();
      }
      ServletUtils.printWrite(resp, result);
   }

   @Override
   protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String result = "";
      try {
         String id = req.getParameter("product-type-id");

         String value = CookieUtils.getCookieValue(req, COOKIE_NAME);
         value = URLDecoder.decode(value, "utf-8");
         ArrayList<CartItemDomain> listCartItemDomain = new ArrayList<>();

         final ObjectMapper objectMapper = new ObjectMapper();
         CartItemDomain[] listCartItem = objectMapper.readValue(value, CartItemDomain[].class);

         for (CartItemDomain cartItem : listCartItem) {
            listCartItemDomain.add(cartItem);
         }
         for (int i = 0; i < listCartItemDomain.size(); i++) {
            if (listCartItem[i].getProductTypeId().equals(id)) {
               result += "true\n" + listCartItemDomain.get(i).getProductTypeId();
               listCartItemDomain.remove(i);
               break;
            }
         }
         Gson gson = new Gson();
         value = gson.toJson(listCartItemDomain);
         String encodeCookie = URLEncoder.encode(value, "utf-8");
         CookieUtils.updateCookie(req, resp, COOKIE_NAME, encodeCookie, COOKIE_AGE);

      } catch (Exception e) {
         result += "false\n" + e.getMessage();
      }
      ServletUtils.printWrite(resp, result);
   }
}
