package com.hknp.controller.web;

import com.hknp.model.dao.AddressDAO;
import com.hknp.model.dao.UserDAO;
import com.hknp.model.entity.AddressEntity;
import com.hknp.model.entity.UserEntity;
import com.hknp.utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collections;

@WebServlet(urlPatterns = {"/info/edit"})
public class InfoEditOfCustomerController extends HttpServlet {
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      try {
         HttpSession session = req.getSession(false);
         Long userId = (Long) session.getAttribute("id");
         UserEntity customerEdit = null;

         if (userId != 0) {
            customerEdit = UserDAO.getInstance().getById(userId);
            if (customerEdit != null) {
               if (customerEdit.getAddressEntities().size() == 0) {
                  AddressEntity newAddress = new AddressEntity();
                  newAddress.setUserId(userId);
                  AddressDAO.getInstance().insert(newAddress);
                  customerEdit.setAddressEntities(Collections.singletonList(newAddress));
                  UserDAO.getInstance().update(customerEdit);
               }
               req.setAttribute("customerEdit", customerEdit);
               ServletUtils.forward(req, resp, "/view/web/info.jsp");
               return;
            }
         }
      } catch (Exception e) {
         ServletUtils.forward(req, resp, "/info/address");
      }
   }
}
