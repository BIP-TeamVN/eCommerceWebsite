package com.hknp.controller.api;

import com.hknp.model.dao.BillDAO;
import com.hknp.model.entity.BillEntity;
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

@WebServlet(urlPatterns = {"/api/delivery-bill-delivering", "/api/deliverybilldelivering"})
public class DeliveryBillDeliveringServlet extends HttpServlet {
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String pagePara = req.getParameter("page");
      HttpSession session = req.getSession();
      Long id = (Long) session.getAttribute("id");
      String type = req.getParameter("type");
      Integer Type = StringUtils.toInt(type);

      Integer page = StringUtils.toInt(pagePara);
      if (page <= 0) {
         page = 1;
      }
      List<BillEntity> listBill = new ArrayList<>();
      List<String> listJsonStr = new ArrayList<>();
      if (Type == 2) {
         listBill = BillDAO.getInstance().gets((page - 1) * 10, 10, Type);
      } else {
         listBill = BillDAO.getInstance().getsForDelivery((page - 1) * 10, 10, id, Type);
      }

      for (BillEntity bill : listBill) {
         listJsonStr.add(bill.toJson());
      }

      ServletUtils.printWrite(resp, "[" + String.join(", ", listJsonStr) + "]");
   }
}
