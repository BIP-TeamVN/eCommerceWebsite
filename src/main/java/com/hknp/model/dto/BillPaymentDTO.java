package com.hknp.model.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BillPaymentDTO {
    Long billPaymentId;
    Long billId;

    public BillPaymentDTO (ResultSet resultSet) {
        try {
            billPaymentId = resultSet.getLong("BILL_PAYMENT_ID");
            billId = resultSet.getLong("BILL_ID");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public BillPaymentDTO(Long billPaymentId, Long billId) {
        this.billPaymentId = billPaymentId;
        this.billId = billId;
    }

    public Long getBillPaymentId() {
        return billPaymentId;
    }

    public void setBillPaymentId(Long billPaymentId) {
        this.billPaymentId = billPaymentId;
    }

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }
}
