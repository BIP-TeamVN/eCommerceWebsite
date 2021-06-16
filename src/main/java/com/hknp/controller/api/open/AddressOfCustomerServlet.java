package com.hknp.controller.api.open;

import com.hknp.model.dao.*;
import com.hknp.model.entity.AddressEntity;
import com.hknp.utils.ServletUtils;
import com.hknp.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = {"/api/info/address"})
public class AddressOfCustomerServlet extends HttpServlet {
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

      HttpSession session = req.getSession(false);
      Long userId = (Long) session.getAttribute("id");

      List<AddressEntity> addressEntities = UserDAO.getInstance().getById(userId).getAddressEntities();
      List<String> listJsonStr = new ArrayList<>();
      for (AddressEntity a : addressEntities) {
         a.setStreet(a.getStreet().replace("\n", "<br>"));
         listJsonStr.add(a.toJson1());
      }
      ServletUtils.printWrite(resp, "[" + String.join(", ", listJsonStr) + "]");
   }

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String result = "";

      try {
         String streetPara = req.getParameter("street");
         String communeId = req.getParameter("commune");
         String districtId = req.getParameter("district");
         String provinceId = req.getParameter("province");
         String fullName = req.getParameter("full-name");
         String addressName = req.getParameter("address-name");
         String phoneNumber = req.getParameter("phone-number");

         HttpSession session = req.getSession();
         Long userId = (Long) session.getAttribute("id");

         AddressEntity addressEntity = new AddressEntity(streetPara, communeId, districtId, provinceId, userId, fullName, addressName, phoneNumber);
         Long resultAddress = AddressDAO.getInstance().insert(addressEntity);
         if (resultAddress != 0) {
            result += "true\n" + resultAddress;
         } else {
            result += "false\nError while insert address";
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
         String streetPara = (String) parameterMap.get("street");
         String communeId = (String) parameterMap.get("commune");
         String districtId = (String) parameterMap.get("district");
         String provinceId = (String) parameterMap.get("province");
         String fullName = (String) parameterMap.get("full-name");
         String addressName = (String) parameterMap.get("address-name");
         String phoneNumber = (String) parameterMap.get("phone-number");

         HttpSession session = req.getSession();
         Long userId = (Long) session.getAttribute("id");

         AddressEntity addressEntity = AddressDAO.getInstance().getById(StringUtils.toLong(id));
         addressEntity.setStreet(streetPara);
         addressEntity.setCommuneEntity(CommuneDAO.getInstance().getById(communeId));
         addressEntity.setDistrictEntity(DistrictDAO.getInstance().getById(districtId));
         addressEntity.setProvinceEntity(ProvinceDAO.getInstance().getById(provinceId));
         addressEntity.setFullName(fullName);
         addressEntity.setAddressName(addressName);
         addressEntity.setPhoneNumber(phoneNumber);

         Boolean resultAddress = AddressDAO.getInstance().update(addressEntity);
         if (resultAddress != false) {
            result += "true\n" + resultAddress;
         } else {
            result += "false\nError while insert address";
         }
      } catch (Exception e) {
         result += "false\n" + e.getMessage();
      }

      ServletUtils.printWrite(resp, result);
   }
}
