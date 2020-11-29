package com.hknp.model.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SellerCategoryDTO {
    Long sellerCategoryId;
    String categoryName;

    public SellerCategoryDTO (ResultSet resultSet) throws SQLException {
        sellerCategoryId = resultSet.getLong("SELLER_CATEGORY_ID");
        categoryName = resultSet.getString("CATEGORY_NAME");
    }
}
