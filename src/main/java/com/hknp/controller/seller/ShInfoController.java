package com.hknp.controller.seller;

import com.hknp.model.dao.AddressDAO;
import com.hknp.model.dao.SellerDAO;
import com.hknp.model.entity.AddressEntity;
import com.hknp.model.entity.SellerEntity;
import com.hknp.utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collections;

@WebServlet(urlPatterns = {"/seller/edit"})
public class ShInfoController extends HttpServlet {
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      HttpSession session = req.getSession();
      Long sellerId = (Long) session.getAttribute("id");
      SellerEntity sellerEdit = null;
      if (sellerId != 0) {
         sellerEdit = SellerDAO.getInstance().getById(sellerId);
         if (sellerEdit != null) {
            if (sellerEdit.getUserEntity().getAddressEntities().size() == 0) {
               AddressEntity newAddress = new AddressEntity();
               newAddress.setUserId(sellerId);
               AddressDAO.getInstance().insert(newAddress);
               sellerEdit.getUserEntity().setAddressEntities(Collections.singletonList(newAddress));
               SellerDAO.getInstance().update(sellerEdit);
            }
            req.setAttribute("sellerEdit", sellerEdit);
            ServletUtils.forward(req, resp, "/view/seller/sh-info.jsp");
            return;
         }
      }

      ServletUtils.forward(req, resp, "/view/seller/sh-info.jsp");

   }

   @Override
   protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

   }
}
