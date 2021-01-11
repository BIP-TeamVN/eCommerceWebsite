package com.hknp.controller.api;

import com.hknp.model.dao.*;
import com.hknp.model.entity.AddressEntity;
import com.hknp.model.entity.Cons;
import com.hknp.utils.StringUtils;

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

@WebServlet(urlPatterns = {"/api/address"})
public class AddressServlet extends HttpServlet {

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
         ArrayList<AddressEntity> listAddress = AddressDAO.getInstance().gets();
         List<String> listJsonStr = new ArrayList<>();

         for (AddressEntity address : listAddress) {
            listJsonStr.add(address.toJson());
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
            String addressStreet = req.getParameter("address-street");
            String provinceId = req.getParameter("province");
            String districtId = req.getParameter("district");
            String communeId = req.getParameter("commune");
            String userId = req.getParameter("user-id");
            String fullName = req.getParameter("full-name");
            String addressName = req.getParameter("address-name");
            String phoneNumber = req.getParameter("phone-number");

            AddressEntity newAddress = new AddressEntity();

            newAddress.setStreet(addressStreet);
            newAddress.setProvinceEntity(ProvinceDAO.getInstance().getById(provinceId));
            newAddress.setDistrictEntity(DistrictDAO.getInstance().getById(districtId));
            newAddress.setCommuneEntity(CommuneDAO.getInstance().getById(communeId));
            newAddress.setUserId(StringUtils.toLong(userId));
            newAddress.setFullName(fullName);
            newAddress.setAddressName(addressName);
            newAddress.setPhoneNumber(phoneNumber);

            Long insertResult = AddressDAO.getInstance().insert(newAddress);
            if (insertResult != 0) {
               result += "true\n" + insertResult;
            } else {
               result += "false\nError while insert address\n";
            }
         } catch (Exception e) {
            result += "false\n" + e.getMessage();
         }
      }else {
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
      if (isAuthentication(req)) {
         try {
            String addressId = req.getParameter("id");
            String addressStreet = req.getParameter("address-street");
            String provinceId = req.getParameter("province");
            String districtId = req.getParameter("district");
            String communeId = req.getParameter("commune");
            String fullName = req.getParameter("full-name");
            String addressName = req.getParameter("address-name");
            String phoneNumber = req.getParameter("phone-number");

            AddressEntity updateAddress = AddressDAO.getInstance().getById(StringUtils.toLong(addressId));

            updateAddress.setStreet(addressStreet);
            updateAddress.setProvinceEntity(ProvinceDAO.getInstance().getById(provinceId));
            updateAddress.setDistrictEntity(DistrictDAO.getInstance().getById(districtId));
            updateAddress.setCommuneEntity(CommuneDAO.getInstance().getById(communeId));
            updateAddress.setFullName(fullName);
            updateAddress.setAddressName(addressName);
            updateAddress.setPhoneNumber(phoneNumber);

            Boolean updateResult = AddressDAO.getInstance().update(updateAddress);
            if (updateResult == false)
               result += "false\nError while update address\n";
            else {
               result += "True";
            }

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
