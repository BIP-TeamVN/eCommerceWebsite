package com.hknp.controller.api;

import com.hknp.model.dao.ProductDAO;
import com.hknp.model.entity.ProductEntity;
import com.hknp.model.entity.ProductTypeEntity;
import com.hknp.utils.ServletUtils;
import com.hknp.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/api/get-product-types"})
public class ShGetProductTypes extends HttpServlet {
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String productIdPara = req.getParameter("id");
      Long productId = StringUtils.toLong(productIdPara);

      HttpSession session = req.getSession();
      Long sellerId = (Long) session.getAttribute("id");
      //Long sellerId = 10002L;
      List<String> listJsonStr = new ArrayList<>();

      try {
         ProductEntity productEntity = ProductDAO.getInstance().getByIdAndSeller(productId, sellerId);
         ArrayList<ProductTypeEntity> productTypeEntities = new ArrayList<>(productEntity.getProductTypeEntities());
         for (ProductTypeEntity t : productTypeEntities) {
            String s = "{" +
                    "\"name\":\"" + t.getProductTypeName() + "\"," +
                    "\"quantity\":\"" + t.getQuantity() + "\"," +
                    "\"image\":\"" + t.getImage() + "\"" +
                    "}";
            listJsonStr.add(s);
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
      if (listJsonStr.size() == 0) {
         String s = "{" +
                 "\"name\":\"" + "" + "\"," +
                 "\"quantity\":\"" + "" + "\"," +
                 "\"image\":\"" + "" + "\"" +
                 "}";
         listJsonStr.add(s);
      }

      ServletUtils.printWrite(resp, "[" + String.join(", ", listJsonStr) + "]");
   }
}
