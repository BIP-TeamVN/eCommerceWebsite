package com.hknp.controller.delivery;

import com.hknp.model.dao.BillDAO;
import com.hknp.model.dao.BillDetailDAO;
import com.hknp.model.entity.BillDetailEntity;
import com.hknp.model.entity.BillEntity;
import com.hknp.utils.ServletUtils;
import com.hknp.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/delivery/detailbill"})
public class DeliveryViewBillController extends HttpServlet {
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      Long totalRows = BillDAO.getInstance().count();

      String page = req.getParameter("page");

      String id = req.getParameter("id");
      Long billId = StringUtils.toLong(id);

      Long currentPage = StringUtils.toLong(page);
      Long totalPage = (totalRows / 10) + ((totalRows % 10 == 0) ? 0 : 1);

      if (currentPage > totalPage) {
         currentPage = totalPage;
      }
      if (currentPage < 1) {
         currentPage = 1L;
      }


      BigDecimal total = new BigDecimal(0);

      Integer quantity;
      BigDecimal price = new BigDecimal(0);

      BillEntity bill = new BillEntity();
      bill = BillDAO.getInstance().getById(billId);
      BigDecimal discount = bill.getDiscountEntity().getDiscountMaxValue();

      List<BillDetailEntity> listBillDetail = new ArrayList<>();
      List<String> listJsonStr = new ArrayList<>();

      listBillDetail = BillDetailDAO.getInstance().gets(0, 10, billId);

      for (BillDetailEntity billdetail : listBillDetail) {
         quantity = billdetail.getQuantity();
         BigDecimal q = new BigDecimal(quantity);
         price = billdetail.getProductTypeEntity().getProductEntity().getPriceOrder();
         BigDecimal tempt = q.multiply(price);
         total = total.add(tempt);
      }
      total = total.subtract(discount);

      req.setAttribute("total", new DecimalFormat("###,###").format(total));
      req.setAttribute("discount", new DecimalFormat("###,###").format(discount));

      req.setAttribute("totalPage", totalPage);
      req.setAttribute("currentPage", currentPage);

      req.setAttribute("billId", id);
      req.setAttribute("status", bill.getStatus());

      if (bill.getStatus() == 0) {
         ServletUtils.forward(req, resp, "/view/seller/sh-bill-waiting-accept.jsp");
      } else {
         ServletUtils.forward(req, resp, "/view/seller/sh-bill-detail.jsp");
      }
   }

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      doGet(req, resp);
   }
}