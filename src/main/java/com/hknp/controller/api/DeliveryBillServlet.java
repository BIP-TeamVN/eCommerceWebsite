package com.hknp.controller.api;

import com.hknp.model.dao.BillDAO;
import com.hknp.model.dao.UserDAO;
import com.hknp.model.entity.BillEntity;
import com.hknp.model.entity.Cons;
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

@WebServlet(urlPatterns = {"/api/delivery-bill", "/api/deliverybill"})
public class DeliveryBillServlet extends HttpServlet {
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String pagePara = req.getParameter("page");
      HttpSession session = req.getSession();
      Long id = (Long) session.getAttribute("id");

      String status = req.getParameter("status");
      Integer stt = Integer.parseInt(status);

      Integer page = StringUtils.toInt(pagePara);
      if (page <= 0) {
         page = 1;
      }

      List<BillEntity> listBill = new ArrayList<>();
      List<String> listJsonStr = new ArrayList<>();

      if (UserDAO.getInstance().getById(id).getUserType().equals(Cons.User.USER_TYPE_DELIVERY)) {
         listBill = BillDAO.getInstance().gets((page - 1) * 10, 10, stt);
      } else if (UserDAO.getInstance().getById(id).getUserType().equals(Cons.User.USER_TYPE_SELLER)) {
         listBill = BillDAO.getInstance().getsForSeller((page - 1) * 10, 10, id, stt);
      } else if (UserDAO.getInstance().getById(id).getUserType().equals(Cons.User.USER_TYPE_CUSTOMER)) {
         listBill = BillDAO.getInstance().getsForCustomer((page - 1) * 10, 10, id, stt);
      }

      for (BillEntity bill : listBill) {
         listJsonStr.add(bill.toJson());
      }

      ServletUtils.printWrite(resp, "[" + String.join(", ", listJsonStr) + "]");
   }
}
