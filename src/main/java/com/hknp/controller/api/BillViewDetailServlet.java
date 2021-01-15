package com.hknp.controller.api;

import com.hknp.model.dao.*;
import com.hknp.model.entity.*;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = {"/api/bill/view/detail"})
public class BillViewDetailServlet extends HttpServlet {
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String pagePara = req.getParameter("page");
      String billId = req.getParameter("id");
      Long id = StringUtils.toLong(billId);
      Integer page = StringUtils.toInt(pagePara);
      if (page <= 0) {
         page = 1;
      }

      List<BillDetailEntity> listBillDetail = new ArrayList<>();
      List<String> listJsonStr = new ArrayList<>();

      listBillDetail = BillDetailDAO.getInstance().gets((page - 1) * 10, 10, id);

      for (BillDetailEntity billdetail : listBillDetail) {
         listJsonStr.add(billdetail.toJson());
      }

      ServletUtils.printWrite(resp, "[" + String.join(", ", listJsonStr) + "]");
   }
   @Override
   protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
      String result = "";
      HttpSession session = req.getSession();
      Long deliveryId = (Long) session.getAttribute("id");
      Map<String, Object> parameterMap = ServletUtils.getParametersMap(req);

      try {
         String billIdid = (String) parameterMap.get("id");
         Integer status = (Integer) parameterMap.get("status");
         if(status == 3){
            BillEntity updateBill = BillDAO.getInstance().getById(StringUtils.toLong(billIdid));
            updateBill.setDeliveryEntity(DeliveryDAO.getInstance().getById(deliveryId));
            updateBill.setStatus(status);
            Boolean updateResult = BillDAO.getInstance().update(updateBill);

            if (updateResult != false) {
               result += "true\n" + updateBill.getDeliveryEntity().getUserId();
            } else {
               result += "false\nError while get bill";
            }
         }
         else {
            BillEntity updateBill = BillDAO.getInstance().getById(StringUtils.toLong(billIdid));
            updateBill.setStatus(status);
            Boolean updateResult = BillDAO.getInstance().update(updateBill);

            if (updateResult != false) {
               result += "true\n" + updateBill.getDeliveryEntity().getUserId();
            } else {
               result += "false\nError while confirm bill";
            }
         }
      } catch (Exception e) {
         result += "false\n" + e.getMessage();
      }

      ServletUtils.printWrite(resp, result);
   }
}
