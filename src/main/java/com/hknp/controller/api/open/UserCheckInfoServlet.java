package com.hknp.controller.api.open;

import com.hknp.model.dao.UserDAO;
import com.hknp.utils.ServletUtils;
import com.hknp.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/api/users/check-info"})
public class UserCheckInfoServlet extends HttpServlet {
   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String infoType = request.getParameter("type");
      String checkValue = request.getParameter("chk-value");
      String idPara = request.getParameter("id");
      Long userId = StringUtils.toLong(idPara);

      String result = "true";

      if (infoType.equals("email")) {
         result = UserDAO.getInstance().checkEmail(userId, checkValue).toString();
      } else if (infoType.equals("phone-number")) {
         result = UserDAO.getInstance().checkPhoneNumber(userId, checkValue).toString();
      }

      ServletUtils.printWrite(response, result);
   }
}