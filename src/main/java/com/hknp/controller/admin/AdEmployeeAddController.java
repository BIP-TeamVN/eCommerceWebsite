package com.hknp.controller.admin;

import com.hknp.model.cons.AddressCons;
import com.hknp.model.cons.UserCons;
import com.hknp.model.dao.*;
import com.hknp.model.entity.AddressEntity;
import com.hknp.model.entity.EmployeeEntity;
import com.hknp.model.entity.UserEntity;
import com.hknp.utils.Base64Utils;
import com.hknp.utils.DateTimeUtils;
import com.hknp.utils.HashUtils;
import com.hknp.utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collections;

@WebServlet(urlPatterns = {"/admin/employee/add"})
public class AdEmployeeAddController extends HttpServlet {
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      if (req.getParameter("last-name") == null) {
         ServletUtils.forward(req, resp, "/view/admin/ad-employee-add.jsp");
      } else {
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

         UserEntity newUser = new UserEntity();
         newUser.setFirstName(firstName);
         newUser.setLastName(firstName);
         newUser.setGender(gender);
         newUser.setDateOfBirth(DateTimeUtils.stringToDate(dateOfBirth, "dd/MM/yyyy"));
         newUser.setPhoneNumber(phoneNumber);
         newUser.setSsn(ssn);
         newUser.setEmail(email);

         newUser.setUserType(UserCons.USER_TYPE_EMPLOYEE);
         newUser.setUserName(email);
         newUser.setPassword(HashUtils.getMd5(Base64Utils.encodeFromString("12")));
         newUser.setStatus(true);

         Long newUserId = UserDAO.getInstance().insert(newUser);

         if (newUserId != 0) {
            AddressEntity newAddress = new AddressEntity();
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
               newEmployee.setSalary(BigDecimal.valueOf(0));
               newEmployee.setStartDate(DateTimeUtils.currentDate());

               EmployeeDAO.getInstance().insert((newEmployee));
            }
         }
      }
   }

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      doGet(req, resp);
   }
}

