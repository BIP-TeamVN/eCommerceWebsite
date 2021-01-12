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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@WebServlet(urlPatterns = {"/api/product"})
public class ProductServlet extends HttpServlet {
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

      ServletUtils.printWrite(resp, "[" + String.join(", ", listJsonStr) + "]");
   }

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String result = "";
      HttpSession session = req.getSession();

      try {
         String productName = req.getParameter("product-name");
         String brandId = req.getParameter("brand-id");
         Long sellerId = (Long) session.getAttribute("id");
         String productOrigin = req.getParameter("product-origin");
         String productDesc = req.getParameter("product-desc");
         String priceOrder = req.getParameter("price-order");
         String priceOrigin = req.getParameter("price-origin");

         String productTypesReq = req.getParameter("product-types");
         String quantitiesReq = req.getParameter("quantities");
         String imagesReq = req.getParameter("images");
         String productCategoriesReq = req.getParameter("product-categories");
         List<String> productTypes = StringUtils.splitToList(productTypesReq, "@#&");
         List<String> quantities = StringUtils.splitToList(quantitiesReq, "@#&");
         List<String> images = StringUtils.splitToList(imagesReq, "@#&");
         List<String> productCategories = StringUtils.splitToList(productCategoriesReq, "@#&");

         String image0 = req.getParameter("image-0");
         String image1 = req.getParameter("image-1");
         String image2 = req.getParameter("image-2");
         String image3 = req.getParameter("image-3");
         String image4 = req.getParameter("image-4");

         ProductEntity newProduct = new ProductEntity();
         newProduct.setProductName(productName);
         newProduct.setBrandEntity(BrandDAO.getInstance().getById(StringUtils.toLong(brandId)));
         newProduct.setSellerEntity(SellerDAO.getInstance().getById(sellerId));
         newProduct.setProductOrigin(productOrigin);
         newProduct.setProductDesc(productDesc);
         newProduct.setPriceOrder(StringUtils.toBigDecimal(priceOrder));
         newProduct.setPriceOrigin(StringUtils.toBigDecimal(priceOrigin));

         newProduct.setStatus(true);
         newProduct.setProductRate(0f);

         Set<ProductTypeEntity> productTypeEntities = new HashSet<>();
         for(int i = 0; i < quantities.size(); i++) {
            ProductTypeEntity productTypeEntity = new ProductTypeEntity(productTypes.get(i), StringUtils.toInt(quantities.get(i)), images.get(i));
            productTypeEntity.setProductEntity(newProduct);
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
         if(image3 != null && !image3.isEmpty()) {
            newProduct.setImage3(image3);
         }
         if(image4 != null && !image4.isEmpty()) {
            newProduct.setImage4(image4);
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

      ServletUtils.printWrite(resp, result);
   }

   @Override
   protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

   }
}
