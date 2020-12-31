package com.hknp.controller.api;

import com.hknp.model.dao.BrandDAO;
import com.hknp.model.dao.CommuneDAO;
import com.hknp.model.dao.DistrictDAO;
import com.hknp.model.dao.ProvinceDAO;
import com.hknp.model.entity.BrandEntity;
import com.hknp.model.entity.CommuneEntity;
import com.hknp.model.entity.DistrictEntity;
import com.hknp.model.entity.ProvinceEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/api/seller-units"})
public class SellerUnitServlet extends HttpServlet {
   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      response.setContentType("text/html; charset=UTF-8");

      try (PrintWriter out = response.getWriter()) {
         String type = request.getParameter("type");
         List<String> listJsonStr = new ArrayList<>();

         for (BrandEntity brand: BrandDAO.getInstance().gets()) {
            listJsonStr.add(brand.toJson());
         }

         out.write("[" + String.join(", ", listJsonStr) + "]");
      }
   }
}
