package com.hknp.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;

@Entity
@Table(name = "BILL_DETAIL")
public class BillDetailEntity implements Serializable {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "BILL_DETAIL_ID")
   Long billDetailId;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "BILL_ID")
   BillEntity billEntity;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "PRODUCT_TYPE_ID")
   ProductTypeEntity productTypeEntity;

   @Column(name = "QUANTITY")
   Integer quantity;

   public Long getBillDetailId() {
      return billDetailId;
   }

   public void setBillDetailId(Long billDetailId) {
      this.billDetailId = billDetailId;
   }

   public BillEntity getBillEntity() {
      return billEntity;
   }

   public void setBillEntity(BillEntity billEntity) {
      this.billEntity = billEntity;
   }

   public ProductTypeEntity getProductTypeEntity() {
      return productTypeEntity;
   }

   public void setProductTypeEntity(ProductTypeEntity productTypeEntity) {
      this.productTypeEntity = productTypeEntity;
   }

   public Integer getQuantity() {
      return quantity;
   }

   public void setQuantity(Integer quantity) {
      this.quantity = quantity;
   }

   public BigDecimal getAmount() {
      return productTypeEntity.getProductEntity().getPriceOrder().multiply(BigDecimal.valueOf(quantity));
   }

   public String toJson() {
      return "{" +
              "\"id\":\"" + productTypeEntity.getProductEntity().getProductId() + "\"," +
              "\"productName\":\"" + productTypeEntity.getProductEntity().getProductName() + "\"," +
              "\"productImage\":\"" + productTypeEntity.getProductEntity().getImage0() + "\"," +
              "\"productTypeName\":\"" + productTypeEntity.getProductTypeName() + "\"," +
              "\"quantity\":\"" + quantity + "\"," +
              "\"price\":\"" + productTypeEntity.getProductEntity().getPriceOrderDisplay() + "\"," +
              "\"Amount\":\"" + new DecimalFormat("###,###").format(getAmount()) + "\"" +
              "}";
   }
}
