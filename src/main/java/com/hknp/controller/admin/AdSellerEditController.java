package com.hknp.controller.admin;

import com.hknp.model.dao.AddressDAO;
import com.hknp.model.dao.SellerDAO;
import com.hknp.model.entity.AddressEntity;
import com.hknp.model.entity.SellerEntity;
import com.hknp.utils.ServletUtils;
import com.hknp.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@WebServlet(urlPatterns = {"/admin/seller/edit"})
public class AdSellerEditController extends HttpServlet {
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String sellerIdPara = req.getParameter("id");
      Long sellerId = StringUtils.toLong(sellerIdPara);
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
            ServletUtils.forward(req, resp, "/view/admin/ad-seller-edit.jsp");
            return;
         }
      }

      ServletUtils.forward(req, resp, "/admin/seller");

   }

   @Override
   protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

   }
}
