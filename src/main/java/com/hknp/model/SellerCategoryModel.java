package com.hknp.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SellerCategoryModel {
    Long sellerCategoryId;
    String categoryName;

    public SellerCategoryModel (ResultSet resultSet) throws SQLException {
        sellerCategoryId = resultSet.getLong("SELLER_CATEGORY_ID");
        categoryName = resultSet.getString("CATEGORY_NAME");
    }
}
