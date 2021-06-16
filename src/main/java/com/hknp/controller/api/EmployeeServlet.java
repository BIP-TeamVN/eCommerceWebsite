package com.hknp.controller.api;

import com.hknp.model.dao.*;
import com.hknp.model.entity.AddressEntity;
import com.hknp.model.entity.Cons;
import com.hknp.model.entity.EmployeeEntity;
import com.hknp.model.entity.UserEntity;
import com.hknp.utils.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = {"/api/employees"})
public class EmployeeServlet extends HttpServlet {
   public boolean isAuthentication(HttpServletRequest req) {
      HttpSession session = req.getSession(false);
      Long id = (Long) session.getAttribute("id");

      if (id != null) {
         String userType = UserDAO.getInstance().getUserType(id);
         return userType.equals(Cons.User.USER_TYPE_ADMIN);
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
            case "POST":
               doPost(req, resp);
               break;
            case "PUT":
            case "PATCH":
               doPut(req, resp);
               break;
            case "DELETE":
               doDelete(req, resp);
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
      String pagePara = req.getParameter("page");
      String keyword = req.getParameter("keyword").trim();
      String columnName = req.getParameter("columnName");
      String typeSort = req.getParameter("typeSort");
      Integer status = StringUtils.toInt(req.getParameter("status"));

      if (keyword == null) {
         keyword = "";
      }

      Integer page = StringUtils.toInt(pagePara);
      if (page <= 0) {
         page = 1;
      }

      ArrayList<EmployeeEntity> listEmployee = EmployeeDAO.getInstance().gets((page - 1) * 10, 10, keyword, status, columnName, typeSort);
      List<String> listJsonStr = new ArrayList<>();


      for (EmployeeEntity employee : listEmployee) {
         listJsonStr.add(employee.toJson());
      }

      ServletUtils.printWrite(resp, "[" + String.join(", ", listJsonStr) + "]");
   }

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String result = "";

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

         String image = req.getParameter("image");

         UserEntity newUser = new UserEntity(
                 lastName,
                 firstName,
                 gender,
                 DateTimeUtils.stringToDate(dateOfBirth, "yyyy-MM-dd"),
                 ssn,
                 phoneNumber,
                 email,
                 phoneNumber, // default username
                 HashUtils.getMd5(Base64Utils.encodeFromString(phoneNumber)),
                 Cons.User.USER_TYPE_EMPLOYEE
         );

         if (image != null && !image.isEmpty()) {
            newUser.setImage(image);
         }

         EmployeeEntity newEmployee = new EmployeeEntity();
         newEmployee.setUserEntity(newUser);
         newEmployee.setSalary(StringUtils.toBigDecimal(salary));
         newEmployee.setStartDate(DateTimeUtils.stringToDate(startDate, "yyyy-MM-dd"));

         Long newEmployeeId = EmployeeDAO.getInstance().insert(newEmployee);

         if (newEmployeeId != 0) {
            AddressEntity newAddress = new AddressEntity(
                    addressStreet,
                    communeId,
                    districtId,
                    provinceId,
                    newEmployeeId,
                    lastName + " " + firstName,
                    Cons.Address.DEFAULT_ADDRESS_NAME,
                    phoneNumber
            );

            Long newAddressId = AddressDAO.getInstance().insert(newAddress);

            if (newAddressId != 0) {
               UserEntity user = UserDAO.getInstance().getById(newEmployeeId);
               user.setAddressEntities(Collections.singletonList(AddressDAO.getInstance().getById(newAddressId)));
               UserDAO.getInstance().update(user);

               result += "true\n" + newEmployeeId;
            } else {
               result += "false\nError while insert address";
            }
         } else {
            result += "false\nError while insert user";
         }
      } catch (Exception e) {
         result += "false\n" + e.getMessage();
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

         String provinceId = (String) parameterMap.get("province");
         String districtId = (String) parameterMap.get("district");
         String communeId = (String) parameterMap.get("commune");
         String addressStreet = (String) parameterMap.get("address-street");

         String startDate = (String) parameterMap.get("start-date");
         String salary = (String) parameterMap.get("salary");

         String image = (String) parameterMap.get("image");

         UserEntity updateUser = UserDAO.getInstance().getById(StringUtils.toLong(id));

         updateUser.setFirstName(firstName);
         updateUser.setLastName(lastName);
         updateUser.setGender(gender);
         updateUser.setDateOfBirth(DateTimeUtils.stringToDate(dateOfBirth, "yyyy-MM-dd"));
         updateUser.setPhoneNumber(phoneNumber);
         updateUser.setSsn(ssn);
         updateUser.setEmail(email);
         updateUser.setImage(image);

         Boolean updateResult = UserDAO.getInstance().update(updateUser);

         if (updateResult != false) {
            if (updateUser.getAddressEntities().size() == 0) {
               updateUser.setAddressEntities(Collections.singletonList(new AddressEntity()));
            }
            AddressEntity updateAddress = updateUser.getAddressEntities().get(0);

            updateAddress.setPhoneNumber(phoneNumber);
            updateAddress.setStreet(addressStreet);
            updateAddress.setProvinceEntity(ProvinceDAO.getInstance().getById(provinceId));
            updateAddress.setDistrictEntity(DistrictDAO.getInstance().getById(districtId));
            updateAddress.setCommuneEntity(CommuneDAO.getInstance().getById(communeId));

            updateAddress.setFullName(lastName + " " + firstName);
            updateAddress.setAddressName(Cons.Address.DEFAULT_ADDRESS_NAME);
            updateAddress.setUserId(updateUser.getUserId());

            Boolean updateAddressResult = AddressDAO.getInstance().update(updateAddress);
            if (updateAddressResult != false) {
               EmployeeEntity employeeEntity = EmployeeDAO.getInstance().getById(updateUser.getUserId());

               employeeEntity.setSalary(StringUtils.toBigDecimal(salary));
               employeeEntity.setStartDate(DateTimeUtils.stringToDate(startDate, "yyyy-MM-dd"));

               EmployeeDAO.getInstance().update((employeeEntity));

               result += "true\n" + updateUser.getUserId().toString();
            } else {
               result += "false\nError while insert address";
            }
         } else {
            result += "false\nError while insert user";
         }
      } catch (Exception e) {
         result += "false\n" + e.getMessage();
      }

      ServletUtils.printWrite(resp, result);
   }

   @Override
   protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      ServletUtils.printWrite(resp, "false");
   }
}
