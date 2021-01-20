package com.hknp.controller.api;

import com.hknp.model.dao.EmployeeDAO;
import com.hknp.utils.ServletUtils;
import com.hknp.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/api/count-employee"})
public class SearchEmployeeServlet extends HttpServlet {
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String keyword = req.getParameter("keyword").trim();
      if (keyword == null) {
         keyword = "";
      }
      Integer status = StringUtils.toInt(req.getParameter("status"));
      Long totalRows;
      totalRows = EmployeeDAO.getInstance().count(status, keyword);

      String page = req.getParameter("page");

      Long currentPage = StringUtils.toLong(page);
      Long totalPage = (totalRows / 10) + ((totalRows % 10 == 0) ? 0 : 1);

      if (currentPage > totalPage) {
         currentPage = totalPage;
      }
      if (currentPage < 1) {
         currentPage = 1L;
      }

      ServletUtils.printWrite(resp, totalPage + "," + currentPage);
   }
}
