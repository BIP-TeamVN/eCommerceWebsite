package com.hknp.model.domain;

import com.hknp.utils.StringUtils;

public class CartItemDomain {
   private String productTypeId;
   private Integer quantity;

   public CartItemDomain() {
   }

   public CartItemDomain(String productTypeId, String quantity) {
      this.productTypeId = productTypeId;
      this.quantity = StringUtils.toInt(quantity);
   }

   public String getProductTypeId() {
      return productTypeId;
   }

   public void setProductTypeId(String productTypeId) {
      this.productTypeId = productTypeId;
   }

   public Integer getQuantity() {
      return quantity;
   }

   public void setQuantity(Integer quantity) {
      this.quantity = quantity;
   }

}
