package com.hknp.model.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BillDetailDTO {
   Long billDetailId;
   Long billId;
   Long productId;

    public BillDetailDTO (ResultSet resultSet) {
        try {
            billDetailId = resultSet.getLong("BILL_DETAIL_ID");
            billId = resultSet.getLong("BILL_ID");
            productId = resultSet.getLong("PRODUCT_ID");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public BillDetailDTO(Long billDetailId, Long billId, Long productId) {
        this.billDetailId = billDetailId;
        this.billId = billId;
        this.productId = productId;
    }

    public Long getBillDetailId() {
        return billDetailId;
    }

    public void setBillDetailId(Long billDetailId) {
        this.billDetailId = billDetailId;
    }

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
