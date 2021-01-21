package com.hknp.controller.api;

import com.hknp.model.dao.*;
import com.hknp.model.entity.Cons;
import com.hknp.model.entity.ProductCategoryEntity;
import com.hknp.model.entity.ProductEntity;
import com.hknp.model.entity.ProductTypeEntity;
import com.hknp.utils.DateTimeUtils;
import com.hknp.utils.ServletUtils;
import com.hknp.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@WebServlet(urlPatterns = {"/api/product"})
public class ProductServlet extends HttpServlet {
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String pagePara = req.getParameter("page");

      HttpSession session = req.getSession();
      Long id = (Long) session.getAttribute("id");
      Integer status = StringUtils.toInt(req.getParameter("status"));
      String keyword = req.getParameter("keyword").trim();
      String columnName = req.getParameter("columnName");
      String typeSort = req.getParameter("typeSort");
      if (keyword == null) {
         keyword = "";
      }
      Integer page = StringUtils.toInt(pagePara);
      if (page <= 0) {
         page = 1;
      }

      List<ProductEntity> listProduct = new ArrayList<>();
      List<String> listJsonStr = new ArrayList<>();

      if (UserDAO.getInstance().getById(id).getUserType().equals(Cons.User.USER_TYPE_SELLER)) {
         listProduct = ProductDAO.getInstance().gets((page - 1) * 10, 10, id, status, keyword, columnName, typeSort);
      } else {
         listProduct = ProductDAO.getInstance().gets((page - 1) * 10, 10, status, keyword, columnName, typeSort);
      }

      for (ProductEntity product : listProduct) {
         listJsonStr.add(product.toJson());
      }

