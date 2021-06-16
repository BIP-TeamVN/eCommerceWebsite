package com.hknp.controller.api.open;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.hknp.model.dao.*;
import com.hknp.model.domain.CartItemDomain;
import com.hknp.model.domain.ProductInCartItemDomain;
import com.hknp.model.entity.BillDetailEntity;
import com.hknp.model.entity.BillEntity;
import com.hknp.model.entity.Cons;
import com.hknp.model.entity.ProductTypeEntity;
import com.hknp.utils.CookieUtils;
import com.hknp.utils.DateTimeUtils;
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
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/api/home-order"})
public class OrderServlet extends HttpServlet {
   private final String COOKIE_NAME = "carts";
   private final Integer COOKIE_AGE = 10 * 3600 * 24;

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String value = CookieUtils.getCookieValue(req, COOKIE_NAME);

      ArrayList<ProductInCartItemDomain> productInCartItemDomainArrayList = new ArrayList<>();
      List<List<ProductInCartItemDomain>> productList = new ArrayList<>();
      ArrayList<CartItemDomain> listCartItemDomain = new ArrayList<>();
      if (value.equals("")) {
         ServletUtils.printWrite(resp, "");
      } else {
         String cookieValue = URLDecoder.decode(value, "utf-8");

         final ObjectMapper objectMapper = new ObjectMapper();
         CartItemDomain[] listCartItem = objectMapper.readValue(cookieValue, CartItemDomain[].class);

         for (CartItemDomain cartItem : listCartItem) {
            listCartItemDomain.add(cartItem);
         }

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

         result.forEach((k, v) -> {

            List<ProductInCartItemDomain> product = new ArrayList<>();

            product = result.get(k);
            productList.add(product);
         });
      }

      String listSellerIdPara = req.getParameter("list-seller-id");
      String addressId = req.getParameter("address-id");

      List<String> listSellerId = StringUtils.splitToList(listSellerIdPara, "@ab");

      HttpSession session = req.getSession();
      Long customerId = (Long) session.getAttribute("id");

      String result = "";
      if (customerId != null) {
         for (String s : listSellerId) {
            BillEntity billEntity = new BillEntity();
            billEntity.setCustomerEntity(CustomerDAO.getInstance().getById(customerId));
            billEntity.setAddressEntity(AddressDAO.getInstance().getById(StringUtils.toLong(addressId)));
            billEntity.setStatus(Cons.Bill.BILL_STATUS_CREATE);
            billEntity.setBillCreateDate(DateTimeUtils.currentDate());
            billEntity.setBillDoneDate(null);
            billEntity.setDeliveryEntity(null);
            billEntity.setDiscountEntity(DiscountDAO.getInstance().getById(1L));
            Long resultBill = BillDAO.getInstance().insert(billEntity);

            if (resultBill != 0) {
               for (int i = 0; i < productList.size(); i++) {
                  Boolean temp = false;
                  if (StringUtils.toLong(s) - StringUtils.toLong(productList.get(i).get(0).getSellerId()) == 0) {
                     temp = true;
                  }
                  if (temp == true) {
                     for (int j = 0; j < productList.get(i).size(); j++) {
                        BillDetailEntity billDetailEntity = new BillDetailEntity();
                        billDetailEntity.setBillEntity(BillDAO.getInstance().getById(resultBill));
                        billDetailEntity.setProductTypeEntity(ProductTypeDAO.getInstance().getById(StringUtils.toLong(productList.get(i).get(j).getProductTypeId())));
                        billDetailEntity.setQuantity(productList.get(i).get(j).getQuantity());
                        BillDetailDAO.getInstance().insert(billDetailEntity);
                        result += "true";
                        for (int k = 0; k < listCartItemDomain.size(); k++) {
                           if (listCartItemDomain.get(k).getProductTypeId().equals(productList.get(i).get(j).getProductTypeId())) {
                              listCartItemDomain.remove(k);
                              break;
                           }
                        }
                     }
                  }
               }

            } else {
               result += "false";
            }
         }

         Gson gson = new Gson();
         String valueNew = gson.toJson(listCartItemDomain);
         String encodeCookie = URLEncoder.encode(valueNew, "utf-8");
         CookieUtils.updateCookie(req, resp, COOKIE_NAME, encodeCookie, COOKIE_AGE);

      } else {
         result += "false\nĐăng nhập để tiếp tục";
      }

      ServletUtils.printWrite(resp, result);
   }
}
