package com.hknp.controller.api.open;

import com.hknp.model.dao.ProductDAO;
import com.hknp.model.dao.UserDAO;
import com.hknp.model.entity.Cons;
import com.hknp.model.entity.ProductEntity;
import com.hknp.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ProductServlet extends HttpServlet {
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      resp.setContentType("text/html; charset=UTF-8");

      String pagePara = req.getParameter("page");
      HttpSession session = req.getSession();
      Long id = (Long) session.getAttribute("id");

      Integer page = StringUtils.toInt(pagePara);
      if (page <= 0) {
         page = 1;
      }

      List<ProductEntity> listProduct = new ArrayList<>();
      List<String> listJsonStr = new ArrayList<>();

      if (UserDAO.getInstance().getById(id).getUserType().equals(Cons.User.USER_TYPE_SELLER)) {
         listProduct = ProductDAO.getInstance().gets((page - 1) * 10, 10, id);
      }
      else {
         listProduct = ProductDAO.getInstance().gets((page - 1) * 10, 10);
      }

      for (ProductEntity product : listProduct) {
         listJsonStr.add(product.toJson());
      }

      try (PrintWriter out = resp.getWriter()) {
         out.write("[" + String.join(", ", listJsonStr) + "]");
      }
   }
}
