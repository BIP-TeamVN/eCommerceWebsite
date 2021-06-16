package com.hknp.controller.api;

import com.hknp.model.dao.BrandDAO;
import com.hknp.model.dao.UserDAO;
import com.hknp.model.entity.BrandEntity;
import com.hknp.model.entity.Cons;
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
import java.util.Map;

@WebServlet(urlPatterns = {"/api/brands"})
public class BrandServlet extends HttpServlet {

   public boolean isAuthentication(HttpServletRequest req) {
      HttpSession session = req.getSession(false);
      Long id = (Long) session.getAttribute("id");

      if (id != null) {
         String userType = UserDAO.getInstance().getUserType(id);
         return userType.equals(Cons.User.USER_TYPE_SELLER) || userType.equals(Cons.User.USER_TYPE_CUSTOMER);
      }
      return false;
   }

   public boolean isAuthenticationAdmin(HttpServletRequest req) {
      HttpSession session = req.getSession(false);
      Long id = (Long) session.getAttribute("id");

      if (id != null) {
         String userType = UserDAO.getInstance().getUserType(id);
         return userType.equals(Cons.User.USER_TYPE_ADMIN) || userType.equals(Cons.User.USER_TYPE_EMPLOYEE);
      }
      return false;
   }

   @Override
   protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      if (req.getMethod().equals("GET")) {
         doGet(req, resp);
      } else {
         if (isAuthenticationAdmin(req)) {
            switch (req.getMethod()) {
               case "POST":
                  doPost(req, resp);
                  break;
               case "PUT":
               case "PATCH":
                  doPut(req, resp);
                  break;
               case "DELETE":
                  doDelete(req, resp);
                  break;
               default:
                  ServletUtils.printWrite(resp, "Method not found");
            }
         } else {
            ServletUtils.printWrite(resp, "Access denied");
         }
      }
   }

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String pagePara = req.getParameter("page");
      Integer page = StringUtils.toInt(pagePara);

      String keyword = req.getParameter("keyword").trim();
      String columnName = req.getParameter("columnName");
      String typeSort = req.getParameter("typeSort");

      if (keyword == null) {
         keyword = "";
      }

      if (page <= 0) {
         page = 1;
      }

      List<String> listJsonStr = new ArrayList<>();
      List<BrandEntity> listBrand = BrandDAO.getInstance().gets((page - 1) * 10, 10, keyword, columnName, typeSort);

      for (BrandEntity br : listBrand) {
         listJsonStr.add(br.toJson());
      }

      ServletUtils.printWrite(resp, "[" + String.join(", ", listJsonStr) + "]");
   }

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
            result += "true\n" + newBrandId;
         } else {
            result += "false\nError while insert brand";
         }
      } catch (Exception e) {
         result += "false\n" + e.getMessage();
      }

      ServletUtils.printWrite(resp, result);
   }

   @Override
   protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String result = "";
      Map<String, Object> parameterMap = ServletUtils.getParametersMap(req);

      try {
         String id = (String) parameterMap.get("id");
         String brandName = (String) parameterMap.get("name");
         String brandOrigin = (String) parameterMap.get("brand-origin");

         String imageBase64 = (String) parameterMap.get("image");

         BrandEntity updateBrand = BrandDAO.getInstance().getById(StringUtils.toLong(id));
         updateBrand.setBrandName(brandName);
         updateBrand.setBrandOrigin(brandOrigin);

         if (imageBase64 != null && !imageBase64.isEmpty()) {
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

      ServletUtils.printWrite(resp, result);
   }
}
