package com.hknp.controller.admin;

import com.hknp.model.dao.ProductCategoryDAO;
import com.hknp.model.entity.ProductCategoryEntity;
import com.hknp.utils.ServletUtils;
import com.hknp.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/admin/product-category/edit"})
public class AdProductCategoryEditController extends HttpServlet {

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

      String productCategoryIdPara = req.getParameter("id");
      Long productCategoryId = StringUtils.toLong(productCategoryIdPara);
      ProductCategoryEntity productCategoryEdit = null;

      if (productCategoryId != 0) {
         productCategoryEdit = ProductCategoryDAO.getInstance().getById(productCategoryId);
         if (productCategoryEdit != null) {
            req.setAttribute("productCategoryEdit", productCategoryEdit);
            ServletUtils.forward(req, resp, "/view/admin/ad-product-category-edit.jsp");
            return;
         }
      }

      ServletUtils.forward(req, resp, "/admin/category");
   }
}
