package com.hknp.controller.seller;

import com.hknp.model.dao.BillDAO;
import com.hknp.model.dao.ProductCategoryDAO;
import com.hknp.model.dao.ProductDAO;
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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/seller"})
public class ShDashboardController extends HttpServlet {
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      Long totalRows = ProductCategoryDAO.getInstance().count();
      String page = req.getParameter("page");

      Long currentPage = StringUtils.toLong(page);
      Long totalPage = (totalRows / 10) + ((totalRows % 10 == 0) ? 0 : 1);

      if (currentPage > totalPage) {
         currentPage = totalPage;
      }
      if (currentPage < 1) {
         currentPage = 1L;
      }

      req.setAttribute("totalPage", totalPage);
      req.setAttribute("currentPage", currentPage);

      HttpSession session = req.getSession();
      Long sellerId = (Long) session.getAttribute("id");

      Long totalCustomer = BillDAO.getInstance().CountCustomerForSeller(sellerId);
      req.setAttribute("totalCustomer", totalCustomer);

      Long totalProduct = ProductDAO.getInstance().countProductSell(sellerId);
      req.setAttribute("totalProduct", totalProduct);

      Long totalBill = 0L;
      BigDecimal totalSale = new BigDecimal(0);
      List<BillEntity> listBill = new ArrayList<>();
      listBill = BillDAO.getInstance().getsForSeller(1,100,sellerId,6);
      for (BillEntity bill : listBill){
         totalSale = totalSale.add(bill.getTotal());
      }
      totalBill = listBill.stream().count();
      req.setAttribute("totalSale", totalSale);
      req.setAttribute("totalBill", totalBill);
      for(int i = 1;i<13;i++){
         BigDecimal totalEachMonth = new BigDecimal(0);
         List<BillEntity> listbill =  new ArrayList<>();
         String first =  String.valueOf(i);
         String last = String.valueOf(i+1);
         listbill = BillDAO.getInstance().getsForSumByMonth(first, last, sellerId);
         Long countBillByMonth = listbill.stream().count();
         for (BillEntity bill : listbill){
            totalEachMonth = totalEachMonth.add(bill.getTotal());
         }
         req.setAttribute("T"+i +"", totalEachMonth);
         req.setAttribute("t"+i +"", countBillByMonth);
      }



      ServletUtils.forward(req, resp, "/view/seller/sh-dashboard.jsp");
   }

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      doGet(req, resp);
   }
}