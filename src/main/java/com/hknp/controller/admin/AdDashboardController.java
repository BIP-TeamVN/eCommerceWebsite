package com.hknp.controller.admin;

import com.hknp.model.dao.*;
import com.hknp.model.entity.BillEntity;
import com.hknp.model.entity.UserEntity;
import com.hknp.utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet(urlPatterns = {"/admin"})
public class AdDashboardController extends HttpServlet {
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      HttpSession session = req.getSession(true);
      Long id = (Long) session.getAttribute("id");
      if (id != null) {
         UserEntity user = UserDAO.getInstance().getById(id);
         req.setAttribute("id", id);
      }
      
      Long totalCustomer = (Long) CustomerDAO.getInstance().count();
      req.setAttribute("totalCustomer", totalCustomer);

      Long totalEmployee = (Long) EmployeeDAO.getInstance().count();
      req.setAttribute("totalEmployee", totalEmployee);

      Long totalSeller = (Long) SellerDAO.getInstance().count();
      req.setAttribute("totalSeller", totalSeller);

      BigDecimal totalSale = new BigDecimal(0);
      List<BillEntity> listBill = BillDAO.getInstance().gets();
      if (listBill != null) {
         for (BillEntity bill : listBill) {
            totalSale = totalSale.add(bill.getTotal());
         }
      }

      req.setAttribute("totalSale", totalSale);

      ServletUtils.forward(req, resp, "/view/admin/ad-dashboard.jsp");
   }

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      doGet(req, resp);
   }
}