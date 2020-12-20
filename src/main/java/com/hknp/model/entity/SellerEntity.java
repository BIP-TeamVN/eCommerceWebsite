package com.hknp.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "SELLER")
public class SellerEntity implements Serializable {
   @Id
   @Column(name = "USER_ID")
   Long userId;

   @Column(name = "STORE_NAME")
   String storeName;

   @Column(name = "STORE_LINK")
   String storeLink;

   @Column(name = "BUSINESS_LICENSE_ID")
   String businessLicenseId;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "SELLER_CATEGORY_ID")
   SellerCategoryEntity sellerCategoryEntity;

   @Column(name = "BANK_ACCOUNT_ID")
   Long bankAccountId;

   @OneToOne(fetch = FetchType.EAGER)
   @PrimaryKeyJoinColumn(name = "USER_ID")
   UserEntity userEntity;

   @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
   @JoinColumn(name = "SELLER_ID")
   List<ProductEntity> productEntities;

   public SellerEntity () {}

   public List<ProductEntity> getProductEntities() {
      return productEntities;
   }

   public void setProductEntities(List<ProductEntity> productEntities) {
      this.productEntities = productEntities;
   }

   public Long getUserId() {
      return userId;
   }

   public void setUserId(Long userId) {
      this.userId = userId;
   }

   public String getStoreName() {
      return storeName;
   }

   public void setStoreName(String storeName) {
      this.storeName = storeName;
   }

   public String getStoreLink() {
      return storeLink;
   }

   public void setStoreLink(String storeLink) {
      this.storeLink = storeLink;
   }

   public String getBusinessLicenseId() {
      return businessLicenseId;
   }

   public void setBusinessLicenseId(String businessLicenseId) {
      this.businessLicenseId = businessLicenseId;
   }

   public SellerCategoryEntity getSellerCategoryEntity() {
      return sellerCategoryEntity;
   }

   public void setSellerCategoryEntity(SellerCategoryEntity sellerCategoryEntity) {
      this.sellerCategoryEntity = sellerCategoryEntity;
   }

   public Long getBankAccountId() {
      return bankAccountId;
   }

   public void setBankAccountId(Long bankAccountId) {
      this.bankAccountId = bankAccountId;
   }

   public UserEntity getUserEntity() {
      return userEntity;
   }

   public void setUserEntity(UserEntity userEntity) {
      this.userEntity = userEntity;
   }
}
