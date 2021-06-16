package com.hknp.controller.admin;

import com.hknp.model.dao.AddressDAO;
import com.hknp.model.dao.DeliveryDAO;
import com.hknp.model.entity.AddressEntity;
import com.hknp.model.entity.DeliveryEntity;
import com.hknp.utils.ServletUtils;
import com.hknp.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@WebServlet(urlPatterns = {"/admin/delivery/edit"})
public class AdDeliveryEditController extends HttpServlet {

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String deliveryIdPara = req.getParameter("id");
      Long deliveryId = StringUtils.toLong(deliveryIdPara);
      DeliveryEntity deliveryEdit = null;

      if (deliveryId != 0) {
         deliveryEdit = DeliveryDAO.getInstance().getById(deliveryId);
         if (deliveryEdit != null) {
            if (deliveryEdit.getUserEntity().getAddressEntities().size() == 0) {
               AddressEntity newAddress = new AddressEntity();
               newAddress.setUserId(deliveryId);
               AddressDAO.getInstance().insert(newAddress);
               deliveryEdit.getUserEntity().setAddressEntities(Collections.singletonList(newAddress));
               DeliveryDAO.getInstance().update(deliveryEdit);
            }
            req.setAttribute("deliveryEdit", deliveryEdit);
            ServletUtils.forward(req, resp, "/view/admin/ad-delivery-edit.jsp");
            return;
         }
      }

      ServletUtils.forward(req, resp, "/admin/delivery");
   }
}
