package com.hknp.model.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BillDTO {
    Long billId;
    Long addressId;
    Long discountId;

    public BillDTO (ResultSet resultSet) {
        try {
            billId = resultSet.getLong("BILL_ID");
            addressId = resultSet.getLong("ADDRESS_ID");
            discountId = resultSet.getLong("DISCOUNT_ID");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public BillDTO(Long billId, Long addressId, Long discountId) {
        this.billId = billId;
        this.addressId = addressId;
        this.discountId = discountId;
    }

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public Long getDiscountId() {
        return discountId;
    }

    public void setDiscountId(Long discountId) {
        this.discountId = discountId;
    }
}
