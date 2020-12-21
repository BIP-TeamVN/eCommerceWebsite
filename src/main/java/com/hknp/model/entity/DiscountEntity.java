package com.hknp.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "DISCOUNT")
public class DiscountEntity implements Serializable {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "DISCOUNT_ID")
   Long discountId;

   @Column(name = "DISCOUNT_CODE")
   String discountCode;

   @Column(name = "DISCOUNT_VALUE")
   BigDecimal discountValue;

   @Column(name = "DISCOUNT_MAX_VALUE")
   BigDecimal discountMaxValue;

   public DiscountEntity() {
   }

   public Long getDiscountId() {
      return discountId;
   }

   public void setDiscountId(Long discountId) {
      this.discountId = discountId;
   }

   public String getDiscountCode() {
      return discountCode;
   }

   public void setDiscountCode(String discountCode) {
      this.discountCode = discountCode;
   }

   public BigDecimal getDiscountValue() {
      return discountValue;
   }

   public void setDiscountValue(BigDecimal discountValue) {
      this.discountValue = discountValue;
   }

   public BigDecimal getDiscountMaxValue() {
      return discountMaxValue;
   }

   public void setDiscountMaxValue(BigDecimal discountMaxValue) {
      this.discountMaxValue = discountMaxValue;
   }
}
