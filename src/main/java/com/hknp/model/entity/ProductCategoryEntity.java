package com.hknp.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "PRODUCT_CATEGORY")
public class ProductCategoryEntity implements Serializable {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "PRODUCT_CATEGORY_ID")
   Long productCategoryId;

   @Column(name = "PRODUCT_CATEGORY_NAME")
   String productCategoryName;

   @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
   @JoinColumn(name = "PRODUCT_CATEGORY_ID")
   List<ProductSubCategoryEntity> productSubCategoryEntities;

   public ProductCategoryEntity () {}

   public List<ProductSubCategoryEntity> getProductSubCategoryEntities() {
      return productSubCategoryEntities;
   }

   public void setProductSubCategoryEntities(List<ProductSubCategoryEntity> productSubCategoryEntities) {
      this.productSubCategoryEntities = productSubCategoryEntities;
   }

   public Long getProductCategoryId() {
      return productCategoryId;
   }

   public void setProductCategoryId(Long productCategoryId) {
      this.productCategoryId = productCategoryId;
   }

   public String getProductCategoryName() {
      return productCategoryName;
   }

   public void setProductCategoryName(String productCategoryName) {
      this.productCategoryName = productCategoryName;
   }
}
