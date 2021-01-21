package com.hknp.controller.api;

import com.hknp.model.dao.BrandDAO;
import com.hknp.model.entity.BrandEntity;
import com.hknp.utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/api/seller-units"})
public class SellerUnitServlet extends HttpServlet {
   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
      String type = request.getParameter("type");
      List<String> listJsonStr = new ArrayList<>();

      for (BrandEntity brand : BrandDAO.getInstance().gets()) {
         listJsonStr.add(brand.toJson());
      }

      ServletUtils.printWrite(response, "[" + String.join(", ", listJsonStr) + "]");
   }
}
