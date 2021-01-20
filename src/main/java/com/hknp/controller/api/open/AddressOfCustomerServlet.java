package com.hknp.controller.api.open;

import com.hknp.model.dao.AddressDAO;
import com.hknp.model.dao.UserDAO;
import com.hknp.model.entity.AddressEntity;
import com.hknp.model.entity.UserEntity;
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
      try {
         HttpSession session = req.getSession(false);
         Long userId = (Long) session.getAttribute("id");

         List<AddressEntity> addressEntities = UserDAO.getInstance().getById(userId).getAddressEntities();
         List<String> listJsonStr = new ArrayList<>();
         for (AddressEntity a: addressEntities) {
            listJsonStr.add(a.toJson1());
         }
         ServletUtils.printWrite(resp, "[" + String.join(", ", listJsonStr) + "]");
      } catch (Exception e) {

      }
   }

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String result = "";

      try {
         String streetPara = req.getParameter("street");
         String communeId = req.getParameter("commune");
         String districtId = req.getParameter("district");
         String provinceId = req.getParameter("province");
         String fullName = req.getParameter("fullName");
         String addressName = req.getParameter("addressName");
         String phoneNumber = req.getParameter("phoneNumber");

         HttpSession session = req.getSession();
         Long userId = (Long) session.getAttribute("id");

         AddressEntity addressEntity = new AddressEntity(streetPara, communeId, districtId, provinceId, userId, fullName, addressName, phoneNumber);
         Long resultAddress = AddressDAO.getInstance().insert(addressEntity);
         if (resultAddress != 0) {
            addressEntity.setAddressId(resultAddress);
            UserEntity userEntity = UserDAO.getInstance().getById(userId);
            List<AddressEntity> addressEntityList = userEntity.getAddressEntities();
            addressEntityList.add(addressEntity);
            userEntity.setAddressEntities(addressEntityList);
            Boolean resultUser = UserDAO.getInstance().update(userEntity);
            if (resultUser != false) {
               result += "true\n" + resultAddress;
            } else {
               result += "false\nError while update user";
            }
         } else {
            result += "false\nError while insert address";
         }
      }
      catch (Exception e) {
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
         String fullName = (String) parameterMap.get("fullName");
         String addressName = (String) parameterMap.get("addressName");
         String phoneNumber = (String) parameterMap.get("phoneNumber");

         HttpSession session = req.getSession();
         Long userId = (Long) session.getAttribute("id");

         AddressEntity addressEntity = new AddressEntity(streetPara, communeId, districtId, provinceId, userId, fullName, addressName, phoneNumber);
         addressEntity.setUserId(StringUtils.toLong(id));
         Boolean resultAddress = AddressDAO.getInstance().update(addressEntity);
         if (resultAddress != false) {
            result += "true\n" + resultAddress;
         } else {
            result += "false\nError while insert address";
         }
      }
      catch (Exception e) {
         result += "false\n" + e.getMessage();
      }

      ServletUtils.printWrite(resp, result);
   }
}
