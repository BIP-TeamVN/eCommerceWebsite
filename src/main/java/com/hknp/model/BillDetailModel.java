package com.hknp.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BillDetailModel {
    Long billDetailId;
    Long billId;
    Long productId;

    public BillDetailModel (ResultSet resultSet) throws SQLException {
        billDetailId = resultSet.getLong("BILL_DETAIL_ID");
        billId = resultSet.getLong("BILL_ID");
        productId = resultSet.getLong("PRODUCT_ID");
    }
}
