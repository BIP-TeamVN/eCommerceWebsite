package com.hknp.controller.api;

import com.hknp.model.dao.*;
import com.hknp.model.entity.*;
import com.hknp.utils.*;

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
import java.util.Map;

@WebServlet(urlPatterns = {"/api/deliveries"})
public class DeliveryServlet extends HttpServlet {
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      resp.setContentType("text/html; charset=UTF-8");

      String pagePara = req.getParameter("page");
      Integer page = StringUtils.toInt(pagePara);
      if (page <= 0) {
         page = 1;
      }

      ArrayList<DeliveryEntity> listDelivery = DeliveryDAO.getInstance().gets((page - 1) * 10, 10);
      List<String> listJsonStr = new ArrayList<>();

      for (DeliveryEntity delivery : listDelivery) {
         listJsonStr.add(delivery.toJson());
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
         String email = req.getParameter("email");
         String ssn = req.getParameter("ssn");

         String provinceId = req.getParameter("province");
         String districtId = req.getParameter("district");
         String communeId = req.getParameter("commune");
         String addressStreet = req.getParameter("address-street");

         String startDate = req.getParameter("start-date");
         String salary = req.getParameter("salary");

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
                 Cons.User.USER_TYPE_DELIVERY
         );

         DeliveryEntity newDelivery = new DeliveryEntity();
         newDelivery.setUserEntity(newUser);
         newDelivery.setSalary(StringUtils.toBigDecimal(salary));
         newDelivery.setStartDate(DateTimeUtils.stringToDate(startDate, "yyyy-MM-dd"));

         Long newDeliveryId = DeliveryDAO.getInstance().insert(newDelivery);

         if (newDeliveryId != 0) {
            AddressEntity newAddress = new AddressEntity(
                    addressStreet,
                    communeId,
                    districtId,
                    provinceId,
                    newDeliveryId,
                    lastName + " " + firstName,
                    Cons.Address.DEFAULT_ADDRESS_NAME,
                    phoneNumber
            );

            Long newAddressId = AddressDAO.getInstance().insert(newAddress);

            if (newAddressId != 0) {
               UserEntity user = UserDAO.getInstance().getById(newDeliveryId);
               user.setAddressEntities(Collections.singletonList(AddressDAO.getInstance().getById(newAddressId)));
               UserDAO.getInstance().update(user);

               result += "true\n" + newDeliveryId.toString();
            } else {
               result += "false\nError while insert address";
            }
         } else {
            result += "false\nError while insert user";
         }
      } catch (Exception e) {
         result += "false\n" + e.getMessage();
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

         UserEntity updateUser = UserDAO.getInstance().getById(StringUtils.toLong(id));

         updateUser.setFirstName(firstName);
         updateUser.setLastName(lastName);
         updateUser.setGender(gender);
         updateUser.setDateOfBirth(DateTimeUtils.stringToDate(dateOfBirth, "dd/MM/yyyy"));
         updateUser.setPhoneNumber(phoneNumber);
         updateUser.setSsn(ssn);
         updateUser.setEmail(email);

         updateUser.setUserType(Cons.User.USER_TYPE_DELIVERY);
         updateUser.setUserName(phoneNumber);
         updateUser.setPassword(HashUtils.getMd5(Base64Utils.encodeFromString(phoneNumber)));
         updateUser.setStatus(true);

         Boolean updateResult = UserDAO.getInstance().update(updateUser);

         if (updateResult != false) {
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
               DeliveryEntity deliveryEntity = DeliveryDAO.getInstance().getById(updateUser.getUserId());

               deliveryEntity.setSalary(StringUtils.toBigDecimal(salary));
               deliveryEntity.setStartDate(DateTimeUtils.stringToDate(startDate, "dd/MM/yyyy"));

               DeliveryDAO.getInstance().update((deliveryEntity));

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

      try (PrintWriter out = resp.getWriter()) {
         out.write(result);
      }
   }

   @Override
   protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      resp.setContentType("text/html; charset=UTF-8");

      try (PrintWriter out = resp.getWriter()) {
         out.write("false");
      }
   }
}
