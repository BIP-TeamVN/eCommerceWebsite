package com.hknp.controller.api;

import com.hknp.model.dao.ProductCategoryDAO;
import com.hknp.model.dao.ProductDAO;
import com.hknp.model.entity.ProductCategoryEntity;
import com.hknp.model.entity.ProductEntity;
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

@WebServlet(urlPatterns = {"/api/get-id-categories"})
public class ShGetProductCategories extends HttpServlet {
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String productIdPara = req.getParameter("id");
      Long productId = StringUtils.toLong(productIdPara);

      HttpSession session = req.getSession();
      Long sellerId = (Long) session.getAttribute("id");
      //Long sellerId = 10002L;
      String result = "";
      ArrayList<ProductCategoryEntity> productCategoryEntities = ProductCategoryDAO.getInstance().gets();

      try {
         ProductEntity productEntity = ProductDAO.getInstance().getByIdAndSeller(productId, sellerId);
         ArrayList<ProductCategoryEntity> productCategorySelect = new ArrayList<>(productEntity.getProductCategoryEntities());

         Boolean temp = false;
         for (ProductCategoryEntity c : productCategoryEntities) {
            temp = false;
            for (ProductCategoryEntity s : productCategorySelect) {
               if (c.getProductCategoryId() == s.getProductCategoryId()) {
                  result += "<option selected value=\"" + c.getProductCategoryId() + "\">" + c.getProductCategoryName() + "</option>";
                  temp = true;
                  break;
               }
            }
            if (temp == false) {
               result += "<option value=\"" + c.getProductCategoryId() + "\">" + c.getProductCategoryName() + "</option>";
            }
         }
      } catch (Exception e) {
         result = "";
         for (ProductCategoryEntity c : productCategoryEntities) {
            result += "<option value=\"" + c.getProductCategoryId() + "\">" + c.getProductCategoryName() + "</option>";
         }
      }

      ServletUtils.printWrite(resp, result);
   }
}
