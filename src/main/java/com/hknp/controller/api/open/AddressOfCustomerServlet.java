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
            listJsonStr.add(a.toJson());
         }
         ServletUtils.printWrite(resp, "[" + String.join(", ", listJsonStr) + "]");
      } catch (Exception e) {

      }
   }

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

   }
}
