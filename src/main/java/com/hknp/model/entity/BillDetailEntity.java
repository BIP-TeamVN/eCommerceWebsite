package com.hknp.model.entity;

import javax.persistence.*;
import java.io.Serializable;

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
   @JoinColumn(name = "PRODUCT_ID")
   ProductEntity productEntity;

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

   public ProductEntity getProductEntity() {
      return productEntity;
   }

   public void setProductEntity(ProductEntity productEntity) {
      this.productEntity = productEntity;
   }

   public Integer getQuantity() {
      return quantity;
   }

   public void setQuantity(Integer quantity) {
      this.quantity = quantity;
   }
}
