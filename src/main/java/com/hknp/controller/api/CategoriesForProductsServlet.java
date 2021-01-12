package com.hknp.controller.api;

import com.hknp.model.dao.ProductCategoryDAO;
import com.hknp.model.entity.ProductCategoryEntity;
import com.hknp.utils.ServletUtils;
import com.hknp.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/api/categories-for-product"})
public class CategoriesForProductsServlet extends HttpServlet {
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      ArrayList<ProductCategoryEntity> listProductCategory = ProductCategoryDAO.getInstance().gets();
      List<String> listJsonStr = new ArrayList<>();

      for (ProductCategoryEntity productCategory : listProductCategory) {
         listJsonStr.add(productCategory.toJson());
      }

      ServletUtils.printWrite(resp, "[" + String.join(", ", listJsonStr) + "]");
   }
}
