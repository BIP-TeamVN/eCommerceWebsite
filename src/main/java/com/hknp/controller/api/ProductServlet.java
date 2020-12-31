package com.hknp.controller.api;

import com.hknp.model.dao.*;
import com.hknp.model.entity.*;
import com.hknp.utils.ServletUtils;
import com.hknp.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@WebServlet(urlPatterns = {"/api/product"})
public class ProductServlet extends HttpServlet {
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      resp.setContentType("text/html; charset=UTF-8");

      String pagePara = req.getParameter("page");
      Integer page = StringUtils.toInt(pagePara);
      if (page <= 0) {
         page = 1;
      }

      ArrayList<ProductEntity> listProduct = ProductDAO.getInstance().gets((page - 1) * 10, 10);
      List<String> listJsonStr = new ArrayList<>();

      for (ProductEntity product : listProduct) {
         listJsonStr.add(product.toJson());
      }

      try (PrintWriter out = resp.getWriter()) {
         out.write("[" + String.join(", ", listJsonStr) + "]");
      }
   }

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      resp.setContentType("text/html; charset=UTF-8");
      String result = "";

      try {
         String productName = req.getParameter("product-name");
         String brandId = req.getParameter("brand-id");
         String sellerId = req.getParameter("seller-id");
         String productOrigin = req.getParameter("product-origin");
         String productDesc = req.getParameter("product-desc");
         String priceOrder = req.getParameter("price-order");
         String priceOrigin = req.getParameter("price-origin");
         String[] productTypes = req.getParameterValues("product-types");
         String[] productCategories = req.getParameterValues("product-categories");

         String image0 = req.getParameter("image0");
         String image1 = req.getParameter("image1");
         String image2 = req.getParameter("image2");

         ProductEntity newProduct = new ProductEntity();
         newProduct.setProductName(productName);
         newProduct.setBrandEntity(BrandDAO.getInstance().getById(StringUtils.toLong(brandId)));
         newProduct.setSellerEntity(SellerDAO.getInstance().getById(StringUtils.toLong(sellerId)));
         newProduct.setProductOrigin(productOrigin);
         newProduct.setProductDesc(productDesc);
         newProduct.setPriceOrder(StringUtils.toBigDecimal(priceOrder));
         newProduct.setPriceOrigin(StringUtils.toBigDecimal(priceOrigin));

         Set<ProductTypeEntity> productTypeEntities = new HashSet<>();
         for(String type: productTypes) {
            ProductTypeEntity productTypeEntity = new ProductTypeEntity(type);
            productTypeEntities.add(productTypeEntity);
         }
         newProduct.setProductTypeEntities(productTypeEntities);

         Set<ProductCategoryEntity> productCategoryEntities = new HashSet<>();
         for(String id: productCategories) {
            ProductCategoryEntity productCategoryEntity = ProductCategoryDAO.getInstance().getById(StringUtils.toLong(id));
            productCategoryEntities.add(productCategoryEntity);
         }
         newProduct.setProductCategoryEntities(productCategoryEntities);

         if(image0 != null && !image0.isEmpty()) {
            newProduct.setImage0(image0);
         }
         if(image1 != null && !image1.isEmpty()) {
            newProduct.setImage1(image1);
         }
         if(image2 != null && !image2.isEmpty()) {
            newProduct.setImage2(image2);
         }

         Long newResult = ProductDAO.getInstance().insert(newProduct);

         if (newResult != 0) {
            result += "true\n" + newProduct.getProductId().toString();
         } else {
            result += "false\nError while insert product";
         }
      } catch (Exception e) {
         result += "false\n" + e.getMessage();
      }

      try (PrintWriter out = resp.getWriter()) {
         out.write(result);
      }
   }

   @Override
   protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

   }
}
