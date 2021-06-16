package com.hknp.controller.web;

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
import java.math.BigDecimal;

@WebServlet(urlPatterns = {"/product"})
public class ProductController extends HttpServlet {
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      try {
         String idPara = req.getParameter("id");
         Long productId = StringUtils.toLong(idPara);
         ProductEntity productEntity = ProductDAO.getInstance().getByIdForCustomer(productId);

         if (productEntity != null) {
            productEntity.setProductDesc(productEntity.getProductDesc().replace("\n", "<br>"));

            BigDecimal percent = productEntity.getPriceOrigin().subtract(productEntity.getPriceOrder());
            percent = percent.multiply(new BigDecimal(100));
            percent = percent.divide(productEntity.getPriceOrigin(), 0, 1);

            req.setAttribute("product", productEntity);
            req.setAttribute("percent", percent);

            ServletUtils.forward(req, resp, "/view/web/product.jsp");
         } else {
            ServletUtils.forward(req, resp, "/home");
         }
      } catch (Exception e) {
         ServletUtils.forward(req, resp, "/home");
      }
   }

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      doGet(req, resp);
   }
}
