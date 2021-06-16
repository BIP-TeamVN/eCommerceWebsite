package com.hknp.controller.seller;

import com.hknp.model.dao.ProductDAO;
import com.hknp.model.entity.ProductEntity;
import com.hknp.utils.ServletUtils;
import com.hknp.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/seller/product/edit"})
public class ShProductEditController extends HttpServlet {
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String productIdPara = req.getParameter("id");
      Long productId = StringUtils.toLong(productIdPara);
      ProductEntity productEntity = null;

      HttpSession session = req.getSession();
      Long sellerId = (Long) session.getAttribute("id");

      if (productId != 0) {
         productEntity = ProductDAO.getInstance().getByIdAndSeller(productId, sellerId);
         if (productEntity != null) {
            req.setAttribute("productEntity", productEntity);
            ServletUtils.forward(req, resp, "/view/seller/sh-product-edit.jsp");
            return;
         }
      }

      ServletUtils.forward(req, resp, "/seller/product");
   }

   @Override
   protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

   }
}