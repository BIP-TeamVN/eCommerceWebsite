package com.hknp.controller.api;

import com.hknp.model.dao.CustomerDAO;
import com.hknp.model.dao.UserDAO;
import com.hknp.model.entity.Cons;
import com.hknp.model.entity.CustomerEntity;
import com.hknp.model.entity.UserEntity;
import com.hknp.utils.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = {"/api/customers"})
public class CustomerServlet extends HttpServlet {

   public boolean isAuthentication(HttpServletRequest req) {
      HttpSession session = req.getSession(false);
      Long id = (Long) session.getAttribute("id");

      if (id != null) {
         String userType = UserDAO.getInstance().getUserType(id);
         return userType.equals(Cons.User.USER_TYPE_ADMIN) || userType.equals(Cons.User.USER_TYPE_EMPLOYEE);
      }

      return false;
   }
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      resp.setContentType("text/html; charset=UTF-8");
      String result = "";

      if (isAuthentication(req)) {
         String pagePara = req.getParameter("page");
         Integer page = StringUtils.toInt(pagePara);
         if (page <= 0) {
            page = 1;
         }

         ArrayList<CustomerEntity> listCustomer = CustomerDAO.getInstance().gets((page - 1) * 10, 10);
         List<String> listJsonStr = new ArrayList<>();

         for (CustomerEntity customer : listCustomer) {
            listJsonStr.add(customer.toJson());
         }
         result = "[" + String.join(", ", listJsonStr) + "]";
      } else {
         result = "Access denied";
      }
      try (PrintWriter out = resp.getWriter()) {
         out.write(result);
      }
   }

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      resp.setContentType("text/html; charset=UTF-8");
      String result = "";

      if (isAuthentication(req)) {
         try {
            String lastName = req.getParameter("last-name");
            String firstName = req.getParameter("first-name");
            String gender = req.getParameter("gender");
            String dateOfBirth = req.getParameter("dob");
            String phoneNumber = req.getParameter("phone-number");
            //String ssn = req.getParameter("ssn");
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            String passwordAgain = req.getParameter("password-again");

            if (passwordAgain.equals(password)) {

               UserEntity newUser = new UserEntity();

               newUser.setFirstName(firstName);
               newUser.setLastName(lastName);
               newUser.setGender(gender);
               newUser.setDateOfBirth(DateTimeUtils.stringToDate(dateOfBirth, "dd/MM/yyyy"));
               newUser.setPhoneNumber(phoneNumber);
               //newUser.setSsn(ssn);
               newUser.setEmail(email);

               newUser.setUserType(Cons.User.USER_TYPE_CUSTOMER);
               newUser.setUserName(phoneNumber);
               newUser.setPassword(password);
               newUser.setPassword(HashUtils.getMd5(Base64Utils.encodeFromString(phoneNumber)));
               newUser.setStatus(true);

               //Long newUserId = UserDAO.getInstance().insert(newUser);

               CustomerEntity newCustomer = new CustomerEntity();
               newCustomer.setUserEntity(newUser);
               newCustomer.setRegisterDate(DateTimeUtils.currentDate());

               Long newUserId = CustomerDAO.getInstance().insert(newCustomer);
               if (newUserId != 0) {
                  String otp = GenerateUtils.oneTimePassword(6);
                  String sVerify = "OTP: " + otp;
                  MailUtils.sendPlanText(email, "Xác thực tài khoản", sVerify);
                  otp = HashUtils.getMd5(otp);
                  CookieUtils.addCookie(resp, "otp", otp, 60 * 5);
                  result += "true\n" + newUserId.toString();
               }
            } else {
               result += "false\nError while insert customer\n";
            }
         } catch (Exception e) {
            result += "false\nError while insert customer\n" + e.getMessage();
         }
      } else {
         result = "Access denied";
      }
      try (PrintWriter out = resp.getWriter()) {
         out.write(result);
      }
   }

   @Override
   protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      resp.setContentType("text/html; charset=UTF-8");
      String result = "";
      Map<String,Object> parameterMap = ServletUtils.getParametersMap(req);

      if (isAuthentication(req)) {
         try {
            String id = (String) parameterMap.get("id");
            String lastName = (String) parameterMap.get("last-name");
            String firstName = (String) parameterMap.get("first-name");
            String gender = (String) parameterMap.get("gender");
            String dateOfBirth = (String) parameterMap.get("dob");
            String phoneNumber = (String) parameterMap.get("phone-number");
            String ssn = (String) parameterMap.get("ssn");
            String email = (String) parameterMap.get("email");

            UserEntity updateUser = UserDAO.getInstance().getById(StringUtils.toLong(id));

            updateUser.setFirstName(firstName);
            updateUser.setLastName(lastName);
            updateUser.setGender(gender);
            updateUser.setDateOfBirth(DateTimeUtils.stringToDate(dateOfBirth, "dd/MM/yyyy"));
            updateUser.setPhoneNumber(phoneNumber);
            updateUser.setSsn(ssn);
            updateUser.setEmail(email);

            updateUser.setUserType(Cons.User.USER_TYPE_CUSTOMER);
            updateUser.setUserName(phoneNumber);
            updateUser.setPassword(HashUtils.getMd5(Base64Utils.encodeFromString(phoneNumber)));
            updateUser.setStatus(true);

            Boolean updateResult = UserDAO.getInstance().update(updateUser);
            if (updateResult == false)
               result += "false\nError while insert user\n";

         } catch (Exception e) {
            result += "false\n" + e.getMessage();
         }
      } else {
         result = "Access denied";
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