      ServletUtils.printWrite(resp, "[" + String.join(", ", listJsonStr) + "]");
   }

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String result = "";
      HttpSession session = req.getSession(false);

      if (isAuthentication(req)) {
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

            newProduct.setStatus(Cons.Product.PRODUCT_STATUS_CREATE);
            newProduct.setProductRate(0f);
            newProduct.setProductCreateDate(DateTimeUtils.currentDate());

            Set<ProductTypeEntity> productTypeEntities = new HashSet<>();
            for (int i = 0; i < quantities.size(); i++) {
               ProductTypeEntity productTypeEntity = new ProductTypeEntity(productTypes.get(i), StringUtils.toInt(quantities.get(i)), images.get(i));
               productTypeEntity.setProductEntity(newProduct);
               productTypeEntities.add(productTypeEntity);
            }
            newProduct.setProductTypeEntities(productTypeEntities);

            Set<ProductCategoryEntity> productCategoryEntities = new HashSet<>();
            for (String id : productCategories) {
               ProductCategoryEntity productCategoryEntity = ProductCategoryDAO.getInstance().getById(StringUtils.toLong(id));
               productCategoryEntities.add(productCategoryEntity);
            }
            newProduct.setProductCategoryEntities(productCategoryEntities);

            if (image0 != null && !image0.isEmpty()) {
               newProduct.setImage0(image0);
            }
            if (image1 != null && !image1.isEmpty()) {
               newProduct.setImage1(image1);
            }
            if (image2 != null && !image2.isEmpty()) {
               newProduct.setImage2(image2);
            }
            if (image3 != null && !image3.isEmpty()) {
               newProduct.setImage3(image3);
            }
            if (image4 != null && !image4.isEmpty()) {
               newProduct.setImage4(image4);
            }

            Long newResult = ProductDAO.getInstance().insert(newProduct);

            if (newResult != 0) {
               result += "true\n" + newProduct.getProductId().toString();
            } else {
               result += "false\nError while update product";
            }
         } catch (Exception e) {
            result += "false\n" + e.getMessage();
         }
         ServletUtils.printWrite(resp, result);
      } else {
         ServletUtils.printWrite(resp, "Access denied");
      }
   }

   @Override
   protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String result = "";
      Map<String, Object> parameterMap = ServletUtils.getParametersMap(req);
      HttpSession session = req.getSession(false);

      if (isAuthentication(req)) {
         try {
            String productId = (String) parameterMap.get("id");

            String productName = (String) parameterMap.get("product-name");
            String brandId = (String) parameterMap.get("brand-id");
            Long sellerId = (Long) session.getAttribute("id");
            String productOrigin = (String) parameterMap.get("product-origin");
            String productDesc = (String) parameterMap.get("product-desc");
            String priceOrder = (String) parameterMap.get("price-order");
            String priceOrigin = (String) parameterMap.get("price-origin");

            String productTypesReq = (String) parameterMap.get("product-types");
            String quantitiesReq = (String) parameterMap.get("quantities");
            String imagesReq = (String) parameterMap.get("images");
            String productCategoriesReq = (String) parameterMap.get("product-categories");
            List<String> productTypes = StringUtils.splitToList(productTypesReq, "@#&");
            List<String> quantities = StringUtils.splitToList(quantitiesReq, "@#&");
            List<String> images = StringUtils.splitToList(imagesReq, "@#&");
            List<String> productCategories = StringUtils.splitToList(productCategoriesReq, "@#&");

            String image0 = (String) parameterMap.get("image-0");
            String image1 = (String) parameterMap.get("image-1");
            String image2 = (String) parameterMap.get("image-2");
            String image3 = (String) parameterMap.get("image-3");
            String image4 = (String) parameterMap.get("image-4");

            ProductEntity productEdit = ProductDAO.getInstance().getById(StringUtils.toLong(productId));
            productEdit.setProductName(productName);
            productEdit.setBrandEntity(BrandDAO.getInstance().getById(StringUtils.toLong(brandId)));
            productEdit.setSellerEntity(SellerDAO.getInstance().getById(sellerId));
            productEdit.setProductOrigin(productOrigin);
            productEdit.setProductDesc(productDesc);
            productEdit.setPriceOrder(StringUtils.toBigDecimal(priceOrder));
            productEdit.setPriceOrigin(StringUtils.toBigDecimal(priceOrigin));

            ArrayList<ProductTypeEntity> productTypeEntities = new ArrayList<>(productEdit.getProductTypeEntities());
            while (quantities.size() < productTypeEntities.size()) {
               productTypeEntities.remove(productTypeEntities.size() - 1);
            }

            for (int i = 0; i < quantities.size(); i++) {
               if (i < productTypeEntities.size()) {
                  productTypeEntities.get(i).setProductTypeName(productTypes.get(i));
                  productTypeEntities.get(i).setQuantity(StringUtils.toInt(quantities.get(i)));
                  productTypeEntities.get(i).setImage(images.get(i));
               } else {
                  ProductTypeEntity productTypeEntity = new ProductTypeEntity(productTypes.get(i), StringUtils.toInt(quantities.get(i)), images.get(i));
                  productTypeEntity.setProductEntity(productEdit);
                  productTypeEntities.add(productTypeEntity);
               }
            }
            productEdit.setProductTypeEntities(new HashSet<>(productTypeEntities));

            Set<ProductCategoryEntity> productCategoryEntities = new HashSet<>();
            for (String id : productCategories) {
               ProductCategoryEntity productCategoryEntity = ProductCategoryDAO.getInstance().getById(StringUtils.toLong(id));
               productCategoryEntities.add(productCategoryEntity);
            }
            productEdit.setProductCategoryEntities(productCategoryEntities);

            if (image0 != null && !image0.isEmpty()) {
               productEdit.setImage0(image0);
            }
            if (image1 != null && !image1.isEmpty()) {
               productEdit.setImage1(image1);
            }
            if (image2 != null && !image2.isEmpty()) {
               productEdit.setImage2(image2);
            }
            if (image3 != null && !image3.isEmpty()) {
               productEdit.setImage3(image3);
            }
            if (image4 != null && !image4.isEmpty()) {
               productEdit.setImage4(image4);
            }

            //Long newResult = ProductDAO.getInstance().insert(productEdit);
            Boolean updateResult = ProductDAO.getInstance().update(productEdit);

            if (updateResult != false) {
               result += "true\n" + productEdit.getProductId().toString();
            } else {
               result += "false\nError while update product";
            }
         } catch (Exception e) {
            result += "false\n" + e.getMessage();
         }
         ServletUtils.printWrite(resp, result);
      } else {
         ServletUtils.printWrite(resp, "Access denied");
      }
   }

   public boolean isAuthentication(HttpServletRequest req) {
      HttpSession session = req.getSession(false);
      Long id = (Long) session.getAttribute("id");

      if (id != null) {
         String userType = UserDAO.getInstance().getUserType(id);
         return userType.equals(Cons.User.USER_TYPE_ADMIN)
                 || userType.equals(Cons.User.USER_TYPE_EMPLOYEE)
                 || userType.equals(Cons.User.USER_TYPE_SELLER);
      }
      return false;
   }
}
