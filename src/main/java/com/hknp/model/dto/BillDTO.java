package com.hknp.model.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BillDTO {
    Long billId;
    Long addressId;
    Long discountId;

    public BillDTO (ResultSet resultSet) throws SQLException {
        billId = resultSet.getLong("BILL_ID");
        addressId = resultSet.getLong("ADDRESS_ID");
        discountId = resultSet.getLong("DISCOUNT_ID");
    }
}
