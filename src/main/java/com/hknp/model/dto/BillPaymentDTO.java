package com.hknp.model.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BillPaymentDTO {
    Long billPaymentId;
    Long billId;

    public BillPaymentDTO (ResultSet resultSet) throws SQLException{
        billPaymentId = resultSet.getLong("BILL_PAYMENT_ID");
        billId = resultSet.getLong("BILL_ID");
    }
}
