package com.hknp.controller.api;

import com.hknp.model.dao.ProductCategoryDAO;
import com.hknp.model.dao.UserDAO;
import com.hknp.model.entity.Cons;
import com.hknp.model.entity.ProductCategoryEntity;
import com.hknp.utils.ServletUtils;
import com.hknp.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = {"/api/product-categories"})
public class ProductCategoryServlet extends HttpServlet {

   public boolean isAuthentication(HttpServletRequest req) {
      HttpSession session = req.getSession(false);
      Long id = (Long) session.getAttribute("id");

      if (id != null) {
         String userType = UserDAO.getInstance().getUserType(id);
         return userType.equals(Cons.User.USER_TYPE_CUSTOMER) || userType.equals(Cons.User.USER_TYPE_SELLER) || userType.equals(Cons.User.USER_TYPE_DELIVERY);
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
      if (isAuthentication(req)) {
         switch (req.getMethod()) {
            case "GET":
               doGet(req, resp);
               break;
            default:
               ServletUtils.printWrite(resp, "Method not found");
         }
      }
      if (isAuthenticationAdmin(req)) {
         switch (req.getMethod()) {
            case "GET":
               doGet(req, resp);
               break;
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
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String pagePara = req.getParameter("page");
      Integer page = StringUtils.toInt(pagePara);
      if (page <= 0) {
         page = 1;
      }
      ArrayList<ProductCategoryEntity> listProductCategory = ProductCategoryDAO.getInstance().gets((page - 1) * 10, 10);
      List<String> listJsonStr = new ArrayList<>();

      for (ProductCategoryEntity productCategory : listProductCategory) {
         listJsonStr.add(productCategory.toJson());
      }
      ServletUtils.printWrite(resp, "[" + String.join(", ", listJsonStr) + "]");
   }

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String result = "";

      try {
         String name = req.getParameter("name");
         String imageBase64 = req.getParameter("imageBase64");

         ProductCategoryEntity newProductCategory = new ProductCategoryEntity();
         newProductCategory.setProductCategoryName(name);

         if (imageBase64 != null && !imageBase64.isEmpty()) {
            newProductCategory.setImage(imageBase64);
         }

         Long newProductCategoryId = ProductCategoryDAO.getInstance().insert(newProductCategory);

         if (newProductCategoryId != 0) {
            result += "true\n" + newProductCategoryId.toString();
         } else {
            result += "false\nError while insert product-category";
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
         String name = (String) parameterMap.get("name");
         String imageBase64 = (String) parameterMap.get("imageBase64");

         ProductCategoryEntity updateProductCategory = ProductCategoryDAO.getInstance().getById(StringUtils.toLong(id));
         updateProductCategory.setProductCategoryName(name);

         if (imageBase64 != null && !imageBase64.isEmpty()) {
            updateProductCategory.setImage(imageBase64);
         }

         Boolean updateResult = ProductCategoryDAO.getInstance().update(updateProductCategory);

         if (updateResult != false) {
            result += "true\n" + updateProductCategory.getProductCategoryId().toString();
         } else {
            result += "false\nError while insert product-category";
         }
      } catch (Exception e) {
         result += "false\n" + e.getMessage();
      }

      ServletUtils.printWrite(resp, result);
   }
}
