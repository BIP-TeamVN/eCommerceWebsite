package com.hknp.model.domain;

import com.hknp.utils.StringUtils;

public class CartItemDomain {
   String productTypeId;
   Integer quantity;

   public CartItemDomain(String productTypeId, String quantity) {
      this.productTypeId = productTypeId;
      this.quantity = StringUtils.toInt(quantity);
   }

}
