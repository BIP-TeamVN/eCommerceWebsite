package com.hknp.controller.api;

import com.hknp.model.dao.BillDAO;
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

@WebServlet(urlPatterns = {"/api/check-number-bill"})
public class CheckNumberForDeliveryServlet extends HttpServlet {
   public boolean isAuthentication(HttpServletRequest req) {
      HttpSession session = req.getSession(false);
      Long id = (Long) session.getAttribute("id");

      if (id != null) {
         String userType = UserDAO.getInstance().getUserType(id);
         return userType.equals(Cons.User.USER_TYPE_DELIVERY);
      }

      return false;
   }

   @Override
   protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      if (isAuthentication(req)) {
         switch (req.getMethod()) {
            case "GET":
               doGet(req, resp);
               break;
            default:
               ServletUtils.printWrite(resp, "Method not found");
         }
      } else {
         ServletUtils.printWrite(resp, "Access denied");
      }
   }

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String result = "";
      HttpSession session = req.getSession();
      Long id = (Long) session.getAttribute("id");
      Long number = BillDAO.getInstance().check(id);
      if (number < 5) {
         result += "true";
      }

      ServletUtils.printWrite(resp, result);
   }
}
