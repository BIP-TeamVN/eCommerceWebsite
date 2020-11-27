package com.hknp.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BillPaymentModel {
    Long billPaymentId;
    Long billId;

    public BillPaymentModel (ResultSet resultSet) throws SQLException{
        billPaymentId = resultSet.getLong("BILL_PAYMENT_ID");
        billId = resultSet.getLong("BILL_ID");
    }
}
