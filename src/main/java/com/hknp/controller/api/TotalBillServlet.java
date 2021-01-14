package com.hknp.controller.api;

import com.hknp.model.dao.BillDAO;
import com.hknp.model.dao.BillDetailDAO;
import com.hknp.model.dao.ProductDAO;
import com.hknp.model.dao.UserDAO;
import com.hknp.model.entity.BillDetailEntity;
import com.hknp.model.entity.BillEntity;
import com.hknp.model.entity.Cons;
import com.hknp.model.entity.ProductEntity;
import com.hknp.utils.ServletUtils;
import com.hknp.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/api/total-bill"})
public class TotalBillServlet extends HttpServlet {
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String billId = req.getParameter("id");
      Long id = StringUtils.toLong(billId);

      BigDecimal total = new BigDecimal(0);

      Integer quantity;
      BigDecimal price = new BigDecimal(0);

      BillEntity bill = new BillEntity();
      bill = BillDAO.getInstance().getById(id);
      BigDecimal discount = bill.getDiscountEntity().getDiscountMaxValue();

      List<BillDetailEntity> listBillDetail = new ArrayList<>();
      List<String> listJsonStr = new ArrayList<>();

      listBillDetail = BillDetailDAO.getInstance().gets(0, 10, id);

      for (BillDetailEntity billdetail : listBillDetail) {
         quantity = billdetail.getQuantity();
         BigDecimal q = new BigDecimal(quantity);
         price = billdetail.getProductTypeEntity().getProductEntity().getPriceOrder();
         BigDecimal tempt = q.multiply(price);
         total = total.add(tempt);
      }
      total = total.subtract(discount);

      req.setAttribute("total", total);
      req.setAttribute("discount", discount);

      ServletUtils.printWrite(resp, "[" + total + " " + discount +"]");
   }
}
