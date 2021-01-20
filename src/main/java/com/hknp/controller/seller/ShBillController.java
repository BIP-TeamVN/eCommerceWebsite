package com.hknp.controller.seller;

import com.hknp.model.dao.BillDAO;
import com.hknp.utils.StringUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/seller/bills"})
public class ShBillController extends HttpServlet {
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      HttpSession session = req.getSession();
      Long sellerId = (Long) session.getAttribute("id");
      Long totalRows = BillDAO.getInstance().count(sellerId, 0);
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
      RequestDispatcher reqDispatcher = req.getRequestDispatcher("/view/seller/sh-bill.jsp");
      reqDispatcher.forward(req, resp);
   }

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      doGet(req, resp);
   }
}