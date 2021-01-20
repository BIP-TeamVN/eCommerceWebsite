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

   @Column(name = "IMAGE", columnDefinition = "LONGTEXT DEFAULT NULL")
   String image;

   @ManyToMany(fetch = FetchType.LAZY, mappedBy = "productCategoryEntities")
   Set<ProductEntity> productEntities;

   public ProductCategoryEntity() {
   }


   public String toJson() {
      return "{" +
              "\"id\":\"" + productCategoryId + "\"," +
              "\"name\":\"" + productCategoryName + "\"," +
              "\"image\":\"" + getImageSrc() + "\"" +
              "}";
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

   public String getImage() {
      return image;
   }

   public void setImage(String image) {
      this.image = image;
   }

   public String getImageSrc() {
      if (image == null || image.isEmpty()) {
         return Cons.ProductCategory.DEFAULT_PRODUCT_CATEGORY_IMAGE;
      } else {
         return image;
      }
   }

   public Set<ProductEntity> getProductEntities() {
      return productEntities;
   }

   public void setProductEntities(Set<ProductEntity> productEntities) {
      this.productEntities = productEntities;
   }
}
