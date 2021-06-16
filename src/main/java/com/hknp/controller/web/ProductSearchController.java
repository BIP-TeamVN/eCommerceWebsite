package com.hknp.controller.web;

import com.hknp.model.dao.BrandDAO;
import com.hknp.model.dao.ProductCategoryDAO;
import com.hknp.model.dao.ProductDAO;
import com.hknp.model.dao.SellerDAO;
import com.hknp.model.entity.BrandEntity;
import com.hknp.model.entity.ProductCategoryEntity;
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

@WebServlet(urlPatterns = {"/product-search"})
public class ProductSearchController extends HttpServlet {
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      try {
         //Load category
         String[] categories1;
         String[] categories2;

         try {
            String categoriesPara = req.getParameter("categories");
            categories1 = categoriesPara.split("@,a");
         } catch (Exception e) {
            categories1 = new String[0];
         }
         try {
            String productIdPara = req.getParameter("product");
            List<ProductCategoryEntity> categoryEntities = new ArrayList<>(ProductDAO.getInstance().getById(StringUtils.toLong(productIdPara)).getProductCategoryEntities());
            categories2 = new String[categoryEntities.size()];
            for (int i = 0; i < categoryEntities.size(); i++) {
               categories2[i] = categoryEntities.get(i).getProductCategoryId().toString();
            }
         } catch (Exception e) {
            categories2 = new String[0];
         }

         int len1 = categories1.length;
         int len2 = categories2.length;
         String[] categories = new String[len1 + len2];

         System.arraycopy(categories1, 0, categories, 0, len1);
         System.arraycopy(categories2, 0, categories, len1, len2);

         String resultCategories = "";
         ArrayList<ProductCategoryEntity> productCategoryEntities = ProductCategoryDAO.getInstance().gets();

         Boolean temp = false;
         if (categories != null) {
            for (ProductCategoryEntity c : productCategoryEntities) {
               temp = false;
               for (String s : categories) {
                  if (c.getProductCategoryId() == StringUtils.toLong(s)) {
                     resultCategories += "<option selected value=\"" + c.getProductCategoryId() + "\">" + c.getProductCategoryName() + "</option>";
                     temp = true;
                     break;
                  }
               }
               if (temp == false) {
                  resultCategories += "<option value=\"" + c.getProductCategoryId() + "\">" + c.getProductCategoryName() + "</option>";
               }
            }
         } else {
            for (ProductCategoryEntity c : productCategoryEntities) {
               resultCategories += "<option value=\"" + c.getProductCategoryId() + "\">" + c.getProductCategoryName() + "</option>";
            }
         }
         req.setAttribute("categories", resultCategories.replace("'", "\\'"));

         //Load brand
         String[] brands;
         try {
            String brandsPara = req.getParameter("brands");
            brands = brandsPara.split("@,a");
         } catch (Exception e) {
            brands = null;
         }

         String resultBrands = "";
         ArrayList<BrandEntity> brandEntities = BrandDAO.getInstance().gets();

         if (brands != null) {
            for (BrandEntity b : brandEntities) {
               temp = false;
               for (String s : brands) {
                  if (b.getBrandId() == StringUtils.toLong(s)) {
                     resultBrands += "<option selected value=\"" + b.getBrandId() + "\">" + b.getBrandName() + "</option>";
                     temp = true;
                     break;
                  }
               }
               if (temp == false) {
                  resultBrands += "<option value=\"" + b.getBrandId() + "\">" + b.getBrandName() + "</option>";
               }
            }
         } else {
            for (BrandEntity b : brandEntities) {
               resultBrands += "<option value=\"" + b.getBrandId() + "\">" + b.getBrandName() + "</option>";
            }
         }
         req.setAttribute("brands", resultBrands.replace("'", "\\'"));

         //load shop
         String shopName;
         try {
            String shopIdPara = req.getParameter("shop");
            shopName = SellerDAO.getInstance().getById(StringUtils.toLong(shopIdPara)).getStoreName();
         } catch (Exception e) {
            shopName = "";
         }
         req.setAttribute("shopName", shopName.replace("'", "\\'"));

         String keyword;
         try {
            keyword = req.getParameter("keyword");
         } catch (Exception e) {
            keyword = "";
         }
         if (keyword == null) {
            keyword = "";
         }
         req.setAttribute("keyword", keyword.replace("'", "\\'"));

         ServletUtils.forward(req, resp, "/view/web/product-search.jsp");
      } catch (Exception e) {
         ServletUtils.forward(req, resp, "/home");
      }
   }

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      doGet(req, resp);
   }
}