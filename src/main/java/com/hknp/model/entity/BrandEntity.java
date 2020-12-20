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

   @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
   @JoinColumn(name = "BRAND_ID")
   List<ProductEntity> productEntities;

   public BrandEntity () {}

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
}
