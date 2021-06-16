package com.hknp.controller.delivery;

import com.hknp.model.dao.DeliveryDAO;
import com.hknp.model.entity.DeliveryEntity;
import com.hknp.utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/delivery/info"})
public class DeliveryInfoController extends HttpServlet {
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      HttpSession session = req.getSession();
      Long deliveryId = (Long) session.getAttribute("id");
      DeliveryEntity deliveryEntity = null;

      if (deliveryId != 0) {
         deliveryEntity = DeliveryDAO.getInstance().getById(deliveryId);
         if (deliveryEntity != null) {
            req.setAttribute("deliveryEntity", deliveryEntity);
            ServletUtils.forward(req, resp, "/view/delivery/dh-info.jsp");
            return;
         }
      }

      ServletUtils.forward(req, resp, "/view/delivery/dh-info.jsp");
   }

   @Override
   protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

   }
}
