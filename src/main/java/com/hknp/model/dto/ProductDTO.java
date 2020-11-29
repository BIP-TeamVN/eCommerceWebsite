package com.hknp.model.dto;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDTO {
    Long productId;
    Long brandId;
    Long sellerId;
    String productName;
    Float productRate;
    String productOrigin;
    String productDesc;
    Integer quantity;
    BigDecimal priceOrigin;
    BigDecimal priceOrder;

    public ProductDTO (ResultSet resultSet) throws SQLException {
        productId = resultSet.getLong("PRODUCT_ID");
        brandId = resultSet.getLong("BRAND_ID");
        sellerId = resultSet.getLong("SELLER_ID");
        productName = resultSet.getString("PRODUCT_NAME");
        productRate = resultSet.getFloat("PRODUCT_RATE");
        productOrigin = resultSet.getString("PRODUCT_ORIGIN");
        productDesc = resultSet.getString("PRODUCT_DESC");
        quantity = resultSet.getInt("QUANTITY");
        priceOrigin = resultSet.getBigDecimal("PRICE_ORIGIN");
        priceOrder = resultSet.getBigDecimal("PRICE_ORDER");
    }
}
