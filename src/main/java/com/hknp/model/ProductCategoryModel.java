package com.hknp.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductCategoryModel {
    Long productCategoryId;
    String productCategoryName;

    public ProductCategoryModel (ResultSet resultSet) throws SQLException {
        productCategoryId = resultSet.getLong("PRODUCT_CATEGORY_ID");
        productCategoryName = resultSet.getString("PRODUCT_CATEGORY_NAME");
    }
}
