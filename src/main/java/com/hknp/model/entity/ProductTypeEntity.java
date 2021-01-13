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

   @Column(name = "QUANTITY")
   Integer quantity;

   @Column(name = "IMAGE")
   String image;

   public ProductTypeEntity() {
   }

   public ProductTypeEntity(String productTypeName, Integer quantity, String image) {
      this.productTypeName = productTypeName;
      this.quantity = quantity;
      this.image = image;
   }

   public Integer getQuantity() {
      return quantity;
   }

   public void setQuantity(Integer quantity) {
      this.quantity = quantity;
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

   public String getImage() {
      if (image == null || image.isEmpty()) {
         return Cons.Product.DEFAULT_PRODUCT_IMAGE;
      } else {
         return image;
      }
   }

   public void setImage(String image) {
      this.image = image;
   }
}
