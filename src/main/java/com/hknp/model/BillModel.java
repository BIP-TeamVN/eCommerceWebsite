package com.hknp.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BillModel {
    Long billId;
    Long addressId;
    Long discountId;

    public BillModel (ResultSet resultSet) throws SQLException {
        billId = resultSet.getLong("BILL_ID");
        addressId = resultSet.getLong("ADDRESS_ID");
        discountId = resultSet.getLong("DISCOUNT_ID");
    }
}
