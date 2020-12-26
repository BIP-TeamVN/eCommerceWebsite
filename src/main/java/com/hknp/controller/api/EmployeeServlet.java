package com.hknp.controller.api;

import com.hknp.model.cons.*;
import com.hknp.model.dao.*;
import com.hknp.model.entity.*;
import com.hknp.utils.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@WebServlet(urlPatterns = {"/api/employees"})
public class EmployeeServlet extends HttpServlet {
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      resp.setContentType("text/html; charset=UTF-8");

      ArrayList<EmployeeEntity> listEmployee = EmployeeDAO.getInstance().gets();
      List<String> listJsonStr = new ArrayList<>();

      for (EmployeeEntity employee: listEmployee) {
         listJsonStr.add(employee.toJson());
      }

      try (PrintWriter out = resp.getWriter()) {
         out.write("[" + String.join(", ", listJsonStr) + "]");
      }
   }

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      resp.setContentType("text/html; charset=UTF-8");

      try {
         String lastName = req.getParameter("last-name");
         String firstName = req.getParameter("first-name");

         String gender = req.getParameter("gender");
         String dateOfBirth = req.getParameter("dob");
         String phoneNumber = req.getParameter("phone-number");
         String ssn = req.getParameter("ssn");
         String email = req.getParameter("email");

         String provinceId = req.getParameter("province");
         String districtId = req.getParameter("district");
         String communeId = req.getParameter("commune");
         String addressStreet = req.getParameter("address-street");

         String startDate = req.getParameter("start-date");
         String salary = req.getParameter("salary");

         UserEntity newUser = new UserEntity();

         newUser.setFirstName(firstName);
         newUser.setLastName(firstName);
         newUser.setGender(gender);
         newUser.setDateOfBirth(DateTimeUtils.stringToDate(dateOfBirth, "dd/MM/yyyy"));
         newUser.setPhoneNumber(phoneNumber);
         newUser.setSsn(ssn);
         newUser.setEmail(email);

         newUser.setUserType(UserCons.USER_TYPE_EMPLOYEE);
         newUser.setUserName(phoneNumber);
         newUser.setPassword(HashUtils.getMd5(Base64Utils.encodeFromString(phoneNumber)));
         newUser.setStatus(true);

         Long newUserId = UserDAO.getInstance().insert(newUser);

         if (newUserId != 0) {
            AddressEntity newAddress = new AddressEntity();

            newAddress.setPhoneNumber(phoneNumber);
            newAddress.setStreet(addressStreet);
            newAddress.setProvinceEntity(ProvinceDAO.getInstance().getById(provinceId));
            newAddress.setDistrictEntity(DistrictDAO.getInstance().getById(districtId));
            newAddress.setCommuneEntity(CommuneDAO.getInstance().getById(communeId));
            newAddress.setFullName(lastName + " " + firstName);
            newAddress.setAddressName(AddressCons.DEFAULT_ADDRESS_NAME);
            newAddress.setUserId(newUserId);

            Long newAddressId = AddressDAO.getInstance().insert(newAddress);

            if (newAddressId != 0) {
               UserEntity user = UserDAO.getInstance().getById(newUserId);
               user.setAddressEntities(Collections.singletonList(AddressDAO.getInstance().getById(newAddressId)));

               UserDAO.getInstance().update(user);

               EmployeeEntity newEmployee = new EmployeeEntity();
               newEmployee.setUserId(newUserId);
               newEmployee.setUserEntity(user);
               newEmployee.setSalary(StringUtils.toBigDecimal(salary));
               newEmployee.setStartDate(DateTimeUtils.currentDate());

               EmployeeDAO.getInstance().insert((newEmployee));
            }
         }
      }
      catch (Exception e) {

      }

      try (PrintWriter out = resp.getWriter()) {
         out.write("id");
      }
   }

   @Override
   protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      resp.setContentType("text/html; charset=UTF-8");

      try (PrintWriter out = resp.getWriter()) {
         out.write("true");
      }
   }

   @Override
   protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      super.doHead(req, resp);
   }

   @Override
   protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      resp.setContentType("text/html; charset=UTF-8");

      try (PrintWriter out = resp.getWriter()) {
         out.write("false");
      }
   }
}
