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

@WebServlet(urlPatterns = {"/product"})
public class ProductController extends HttpServlet {
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String idPara = req.getParameter("id");
      Long productId = StringUtils.toLong(idPara);
      ProductEntity productEntity = ProductDAO.getInstance().getById(productId);

      if(productEntity != null){
         req.setAttribute("productId", productId);
         req.setAttribute("brandId", productEntity.getBrandEntity().getBrandId());
         req.setAttribute("brandName", productEntity.getBrandEntity().getBrandName());

         req.setAttribute("productName", productEntity.getProductName());

         ServletUtils.forward(req, resp,"/view/web/product.jsp");
      }
      else {
         ServletUtils.forward(req, resp,"/view/web/home.jsp");
      }
   }

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      doGet(req, resp);
   }
}
