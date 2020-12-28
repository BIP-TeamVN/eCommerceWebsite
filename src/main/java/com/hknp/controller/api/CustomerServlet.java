package com.hknp.controller.api;

import com.hknp.model.cons.UserCons;
import com.hknp.model.dao.CustomerDAO;
import com.hknp.model.dao.UserDAO;
import com.hknp.model.entity.CustomerEntity;
import com.hknp.model.entity.UserEntity;
import com.hknp.utils.Base64Utils;
import com.hknp.utils.DateTimeUtils;
import com.hknp.utils.HashUtils;
import com.hknp.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@WebServlet(urlPatterns = {"/api/customer"})
public class CustomerServlet extends HttpServlet {
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      resp.setContentType("text/html; charset=UTF-8");

      ArrayList<CustomerEntity> listCustomer = CustomerDAO.getInstance().gets();
      List<String> listJsonStr = new ArrayList<>();

      for (CustomerEntity customer : listCustomer) {
         listJsonStr.add(customer.toJson());
      }

      try (PrintWriter out = resp.getWriter()) {
         out.write("[" + String.join(", ", listJsonStr) + "]");
      }
   }

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      resp.setContentType("text/html; charset=UTF-8");
      String result = "";

      try {
         String lastName = req.getParameter("last-name");
         String firstName = req.getParameter("first-name");
         String gender = req.getParameter("gender");
         String dateOfBirth = req.getParameter("dob");
         String phoneNumber = req.getParameter("phone-number");
         String ssn = req.getParameter("ssn");
         String email = req.getParameter("email");
         String password = req.getParameter("password");

         UserEntity newUser = new UserEntity();

         newUser.setFirstName(firstName);
         newUser.setLastName(lastName);
         newUser.setGender(gender);
         newUser.setDateOfBirth(DateTimeUtils.stringToDate(dateOfBirth, "dd/MM/yyyy"));
         newUser.setPhoneNumber(phoneNumber);
         newUser.setSsn(ssn);
         newUser.setEmail(email);

         newUser.setUserType(UserCons.USER_TYPE_CUSTOMER);
         newUser.setUserName(phoneNumber);
         newUser.setPassword(password);
         newUser.setPassword(HashUtils.getMd5(Base64Utils.encodeFromString(phoneNumber)));
         newUser.setStatus(true);

         //Long newUserId = UserDAO.getInstance().insert(newUser);

         CustomerEntity newCustomer = new CustomerEntity();
         newCustomer.setUserEntity(newUser);
         newCustomer.setRegisterDate(DateTimeUtils.currentDate());

         Long newUserId = CustomerDAO.getInstance().insert(newCustomer);

         result += "true\n" + newUserId.toString();
      } catch (Exception e) {
         result += "false\nError while insert customer\n" + e.getMessage();
      }

      try (PrintWriter out = resp.getWriter()) {
         out.write(result);
      }
   }

   @Override
   protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      resp.setContentType("text/html; charset=UTF-8");
      String result = "";

      try {
         String id = req.getParameter("id");
         String lastName = req.getParameter("last-name");
         String firstName = req.getParameter("first-name");
         String gender = req.getParameter("gender");
         String dateOfBirth = req.getParameter("dob");
         String phoneNumber = req.getParameter("phone-number");
         String ssn = req.getParameter("ssn");
         String email = req.getParameter("email");
         String password = req.getParameter("password");

         UserEntity updateUser = UserDAO.getInstance().getById(StringUtils.toLong(id));

         updateUser.setFirstName(firstName);
         updateUser.setLastName(lastName);
         updateUser.setGender(gender);
         updateUser.setDateOfBirth(DateTimeUtils.stringToDate(dateOfBirth, "dd/MM/yyyy"));
         updateUser.setPhoneNumber(phoneNumber);
         updateUser.setSsn(ssn);
         updateUser.setEmail(email);

         updateUser.setUserType(UserCons.USER_TYPE_CUSTOMER);
         updateUser.setUserName(phoneNumber);
         updateUser.setPassword(HashUtils.getMd5(Base64Utils.encodeFromString(phoneNumber)));
         updateUser.setStatus(true);

         Boolean updateResult = UserDAO.getInstance().update(updateUser);
         if (updateResult == false)
            result += "false\nError while insert user\n";

      } catch (Exception e) {
         result += "false\n" + e.getMessage();
      }

      try (PrintWriter out = resp.getWriter()) {
         out.write(result);
      }
   }

   @Override
   protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      super.doDelete(req, resp);
   }
}
