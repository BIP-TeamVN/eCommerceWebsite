package com.hknp.model.dto;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DiscountDTO {
    Long discountId;
    String discountCode;
    BigDecimal discountValue;
    BigDecimal discountMaxValue;

    public DiscountDTO (ResultSet resultSet) {
        try {
            discountId = resultSet.getLong("DISCOUNT_ID");
            discountCode = resultSet.getString("DISCOUNT_CODE");
            discountValue = resultSet.getBigDecimal("DISCOUNT_VALUE");
            discountMaxValue = resultSet.getBigDecimal("DISCOUNT_MAX_VALUE");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public DiscountDTO(Long discountId, String discountCode, BigDecimal discountValue, BigDecimal discountMaxValue) {
        this.discountId = discountId;
        this.discountCode = discountCode;
        this.discountValue = discountValue;
        this.discountMaxValue = discountMaxValue;
    }

    public Long getDiscountId() {
        return discountId;
    }

    public void setDiscountId(Long discountId) {
        this.discountId = discountId;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public BigDecimal getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(BigDecimal discountValue) {
        this.discountValue = discountValue;
    }

    public BigDecimal getDiscountMaxValue() {
        return discountMaxValue;
    }

    public void setDiscountMaxValue(BigDecimal discountMaxValue) {
        this.discountMaxValue = discountMaxValue;
    }
}
