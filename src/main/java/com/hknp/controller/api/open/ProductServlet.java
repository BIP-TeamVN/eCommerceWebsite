package com.hknp.controller.api.open;

import com.hknp.model.dao.ProductDAO;
import com.hknp.model.dao.UserDAO;
import com.hknp.model.entity.Cons;
import com.hknp.model.entity.ProductEntity;
import com.hknp.utils.ServletUtils;
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
      String pagePara = req.getParameter("page");                    //trang hiện tại

      String keyword = req.getParameter("keyword").trim();           //từ khóa cần tìm kiếm
      String columnName = req.getParameter("columnName");            //tên cột cần sắp xếp thứ tự
      String typeSort = req.getParameter("typeSort");                //kiêu sắp xếp
      if (keyword == null) {
         keyword = "";
      }
      Integer page = StringUtils.toInt(pagePara);
      if (page <= 0) {
         page = 1;
      }

      List<ProductEntity> listProduct = new ArrayList<>();
      List<String> listJsonStr = new ArrayList<>();

      listProduct = ProductDAO.getInstance().gets((page - 1) * 10, 10, 1, keyword, columnName, typeSort);

      for (ProductEntity product : listProduct) {
         listJsonStr.add(product.toJson());
      }

      ServletUtils.printWrite(resp, "[" + String.join(", ", listJsonStr) + "]");
   }
}
