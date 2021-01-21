package com.hknp.controller.common;

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

@WebServlet(urlPatterns = {"/logout"})
public class LogoutController extends HttpServlet {
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      doPost(req, resp);
   }

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String urlLogout = "/home";
      HttpSession session = req.getSession();
      Long id = (Long) session.getAttribute("id");

      String userType = UserDAO.getInstance().getUserType(id);

      if (!userType.equals(Cons.User.USER_TYPE_CUSTOMER)
              && !userType.equals("")) {
         urlLogout = "/login";
      }

      session.invalidate();
      ServletUtils.forward(req, resp, "/home");
   }
}
