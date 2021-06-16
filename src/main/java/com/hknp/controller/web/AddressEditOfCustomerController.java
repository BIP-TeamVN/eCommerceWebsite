package com.hknp.controller.web;

import com.hknp.model.dao.AddressDAO;
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

@WebServlet(urlPatterns = {"/info/address/edit"})
public class AddressEditOfCustomerController extends HttpServlet {
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      try {
         HttpSession session = req.getSession(false);
         Long userId = (Long) session.getAttribute("id");
         Long addressId = StringUtils.toLong(req.getParameter("id"));

         AddressEntity addressEntity = AddressDAO.getInstance().getById(addressId);
         if ((addressEntity.getUserId() - userId) == 0) {
            req.setAttribute("address", addressEntity);
            ServletUtils.forward(req, resp, "/view/web/customer-edit-address.jsp");
         } else {
            ServletUtils.forward(req, resp, "/info/address");
         }
      } catch (Exception e) {
         ServletUtils.forward(req, resp, "/info/address");
      }
   }
}
