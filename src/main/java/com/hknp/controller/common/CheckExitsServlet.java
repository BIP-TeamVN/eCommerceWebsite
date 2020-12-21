package com.hknp.controller.common;

import com.hknp.model.dao.UserDAO;
import com.hknp.model.entity.UserEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(urlPatterns = {"/CheckExitsServlet"})
public class CheckExitsServlet extends HttpServlet {
   private ArrayList<UserEntity> listUsers;

   @Override
   public void init() throws ServletException {
      listUsers = UserDAO.getInstance().gets();
   }

   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      try (PrintWriter out = response.getWriter()) {
         String op = request.getParameter("operation");
         String checkValue = request.getParameter("chkValue");
         String result = "true";

         if (op.equals("email")) {
            for (int i = 0; i < listUsers.size(); i++) {
               if (listUsers.get(i).getEmail().equals(checkValue)) {
                  result = "false";
                  break;
               }
            }
         } else if (op.equals("phone-number")) {
            for (int i = 0; i < listUsers.size(); i++) {
               if (listUsers.get(i).getPhoneNumber().equals(checkValue)) {
                  result = "false";
                  break;
               }
            }
         }
         out.write(result);
      }
   }

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      doGet(req, resp);
   }
}