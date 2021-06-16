package com.hknp.controller.delivery;

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

@WebServlet(urlPatterns = {"/delivery"})
public class HomeController extends HttpServlet {
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String type = req.getParameter("type");// lấy ra loại type
      Integer Type = StringUtils.toInt(type);

      Integer tempt = Type;
      if (tempt > 3) {
         tempt -= 2;
      } else if (tempt == 2) {
         tempt -= 1;
      }
      String status = tempt.toString();

      HttpSession session = req.getSession();
      Long id = (Long) session.getAttribute("id");

      if (Type == 2) {
         Long totalRows = BillDAO.getInstance().count(2);
         String page = req.getParameter("page");

         Long currentPage = StringUtils.toLong(page);
         Long totalPage = (totalRows / 10) + ((totalRows % 10 == 0) ? 0 : 1);

         if (currentPage > totalPage) {
            currentPage = totalPage;
         }
         if (currentPage < 1) {
            currentPage = 1L;
         }
         req.setAttribute("currentPage", currentPage);
         req.setAttribute("totalPage", totalPage);

         req.setAttribute("type", type);
         req.setAttribute("nav", status);
         RequestDispatcher reqDispatcher = req.getRequestDispatcher("/view/delivery/dh-billdelivering.jsp");
         reqDispatcher.forward(req, resp);
      } else if (Type == 4 || Type == 5 || Type == 6) {
         Long totalRows = BillDAO.getInstance().countForShipper(id, Type);
         String page = req.getParameter("page");

         Long currentPage = StringUtils.toLong(page);
         Long totalPage = (totalRows / 10) + ((totalRows % 10 == 0) ? 0 : 1);

         if (currentPage > totalPage) {
            currentPage = totalPage;
         }
         if (currentPage < 1) {
            currentPage = 1L;
         }
         req.setAttribute("currentPage", currentPage);
         req.setAttribute("totalPage", totalPage);

         req.setAttribute("type", type);
         req.setAttribute("nav", status);
         RequestDispatcher reqDispatcher = req.getRequestDispatcher("/view/delivery/dh-billdelivering.jsp");
         reqDispatcher.forward(req, resp);
      } else {
         Long totalRows = BillDAO.getInstance().count(2);
         String page = req.getParameter("page");

         Long currentPage = StringUtils.toLong(page);
         Long totalPage = (totalRows / 10) + ((totalRows % 10 == 0) ? 0 : 1);

         if (currentPage > totalPage) {
            currentPage = totalPage;
         }
         if (currentPage < 1) {
            currentPage = 1L;
         }
         req.setAttribute("currentPage", currentPage);
         req.setAttribute("totalPage", totalPage);

         RequestDispatcher reqDispatcher = req.getRequestDispatcher("/view/delivery/home.jsp");
         reqDispatcher.forward(req, resp);
      }

   }

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      doGet(req, resp);
   }
}