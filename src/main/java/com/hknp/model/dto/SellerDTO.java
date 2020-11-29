package com.hknp.model.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SellerDTO {
    Long userId;
    String storeName;
    String storeLink;
    String businessCategoryId;
    Long sellerCategoryId;
    Long bankAccountId;

    public SellerDTO (ResultSet resultSet) throws SQLException {
        userId = resultSet.getLong("USER_ID");
        storeName = resultSet.getString("STORE_NAME");
        storeLink = resultSet.getString("STORE_LINK");
        businessCategoryId = resultSet.getString("BUSINESS_LICENSE_ID");
        sellerCategoryId = resultSet.getLong("SELLER_CATEGORY_ID");
        bankAccountId = resultSet.getLong("BANK_ACCOUNT_ID");
    }
}
