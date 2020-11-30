package com.hknp.model.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BillDetailDTO {
   Long billDetailId;
   Long billId;
   Long productId;

   public BillDetailDTO (ResultSet resultSet) throws SQLException {
      billDetailId = resultSet.getLong("BILL_DETAIL_ID");
      billId = resultSet.getLong("BILL_ID");
      productId = resultSet.getLong("PRODUCT_ID");
   }
}
