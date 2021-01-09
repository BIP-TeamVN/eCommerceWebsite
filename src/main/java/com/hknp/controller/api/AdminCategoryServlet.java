package com.hknp.controller.api;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/api/admin-category"})
public class AdminCategoryServlet extends HttpServlet {
   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      response.setContentType("text/html; charset=UTF-8");

      try (PrintWriter out = response.getWriter()) {
         List<String> listJsonStr = new ArrayList<>();

         for(SellerCategoryEntity sellerCategory : SellerCategoryDAO.getInstance().gets()){
            listJsonStr.add(sellerCategory.toJson());
         }

         out.write("[" + String.join(", ", listJsonStr) + "]");
      }
   }
}
