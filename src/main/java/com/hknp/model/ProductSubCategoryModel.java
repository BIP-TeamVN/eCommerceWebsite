package com.hknp.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductSubCategoryModel {
    Long productSubCategoryId;
    Long productCategoryId;
    String productSubCategoryName;

    public ProductSubCategoryModel (ResultSet resultSet) throws SQLException {
        productSubCategoryId = resultSet.getLong("PRODUCT_SUB_CATEGORY_ID");
        productCategoryId = resultSet.getLong("PRODUCT_CATEGORY_ID");
        productSubCategoryName = resultSet.getString("PRODUCT_SUB_CATEGORY_NAME");
    }
}
