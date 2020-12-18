package com.hknp.model.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "PRODUCT_SUB_CATEGORY")
public class ProductSubCategoryEntity implements Serializable {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "PRODUCT_SUB_CATEGORY_ID", columnDefinition = "")
   Long productSubCategoryId;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "PRODUCT_CATEGORY_ID")
   ProductCategoryEntity productCategoryEntity;

   @Column(name = "PRODUCT_SUB_CATEGORY_NAME")
   String productSubCategoryName;

   public ProductSubCategoryEntity () {}

   public Long getProductSubCategoryId() {
      return productSubCategoryId;
   }

   public void setProductSubCategoryId(Long productSubCategoryId) {
      this.productSubCategoryId = productSubCategoryId;
   }

   public ProductCategoryEntity getProductCategoryEntity() {
      return productCategoryEntity;
   }

   public void setProductCategoryEntity(ProductCategoryEntity productCategoryEntity) {
      this.productCategoryEntity = productCategoryEntity;
   }

   public String getProductSubCategoryName() {
      return productSubCategoryName;
   }

   public void setProductSubCategoryName(String productSubCategoryName) {
      this.productSubCategoryName = productSubCategoryName;
   }
}
