package com.hknp.controller.api;

import com.hknp.model.dao.BillDAO;
import com.hknp.model.dao.BillDetailDAO;
import com.hknp.model.dao.DeliveryDAO;
import com.hknp.model.entity.BillDetailEntity;
import com.hknp.model.entity.BillEntity;
import com.hknp.utils.DateTimeUtils;
import com.hknp.utils.MailUtils;
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
   protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String result = "";
      HttpSession session = req.getSession();
      Long id = (Long) session.getAttribute("id");
      Map<String, Object> parameterMap = ServletUtils.getParametersMap(req);

      try {
         String billIdid = (String) parameterMap.get("id");
         Integer status = (Integer) parameterMap.get("status");
         String email = (BillDAO.getInstance().getById(StringUtils.toLong(billIdid)).getCustomerEntity().getUserEntity().getEmail());
         String customerName = BillDAO.getInstance().getById(StringUtils.toLong(billIdid)).getCustomerEntity().getUserEntity().getFullName();
         if (status == 4) {
            BillEntity updateBill = BillDAO.getInstance().getById(StringUtils.toLong(billIdid));
            updateBill.setDeliveryEntity(DeliveryDAO.getInstance().getById(id));
            updateBill.setStatus(status);
            Boolean updateResult = BillDAO.getInstance().update(updateBill);
            if (updateResult != false) {
               result += "true\n" + updateBill.getDeliveryEntity().getUserId();
            } else {
               result += "false\nError while get bill";
            }
         } else if (status == 2) {
            BillEntity updateBill = BillDAO.getInstance().getById(StringUtils.toLong(billIdid));
            updateBill.setStatus(status);
            MailUtils.send(email, "Xac Nhan Don Hang", "Chào " + customerName + ", cửa hàng đã duyệt đơn hàng mang mã số " + billIdid + " của bạn! <br/> Cảm ơn bạn đã sử dụng dịch vụ của chúng tôi! <br/> Shipper sẽ giao hàng cho bạn trong thời gian sớm nhất!", "text/html");
            updateBill.setBillCreateDate(DateTimeUtils.currentDate());

            Boolean updateResult = BillDAO.getInstance().update(updateBill);

            if (updateResult != false) {
               result += "true\n" + updateBill.getDeliveryEntity().getUserId();
            } else {
               result += "false\nError while confirm bill";
            }
         } else if (status == 6) {
            BillEntity updateBill = BillDAO.getInstance().getById(StringUtils.toLong(billIdid));
            updateBill.setStatus(status);
            updateBill.setBillDoneDate(DateTimeUtils.currentDate());
            Boolean updateResult = BillDAO.getInstance().update(updateBill);
            if (updateResult != false) {
               result += "true\n" + updateBill.getDeliveryEntity().getUserId();
            } else {
               result += "false\nError while confirm bill";
            }
         } else {
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
