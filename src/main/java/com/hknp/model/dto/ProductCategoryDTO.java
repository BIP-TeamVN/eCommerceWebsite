package com.hknp.model.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductCategoryDTO {
   Long productCategoryId;
   String productCategoryName;

   public ProductCategoryDTO (ResultSet resultSet) throws SQLException {
      productCategoryId = resultSet.getLong("PRODUCT_CATEGORY_ID");
      productCategoryName = resultSet.getString("PRODUCT_CATEGORY_NAME");
   }
}
