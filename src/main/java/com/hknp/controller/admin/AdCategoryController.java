package com.hknp.controller.admin;

import com.hknp.model.dao.ProductCategoryDAO;
import com.hknp.utils.ServletUtils;
import com.hknp.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/admin/category"})
public class AdCategoryController extends HttpServlet {
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

      Long totalRows = ProductCategoryDAO.getInstance().count();
      String page = req.getParameter("page");

      Long currentPage = StringUtils.toLong(page);
      Long totalPage = (totalRows / 10) + ((totalRows % 10 == 0) ? 0 : 1);

      if (currentPage > totalPage) {
         currentPage = totalPage;
      }
      if (currentPage < 1) {
         currentPage = 1L;
      }

      req.setAttribute("totalPage", totalPage);
      req.setAttribute("currentPage", currentPage);
      ServletUtils.forward(req, resp, "/view/admin/ad-category.jsp");
   }

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      doGet(req, resp);
   }
}