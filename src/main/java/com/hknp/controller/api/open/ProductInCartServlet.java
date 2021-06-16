package com.hknp.controller.api.open;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hknp.model.dao.ProductTypeDAO;
import com.hknp.model.domain.CartItemDomain;
import com.hknp.model.domain.ProductInCartItemDomain;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/api/load-list-product-in-carts"})
public class ProductInCartServlet extends HttpServlet {

   private static final String COOKIE_NAME = "carts";

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

      String value = CookieUtils.getCookieValue(req, COOKIE_NAME);
      value = URLDecoder.decode(value, "utf-8");
      if (value.equals("")) {
         ServletUtils.printWrite(resp, "");
      } else {
         String cookieValue = URLDecoder.decode(value, "utf-8");
         ArrayList<CartItemDomain> listCartItemDomain = new ArrayList<>();
         final ObjectMapper objectMapper = new ObjectMapper();
         CartItemDomain[] listCartItem = objectMapper.readValue(cookieValue, CartItemDomain[].class);
         List<String> listJsonStr = new ArrayList<>();
         for (CartItemDomain cartItem : listCartItem) {
            listCartItemDomain.add(cartItem);
         }

         ArrayList<ProductInCartItemDomain> productInCartItemDomainArrayList = new ArrayList<>();


         for (CartItemDomain product : listCartItemDomain) {
            ProductInCartItemDomain productInCartItemDomain = new ProductInCartItemDomain();
            ProductTypeEntity productTypeEntity = ProductTypeDAO.getInstance().getById(StringUtils.toLong(product.getProductTypeId()));

            productInCartItemDomain.setProductId(productTypeEntity.getProductEntity().getProductId().toString());
            productInCartItemDomain.setProductTypeId(product.getProductTypeId());
            productInCartItemDomain.setImage(productTypeEntity.getImage());
            productInCartItemDomain.setName(productTypeEntity.getProductEntity().getProductName());
            productInCartItemDomain.setPrice(productTypeEntity.getProductEntity().getPriceOrder().toString());
            productInCartItemDomain.setNameDetail(productTypeEntity.getProductTypeName());
            productInCartItemDomain.setQuantity(product.getQuantity());

            String shopName = productTypeEntity.getProductEntity().getSellerEntity().getStoreName();
            Long sellerId = productTypeEntity.getProductEntity().getSellerEntity().getUserId();

            productInCartItemDomain.setShopName(shopName);
            productInCartItemDomain.setSellerId(sellerId.toString());

            productInCartItemDomainArrayList.add(productInCartItemDomain);
            listJsonStr.add(productInCartItemDomain.toJson());
         }
         ServletUtils.printWrite(resp, "[" + String.join(", ", listJsonStr) + "]");
      }
   }
}
