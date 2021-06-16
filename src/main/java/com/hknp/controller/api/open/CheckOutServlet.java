package com.hknp.controller.api.open;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hknp.model.dao.ProductTypeDAO;
import com.hknp.model.dao.UserDAO;
import com.hknp.model.domain.CartItemDomain;
import com.hknp.model.domain.ProductInCartItemDomain;
import com.hknp.model.entity.Cons;
import com.hknp.model.entity.ProductTypeEntity;
import com.hknp.utils.CookieUtils;
import com.hknp.utils.ServletUtils;
import com.hknp.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/api/check-out"})
public class CheckOutServlet extends HttpServlet {

   private static final String COOKIE_NAME = "carts";

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

      String value = CookieUtils.getCookieValue(req, COOKIE_NAME);
      if (value.equals("")) {
         ServletUtils.printWrite(resp, "");
      } else {
         String cookieValue = URLDecoder.decode(value, "utf-8");

         ArrayList<CartItemDomain> listCartItemDomain = new ArrayList<>();

         final ObjectMapper objectMapper = new ObjectMapper();
         CartItemDomain[] listCartItem = objectMapper.readValue(cookieValue, CartItemDomain[].class);

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
         }

         Map<String, List<ProductInCartItemDomain>> result = productInCartItemDomainArrayList.stream().
                 collect(Collectors.groupingBy(ProductInCartItemDomain::getSellerId));
         List<List<ProductInCartItemDomain>> productList = new ArrayList<>();

         result.forEach((k, v) -> {

            List<ProductInCartItemDomain> product = new ArrayList<>();

            product = result.get(k);
            productList.add(product);
         });

         List<String> listJsonStrFull = new ArrayList<>();

         for (int i = 0; i < productList.size(); i++) {

            List<ProductInCartItemDomain> productDetail = productList.get(i);

            List<String> listJsonStr = new ArrayList<>();
            for (int j = 0; j < productDetail.size(); j++) {
               listJsonStr.add(productDetail.get(j).toJsonOne());
            }

            String temp = "[" + String.join(", ", listJsonStr) + "]";
            listJsonStrFull.add(productList.get(i).get(0).toJson(temp));
         }

         ServletUtils.printWrite(resp, "[" + String.join(", ", listJsonStrFull) + "]");
      }
   }

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      HttpSession session = req.getSession(true);
      Long id = (Long) session.getAttribute("id");
      String result = "";
      if (id != null) {
         String userType = UserDAO.getInstance().getUserType(id);
         if (userType.equals(Cons.User.USER_TYPE_CUSTOMER)) {
            session.setAttribute("id", id);
            result += "true\n";
         }
      } else {
         result += "false\n";
      }
      ServletUtils.printWrite(resp, result);
   }
}
