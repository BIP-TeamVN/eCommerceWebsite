package com.hknp.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "PRODUCT_CATEGORY")
public class ProductCategoryEntity implements Serializable {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "PRODUCT_CATEGORY_ID")
   Long productCategoryId;

   @Column(name = "PRODUCT_CATEGORY_NAME")
   String productCategoryName;
   @ManyToMany(fetch = FetchType.EAGER, mappedBy = "productCategoryEntities")
   Set<ProductEntity> productEntities;

   public ProductCategoryEntity() {
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

   public Set<ProductEntity> getProductEntities() {
      return productEntities;
   }

   public void setProductEntities(Set<ProductEntity> productEntities) {
      this.productEntities = productEntities;
   }
}
