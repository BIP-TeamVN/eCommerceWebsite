package com.hknp.model.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductSubCategoryDTO {
   Long productSubCategoryId;
   Long productCategoryId;
   String productSubCategoryName;

   public ProductSubCategoryDTO (ResultSet resultSet) throws SQLException {
      productSubCategoryId = resultSet.getLong("PRODUCT_SUB_CATEGORY_ID");
      productCategoryId = resultSet.getLong("PRODUCT_CATEGORY_ID");
      productSubCategoryName = resultSet.getString("PRODUCT_SUB_CATEGORY_NAME");
   }
}
