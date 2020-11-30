package com.hknp.model.dto;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DiscountDTO {
   Long discountId;
   String discountCode;
   BigDecimal discountValue;
   BigDecimal discountMaxValue;

   public DiscountDTO (ResultSet resultSet) throws SQLException {
      discountId = resultSet.getLong("DISCOUNT_ID");
      discountCode = resultSet.getString("DISCOUNT_CODE");
      discountValue = resultSet.getBigDecimal("DISCOUNT_VALUE");
      discountMaxValue = resultSet.getBigDecimal("DISCOUNT_MAX_VALUE");
   }
}
