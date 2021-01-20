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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = {"/api/customers"})
public class CustomerServlet extends HttpServlet {
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String pagePara = req.getParameter("page");
      Integer page = StringUtils.toInt(pagePara);

      String keyword = req.getParameter("keyword").trim();
      String columnName = req.getParameter("columnName");
      String typeSort = req.getParameter("typeSort");
      if (keyword == null) {
         keyword = "";
      }

      if (page <= 0) {
         page = 1;
      }

      List<CustomerEntity> listCustomer = new ArrayList<>();
      List<String> listJsonStr = new ArrayList<>();

      listCustomer = CustomerDAO.getInstance().gets((page - 1) * 10, 10, keyword, columnName, typeSort);

      for (CustomerEntity Cus: listCustomer) {
         listJsonStr.add(Cus.toJson());
      }

      ServletUtils.printWrite(resp, "[" + String.join(", ", listJsonStr) + "]");
   }

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String result = "";

      try {
         String lastName = req.getParameter("login-last-name");
         String firstName = req.getParameter("login-first-name");
         String email = req.getParameter("signup-email");
         String password = req.getParameter("signup-password");
         String passwordAgain = req.getParameter("signup-re-password");

         UserEntity newUser = new UserEntity();

         newUser.setFirstName(firstName);
         newUser.setLastName(lastName);
         newUser.setEmail(email);

         newUser.setUserType(Cons.User.USER_TYPE_CUSTOMER);
         newUser.setUserName(email);
         newUser.setPassword(HashUtils.getMd5(Base64Utils.encodeFromString(password)));
         newUser.setImage(Cons.User.DEFAULT_USER_IMAGE_MALE_SRC);
         newUser.setStatus(true);


         CustomerEntity newCustomer = new CustomerEntity();
         newCustomer.setUserEntity(newUser);
         newCustomer.setRegisterDate(DateTimeUtils.currentDate());

         Long newUserId = CustomerDAO.getInstance().insert(newCustomer);
         if(newUserId != 0){
            result += "true\n" + newUserId.toString();
         }
         else {
            result += "false\n";
         }

      } catch (Exception e) {
         result += "false\nError while insert customer\n" + e.getMessage();
      }

      ServletUtils.printWrite(resp, result);
   }

   @Override
   protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String result = "";
      Map<String, Object> parameterMap = ServletUtils.getParametersMap(req);

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
      ServletUtils.printWrite(resp, result);
   }

   @Override
   protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      super.doDelete(req, resp);
   }
}
