package com.hknp.model.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "PRODUCT_TYPE")
public class ProductTypeEntity implements Serializable {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "PRODUCT_TYPE_ID")
   Long productTypeId;

   @ManyToOne
   @JoinColumn(name = "PRODUCT_ID")
   ProductEntity productEntity;

   @Column(name = "PRODUCT_TYPE_NAME")
   String productTypeName;

   public ProductTypeEntity() {
   }

   public Long getProductTypeId() {
      return productTypeId;
   }

   public void setProductTypeId(Long productTypeId) {
      this.productTypeId = productTypeId;
   }

   public ProductEntity getProductEntity() {
      return productEntity;
   }

   public void setProductEntity(ProductEntity productEntity) {
      this.productEntity = productEntity;
   }

   public String getProductTypeName() {
      return productTypeName;
   }

   public void setProductTypeName(String productTypeName) {
      this.productTypeName = productTypeName;
   }
}
