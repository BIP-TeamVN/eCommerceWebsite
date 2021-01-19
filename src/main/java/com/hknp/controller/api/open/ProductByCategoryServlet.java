package com.hknp.controller.api.open;

import com.hknp.model.dao.ProductDAO;
import com.hknp.model.entity.ProductEntity;
import com.hknp.utils.ServletUtils;
import com.hknp.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/api/product-by-category"})
public class ProductByCategoryServlet extends HttpServlet {
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String type = req.getParameter("type");
      Long productId = StringUtils.toLong(req.getParameter("productId"));

      List<ProductEntity> listProduct = new ArrayList<>();
      List<String> listJsonStr = new ArrayList<>();
      if (type.equals("min")) {
         listProduct = ProductDAO.getInstance().getProductByCategory(0, 4, productId);
      } else {
         Integer currentPage = StringUtils.toInt(req.getParameter("currentPage"));
         listProduct = ProductDAO.getInstance().getProductByCategory((currentPage - 1) * 12, 12, productId);
      }

      for (ProductEntity product : listProduct) {
         listJsonStr.add(product.toJson());
      }
      ServletUtils.printWrite(resp, "[" + String.join(", ", listJsonStr) + "]");
   }
}
