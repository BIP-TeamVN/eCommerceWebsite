package com.hknp.controller.admin;

import com.hknp.model.dao.UserDAO;
import com.hknp.model.entity.Cons;
import com.hknp.utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/admin"})
public class AdminDashboardController extends HttpServlet {
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      HttpSession session = req.getSession();
      try {
         Long id = (Long) session.getAttribute("id");
         String userType = UserDAO.getInstance().getById(id).getUserType();
         if (userType.equals(Cons.User.USER_TYPE_ADMIN)){
            ServletUtils.forward(req, resp, "/view/admin/ad-dashboard.jsp");
         }
         else {
            ServletUtils.forward(req, resp, "/login");
         }
      } catch (Exception e) {
         ServletUtils.forward(req, resp, "/login");
      }
   }

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      doGet(req, resp);
   }
}