package com.hknp.controller.admin;

import com.hknp.model.dao.*;
import com.hknp.model.entity.BillEntity;
import com.hknp.model.entity.UserEntity;
import com.hknp.utils.DateTimeUtils;
import com.hknp.utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
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

      List<BillEntity> listBill =  new ArrayList<>();
      listBill = BillDAO.getInstance().gets();
      for (BillEntity bill : listBill){
         totalSale = totalSale.add(bill.getTotal());
      }
      req.setAttribute("totalSale", totalSale);

      for(int i =1 ; i< 12;i++){
         BigDecimal totalEachMonth = new BigDecimal(0);
         List<BillEntity> listbill =  new ArrayList<>();
         String first =  String.valueOf(i);
         String last = String.valueOf(i+1);
         listbill = BillDAO.getInstance().getsByMonth(first, last);
         Long countBillByMonth = listbill.stream().count();
         for (BillEntity bill : listbill){
            totalEachMonth = totalEachMonth.add(bill.getTotal());
         }
         //req.setAttribute();// tổng tiền mỗi tháng
         //req.setAttribute();// tổng số bill mỗi tháng
      }

      ServletUtils.forward(req, resp, "/view/admin/ad-dashboard.jsp");
   }

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      doGet(req, resp);
   }
}