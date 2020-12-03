package com.hknp.model.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductCategoryDTO {
    Long productCategoryId;
    String productCategoryName;

    public ProductCategoryDTO (ResultSet resultSet) {
        try {
            productCategoryId = resultSet.getLong("PRODUCT_CATEGORY_ID");
            productCategoryName = resultSet.getString("PRODUCT_CATEGORY_NAME");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public ProductCategoryDTO(Long productCategoryId, String productCategoryName) {
        this.productCategoryId = productCategoryId;
        this.productCategoryName = productCategoryName;
    }

    public Long getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Long productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public String getProductCategoryName() {
        return productCategoryName;
    }

    public void setProductCategoryName(String productCategoryName) {
        this.productCategoryName = productCategoryName;
    }
}
