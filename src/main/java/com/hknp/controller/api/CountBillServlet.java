package com.hknp.controller.api;

import com.hknp.model.dao.BillDAO;
import com.hknp.model.dao.UserDAO;
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

@WebServlet(urlPatterns = {"/api/count-bill-count"})
public class CountBillServlet extends HttpServlet {
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      HttpSession session = req.getSession();
      Long id = (Long) session.getAttribute("id");
      Integer status = StringUtils.toInt(req.getParameter("status"));
      String userType = UserDAO.getInstance().getUserType(id);
      Long totalRows;

      if (userType.equals(Cons.User.USER_TYPE_SELLER)) {
         totalRows = BillDAO.getInstance().count(id, status);
      } else if (userType.equals(Cons.User.USER_TYPE_DELIVERY)) {
         totalRows = BillDAO.getInstance().countForShipper(id, status);
      } else {
         totalRows = BillDAO.getInstance().countForCustommer(id, status);
      }

      String page = req.getParameter("page");

      Long currentPage = StringUtils.toLong(page);
      Long totalPage = (totalRows / 10) + ((totalRows % 10 == 0) ? 0 : 1);

      if (currentPage > totalPage) {
         currentPage = totalPage;
      }
      if (currentPage < 1) {
         currentPage = 1L;
      }

      ServletUtils.printWrite(resp, totalPage + "," + currentPage);
   }
}
