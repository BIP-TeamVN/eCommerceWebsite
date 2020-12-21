package com.hknp.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "SELLER_CATEGORY")
public class SellerCategoryEntity implements Serializable {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "SELLER_CATEGORY_ID")
   Long sellerCategoryId;

   @Column(name = "CATEGORY_NAME")
   String categoryName;

   @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
   @JoinColumn(name = "SELLER_CATEGORY_ID")
   List<SellerEntity> sellerEntities;

   public SellerCategoryEntity() {
   }

   public List<SellerEntity> getSellerEntities() {
      return sellerEntities;
   }

   public void setSellerEntities(List<SellerEntity> sellerEntities) {
      this.sellerEntities = sellerEntities;
   }

   public Long getSellerCategoryId() {
      return sellerCategoryId;
   }

   public void setSellerCategoryId(Long sellerCategoryId) {
      this.sellerCategoryId = sellerCategoryId;
   }

   public String getCategoryName() {
      return categoryName;
   }

   public void setCategoryName(String categoryName) {
      this.categoryName = categoryName;
   }
}
