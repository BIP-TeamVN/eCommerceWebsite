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

    public ProductDTO (ResultSet resultSet) {
        try {
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
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public ProductDTO(Long productId, Long brandId, Long sellerId, String productName, Float productRate, String productOrigin, String productDesc, Integer quantity, BigDecimal priceOrigin, BigDecimal priceOrder) {
        this.productId = productId;
        this.brandId = brandId;
        this.sellerId = sellerId;
        this.productName = productName;
        this.productRate = productRate;
        this.productOrigin = productOrigin;
        this.productDesc = productDesc;
        this.quantity = quantity;
        this.priceOrigin = priceOrigin;
        this.priceOrder = priceOrder;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Float getProductRate() {
        return productRate;
    }

    public void setProductRate(Float productRate) {
        this.productRate = productRate;
    }

    public String getProductOrigin() {
        return productOrigin;
    }

    public void setProductOrigin(String productOrigin) {
        this.productOrigin = productOrigin;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPriceOrigin() {
        return priceOrigin;
    }

    public void setPriceOrigin(BigDecimal priceOrigin) {
        this.priceOrigin = priceOrigin;
    }

    public BigDecimal getPriceOrder() {
        return priceOrder;
    }

    public void setPriceOrder(BigDecimal priceOrder) {
        this.priceOrder = priceOrder;
    }
}
