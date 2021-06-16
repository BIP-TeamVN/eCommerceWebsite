package com.hknp.controller.web;

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

@WebServlet(urlPatterns = {"/category"})
public class CategoryController extends HttpServlet {
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String idPara = req.getParameter("id");
      Long categoryId = StringUtils.toLong(idPara);
      ProductCategoryEntity categoryEntity = ProductCategoryDAO.getInstance().getById(categoryId);

      if (categoryEntity != null) {
         /* Set attribute */
         ServletUtils.forward(req, resp, "/view/web/category.jsp");
      } else {
         ServletUtils.forward(req, resp, "/view/web/home.jsp");
      }
   }

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      doGet(req, resp);
   }
}