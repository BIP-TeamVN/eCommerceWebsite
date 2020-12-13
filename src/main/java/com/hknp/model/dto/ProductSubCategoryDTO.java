package com.hknp.model.dto;

import com.hknp.model.dao.ProductCategoryDAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductSubCategoryDTO {
   Long productSubCategoryId;
   Long productCategoryId;
   ProductCategoryDTO productCategory;
   String productSubCategoryName;

   public ProductSubCategoryDTO(ResultSet resultSet) {
      try {
         productSubCategoryId = resultSet.getLong("PRODUCT_SUB_CATEGORY_ID");
         productCategoryId = resultSet.getLong("PRODUCT_CATEGORY_ID");
         productCategory = ProductCategoryDAO.getInstance().getById(productCategoryId);
         productSubCategoryName = resultSet.getString("PRODUCT_SUB_CATEGORY_NAME");
      } catch (SQLException exception) {
         exception.printStackTrace();
      }
   }

   public ProductSubCategoryDTO(Long productSubCategoryId, Long productCategoryId, ProductCategoryDTO productCategory, String productSubCategoryName) {
      this.productSubCategoryId = productSubCategoryId;
      this.productCategoryId = productCategoryId;
      this.productCategory = productCategory;
      this.productSubCategoryName = productSubCategoryName;
   }

   public Long getProductSubCategoryId() {
      return productSubCategoryId;
   }

   public void setProductSubCategoryId(Long productSubCategoryId) {
      this.productSubCategoryId = productSubCategoryId;
   }

   public Long getProductCategoryId() {
      return productCategoryId;
   }

   public void setProductCategoryId(Long productCategoryId) {
      this.productCategoryId = productCategoryId;
   }

   public ProductCategoryDTO getProductCategory() {
      return productCategory;
   }

   public void setProductCategory(ProductCategoryDTO productCategory) {
      this.productCategory = productCategory;
   }

   public String getProductSubCategoryName() {
      return productSubCategoryName;
   }

   public void setProductSubCategoryName(String productSubCategoryName) {
      this.productSubCategoryName = productSubCategoryName;
   }
}
