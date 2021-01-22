package com.hknp.controller.api;

import com.hknp.model.dao.BillDAO;
import com.hknp.model.entity.BillEntity;
import com.hknp.utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/api/statists"})
public class Statists extends HttpServlet {
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String type = req.getParameter("type");      // lấy xuống là tính tổng hay tính số lượng
      String year = req.getParameter("year");
      List<String> listJsonStr = new ArrayList<>();
      if (type.equals("total")) {
         for (int i = 1; i < 13; i++) {
            BigDecimal totalEachMonth = new BigDecimal(0);
            List<BillEntity> listbill = new ArrayList<>();
            String first = String.valueOf(i);
            String last = String.valueOf(i + 1);
            listbill = BillDAO.getInstance().getsByMonth(first, last, year);
            for (BillEntity bill : listbill) {
               totalEachMonth = totalEachMonth.add(bill.getTotal());
            }
            String s = "" + totalEachMonth + "";
            listJsonStr.add(s);
         }
      } else {
         for (int i = 1; i < 13; i++) {
            String first = String.valueOf(i);
            String last = String.valueOf(i + 1);
            List<BillEntity> listBill = BillDAO.getInstance().getsByMonth(first, last, year);
            String s = "" + listBill.size();
            listJsonStr.add(s);
         }
      }
      ServletUtils.printWrite(resp, "[" + String.join(", ", listJsonStr) + "]");
   }
}
