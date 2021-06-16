package com.hknp.controller.admin;

import com.hknp.model.dao.*;
import com.hknp.model.entity.AddressEntity;
import com.hknp.model.entity.Cons;
import com.hknp.model.entity.UserEntity;
import com.hknp.utils.DateTimeUtils;
import com.hknp.utils.ServletUtils;
import com.hknp.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;


@WebServlet(urlPatterns = {"/admin/information/edit"})
public class AdInformationController extends HttpServlet {
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      HttpSession session = req.getSession();
      Long userId = (Long) session.getAttribute("id");
      UserEntity userEdit = null;

      if (userId != 0) {
         userEdit = UserDAO.getInstance().getById(userId);
         if (userEdit != null) {

            if (userEdit.getAddressEntities().size() == 0) {

               AddressEntity newAddress = new AddressEntity();
               newAddress.setUserId(userId);
               AddressDAO.getInstance().insert(newAddress);
               userEdit.setAddressEntities(Collections.singletonList(newAddress));
               UserDAO.getInstance().update(userEdit);
            }
            req.setAttribute("userEdit", userEdit);
            ServletUtils.forward(req, resp, "/view/admin/ad-information.jsp");
            return;
         }
      }
      ServletUtils.forward(req, resp, "/admin");

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

            updateAddress.setStreet(addressStreet);
            updateAddress.setProvinceEntity(ProvinceDAO.getInstance().getById(provinceId));
            updateAddress.setDistrictEntity(DistrictDAO.getInstance().getById(districtId));
            updateAddress.setCommuneEntity(CommuneDAO.getInstance().getById(communeId));

            updateAddress.setFullName(lastName + " " + firstName);
            updateAddress.setAddressName(Cons.Address.DEFAULT_ADDRESS_NAME);
            updateAddress.setUserId(updateUser.getUserId());

            AddressDAO.getInstance().update(updateAddress);
            result += "true\n" + updateUser.getUserId().toString();
         } else {
            result += "false\nError while insert user";
         }
      } catch (Exception e) {
         result += "false\n" + e.getMessage();
      }

      ServletUtils.printWrite(resp, result);
   }
}
