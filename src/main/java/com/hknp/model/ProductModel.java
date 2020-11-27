package com.hknp.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductModel {
    Long productId;
    Long brandId;
    Long sellerId;
    String productName;
    Float productRate;
    String productOrigin;
    String productDesc;
    Integer quantity;
    Double priceOrigin;
    Double priceOrder;

    public ProductModel (ResultSet resultSet) throws SQLException {
        productId = resultSet.getLong("PRODUCT_ID");
        brandId = resultSet.getLong("BRAND_ID");
        sellerId = resultSet.getLong("SELLER_ID");
        productName = resultSet.getString("PRODUCT_NAME");
        productRate = resultSet.getFloat("PRODUCT_RATE");
        productOrigin = resultSet.getString("PRODUCT_ORIGIN");
        productDesc = resultSet.getString("PRODUCT_DESC");
        quantity = resultSet.getInt("QUANTITY");
        priceOrigin = resultSet.getDouble("PRICE_ORIGIN");
        priceOrder = resultSet.getDouble("PRICE_ORDER");
    }
}
