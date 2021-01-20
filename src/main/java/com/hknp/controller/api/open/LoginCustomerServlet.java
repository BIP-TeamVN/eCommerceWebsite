package com.hknp.controller.api.open;

import com.hknp.model.dao.UserDAO;
import com.hknp.model.entity.Cons;
import com.hknp.model.entity.UserEntity;
import com.hknp.utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(urlPatterns = {"/api/login-customer"})
public class LoginCustomerServlet extends HttpServlet {
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String result = "";
      HttpSession session = req.getSession(false);
      Long id = (Long) session.getAttribute("id");

      if (id != null) {
         UserEntity user = UserDAO.getInstance().getById(id);
         if (user != null
                 && user.getUserType().equals(Cons.User.USER_TYPE_CUSTOMER)) {
            result = user.toJson();
         }
      }
      ServletUtils.printWrite(resp, result);
   }

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      Long id;
      String result = "";
      try {
         String userName = req.getParameter("username");
         String password = req.getParameter("password");

         id = UserDAO.getInstance().checkLogin(userName, password);

         UserEntity user = UserDAO.getInstance().getById(id);

         String userType = UserDAO.getInstance().getUserType(id);

         if (userType.equals(Cons.User.USER_TYPE_CUSTOMER)) {
            result += "true\n" + user.getFullName() + "\n" + user.getImageSrc();

            HttpSession session = req.getSession();
            session.setAttribute("id", id);
         } else {
            result += "false\n";
         }
      } catch (Exception e1) {
         result += "false\n";
      }

      ServletUtils.printWrite(resp, result);
   }
}
