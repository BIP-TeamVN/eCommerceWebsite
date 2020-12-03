package com.hknp.model.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SellerCategoryDTO {
    Long sellerCategoryId;
    String categoryName;

    public SellerCategoryDTO (ResultSet resultSet) {
        try {
            sellerCategoryId = resultSet.getLong("SELLER_CATEGORY_ID");
            categoryName = resultSet.getString("CATEGORY_NAME");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public SellerCategoryDTO(Long sellerCategoryId, String categoryName) {
        this.sellerCategoryId = sellerCategoryId;
        this.categoryName = categoryName;
    }

    public Long getSellerCategoryId() {
        return sellerCategoryId;
    }

    public void setSellerCategoryId(Long sellerCategoryId) {
        this.sellerCategoryId = sellerCategoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
