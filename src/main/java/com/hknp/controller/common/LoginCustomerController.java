package com.hknp.controller.common;

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


@WebServlet(urlPatterns = {"/login-customer"})

public class LoginCustomerController extends HttpServlet {
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      doPost(req, resp);
   }

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      HttpSession session = req.getSession(true);
      Long id = (Long) session.getAttribute("id");
      String result = "";
      try {
         String userName = req.getParameter("username");
         String password = req.getParameter("password");

         id = UserDAO.getInstance().checkLogin(userName, password);

         UserEntity user = UserDAO.getInstance().getById(id);

         String userType = UserDAO.getInstance().getUserType(id);

         if (userType.equals(Cons.User.USER_TYPE_CUSTOMER)) {
            result += "true\n"+user.getFullName()+ "\n" + user.getImageSrc();
            ServletUtils.printWrite(resp, result);
         } else {
            result += "false\n";
            ServletUtils.printWrite(resp, result);
         }
      } catch (Exception e1) {
         result += "false\n";
         ServletUtils.printWrite(resp, result);
      }
   }
}
