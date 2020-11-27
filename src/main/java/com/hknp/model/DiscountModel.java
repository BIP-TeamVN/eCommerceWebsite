package com.hknp.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DiscountModel {
    Long discountId;
    String discountCode;
    Double discountValue;
    Double discountMaxValue;

    public DiscountModel (ResultSet resultSet) throws SQLException {
        discountId = resultSet.getLong("DISCOUNT_ID");
        discountCode = resultSet.getString("DISCOUNT_CODE");
        discountValue = resultSet.getDouble("DISCOUNT_VALUE");
        discountMaxValue = resultSet.getDouble("DISCOUNT_MAX_VALUE");
    }
}
