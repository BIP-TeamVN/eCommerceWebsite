package com.hknp.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "BRAND")
public class BrandEntity implements Serializable {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "BRAND_ID")
   Long brandId;

   @Column(name = "BRAND_NAME")
   String brandName;

   @Column(name = "BRAND_ORIGIN")
   String brandOrigin;

   @Column(name = "IMAGE", columnDefinition = "LONGTEXT DEFAULT NULL")
   String image;

   @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @JoinColumn(name = "BRAND_ID")
   List<ProductEntity> productEntities;

   public BrandEntity() {
   }

   public String toJson() {
      return "{" +
              "\"id\":\"" + brandId + "\"," +
              "\"brandName\":\"" + brandName + "\"," +
              "\"brandOrigin\":\"" + brandOrigin + "\"," +
              "\"image\":\"" + getImageSrc() + "\"" +
              "}";
   }

   public List<ProductEntity> getProductEntities() {
      return productEntities;
   }

   public void setProductEntities(List<ProductEntity> productEntities) {
      this.productEntities = productEntities;
   }

   public Long getBrandId() {
      return brandId;
   }

   public void setBrandId(Long brandId) {
      this.brandId = brandId;
   }

   public String getBrandName() {
      return brandName;
   }

   public void setBrandName(String brandName) {
      this.brandName = brandName;
   }

   public String getBrandOrigin() {
      return brandOrigin;
   }

   public void setBrandOrigin(String brandOrigin) {
      this.brandOrigin = brandOrigin;
   }

   public String getImage() {
      return image;
   }

   public void setImage(String image) {
      this.image = image;
   }

   public String getImageSrc() {
      if (image == null || image.isEmpty()) {
         return Cons.Brand.DEFAULT_BRAND_IMAGE;
      } else {
         return image;
      }
   }
}
