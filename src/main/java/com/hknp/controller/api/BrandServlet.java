package com.hknp.controller.api;

import com.hknp.model.dao.BrandDAO;
import com.hknp.model.dao.EmployeeDAO;
import com.hknp.model.entity.BrandEntity;
import com.hknp.model.entity.EmployeeEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/api/brands"})
public class BrandServlet extends HttpServlet {
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

      resp.setContentType("text/html; charset=UTF-8");

      ArrayList<BrandEntity> listBrand = BrandDAO.getInstance().gets();
      List<String> listJsonStr = new ArrayList<>();

      for (BrandEntity brand : listBrand) {
         listJsonStr.add(brand.toJson());
      }

      try (PrintWriter out = resp.getWriter()) {
         out.write("[" + String.join(", ", listJsonStr) + "]");
      }
   }
}
