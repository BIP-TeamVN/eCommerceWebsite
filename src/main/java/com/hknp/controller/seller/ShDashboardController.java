package com.hknp.controller.seller;

import com.hknp.model.dao.BillDAO;
import com.hknp.model.dao.ProductCategoryDAO;
import com.hknp.model.dao.ProductDAO;
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
import java.math.BigDecimal;
import java.text.DecimalFormat;
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

      req.setAttribute("sellerId", sellerId);

      Long totalCustomer = BillDAO.getInstance().CountCustomerForSeller(sellerId);
      req.setAttribute("totalCustomer", totalCustomer);

      Long totalProduct = ProductDAO.getInstance().countProductSell(sellerId);
      req.setAttribute("totalProduct", totalProduct);

      Long totalBill = 0L;
      BigDecimal totalSale = new BigDecimal(0);
      List<BillEntity> listBill = BillDAO.getInstance()
              .getsForSeller(0, 100, sellerId, Cons.Bill.BILL_STATUS_DONE);

      for (BillEntity bill : listBill) {
         totalSale = totalSale.add(bill.getTotal());
      }
      totalBill = listBill.stream().count();

      req.setAttribute("totalSale", new DecimalFormat("###,###").format(totalSale));
      req.setAttribute("totalBill", totalBill);

      ServletUtils.forward(req, resp, "/view/seller/sh-dashboard.jsp");
   }

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      doGet(req, resp);
   }
}