package com.hknp.controller.api;

import com.hknp.model.dao.CustomerDAO;
import com.hknp.model.dao.ProductDAO;
import com.hknp.model.dao.UserDAO;
import com.hknp.model.entity.Cons;
import com.hknp.model.entity.CustomerEntity;
import com.hknp.utils.ServletUtils;
import com.hknp.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/api/count-product-count"})
public class CountProductServlet extends HttpServlet {
   public boolean isAuthentication(HttpServletRequest req) {
      HttpSession session = req.getSession(false);
      Long id = (Long) session.getAttribute("id");

      if (id != null) {
         String userType = UserDAO.getInstance().getUserType(id);
         return userType.equals(Cons.User.USER_TYPE_ADMIN) || userType.equals(Cons.User.USER_TYPE_EMPLOYEE) || userType.equals(Cons.User.USER_TYPE_SELLER);
      }

      return false;
   }

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      if (isAuthentication(req)) {
         Long totalRows = ProductDAO.getInstance().count(StringUtils.toInt(req.getParameter("status")));

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
}
