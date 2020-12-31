package com.hknp.controller.api;

import com.hknp.model.dao.BrandDAO;
import com.hknp.model.entity.BrandEntity;
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
import java.util.Map;

@WebServlet(urlPatterns = {"/api/brands"})
public class BrandServlet extends HttpServlet {
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

      resp.setContentType("text/html; charset=UTF-8");

      String pagePara = req.getParameter("page");
      Integer page = StringUtils.toInt(pagePara);
      if (page <= 0) {
         page = 1;
      }

      ArrayList<BrandEntity> listBrand = BrandDAO.getInstance().gets((page - 1) * 10, 10);
      List<String> listJsonStr = new ArrayList<>();

      for (BrandEntity brand : listBrand) {
         listJsonStr.add(brand.toJson());
      }

      try (PrintWriter out = resp.getWriter()) {
         out.write("[" + String.join(", ", listJsonStr) + "]");
      }
   }

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

      resp.setContentType("text/html; charset=UTF-8");

      String result = "";

      try {
         String brandName = req.getParameter("brandName");
         String brandOrigin = req.getParameter("brandOrigin");

         String imageBase64 = req.getParameter("imageBase64");


         BrandEntity newBrand = new BrandEntity();
         newBrand.setBrandName(brandName);
         newBrand.setBrandOrigin(brandOrigin);

         if (imageBase64 != null && !imageBase64.isEmpty()) {
            newBrand.setImage(imageBase64);
         }
         Long newBrandId = BrandDAO.getInstance().insert(newBrand);

         if (newBrandId != 0) {
            result += "true\n" + newBrandId.toString();
         } else {
            result += "false\nError while insert brand";
         }
      } catch (Exception e) {
         result += "false\n" + e.getMessage();
      }

      try (PrintWriter out = resp.getWriter()) {
         out.write(result);
      }
   }

   @Override
   protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      resp.setContentType("text/html; charset=UTF-8");
      String result = "";
      Map<String,Object> parameterMap = ServletUtils.getParametersMap(req);

      try {
         String id = (String) parameterMap.get("id");
         String brandName = (String) parameterMap.get("name");
         String brandOrigin = (String) parameterMap.get("brand-origin");

         String imageBase64 = (String) parameterMap.get("image");


         BrandEntity updateBrand = BrandDAO.getInstance().getById(StringUtils.toLong(id));
         updateBrand.setBrandName(brandName);
         updateBrand.setBrandOrigin(brandOrigin);

         if(imageBase64 != null && !imageBase64.isEmpty()) {
            updateBrand.setImage(imageBase64);
         }
         Boolean updateResult = BrandDAO.getInstance().update(updateBrand);

         if (updateResult != false) {
            result += "true\n" + updateBrand.getBrandId().toString();
         } else {
            result += "false\nError while insert brand";
         }
      } catch (Exception e) {
         result += "false\n" + e.getMessage();
      }

      try (PrintWriter out = resp.getWriter()) {
         out.write(result);
      }
   }
}
