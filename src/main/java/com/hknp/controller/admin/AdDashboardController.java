package com.hknp.controller.admin;

import com.hknp.model.dao.UserDAO;
import com.hknp.model.entity.UserEntity;
import com.hknp.utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/admin"})
public class AdDashboardController extends HttpServlet {
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      HttpSession session = req.getSession(true);
      Long id = (Long) session.getAttribute("id");
      if (id != null) {
         UserEntity user = UserDAO.getInstance().getById(id);
         req.setAttribute("id", id);
      }
      ServletUtils.forward(req, resp, "/view/admin/ad-dashboard.jsp");
   }

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      doGet(req, resp);
   }
}