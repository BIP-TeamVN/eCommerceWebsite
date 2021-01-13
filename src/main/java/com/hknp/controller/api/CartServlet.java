package com.hknp.controller.api;

import com.hknp.model.dao.BillDAO;
import com.hknp.model.dao.BillDetailDAO;
import com.hknp.model.dao.BrandDAO;
import com.hknp.model.dao.UserDAO;
import com.hknp.model.entity.*;
import com.hknp.utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/api/carts"})
public class CartServlet extends HttpServlet {

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      HttpSession session = req.getSession(true);
      Long id = (Long) session.getAttribute("id");

      id = 10L;

      UserEntity user = null;
      if (id != null) {

         user = UserDAO.getInstance().getById(id);

         if (user.getUserType().equals(Cons.User.USER_TYPE_CUSTOMER)) {

            //Lấy được danh sách Bill chưa thanh toán của người này.

            ArrayList<BillEntity> listBill = BillDAO.getInstance().gets(0, 10, 0, user.getUserId());

            //Lấy ra tất cả Bill Detail chưa thanh toán của người này.

            ArrayList<BillDetailEntity> listBillDetailFull = null;

            for (BillEntity bill : listBill) {
               ArrayList<BillDetailEntity> listBillDetail = BillDetailDAO.getInstance().gets(0, 10, bill.getBillId());
               listBillDetailFull.addAll(listBillDetail);
            }
            List<String> listJsonStr = new ArrayList<>();

            for (BillDetailEntity billDetail : listBillDetailFull) {
               String str = "{" +
                       "\"productTypeId\":\"" + billDetail.getProductEntity().getProductId() + "\"," +
                       "\"quantity\":\"" + billDetail.getQuantity() + "\"" +
                       "}";
               listJsonStr.add(str);
            }
            ServletUtils.printWrite(resp, "[" + String.join(", ", listJsonStr) + "]");

         }
      }
      ServletUtils.printWrite(resp, "Accsdfgdf");
   }
}
