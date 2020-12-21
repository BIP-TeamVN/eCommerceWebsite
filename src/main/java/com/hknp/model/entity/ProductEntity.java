package com.hknp.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "PRODUCT")
public class ProductEntity implements Serializable {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "PRODUCT_ID")
   Long productId;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "BRAND_ID")
   BrandEntity brandEntity;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "SELLER_ID")
   SellerEntity sellerEntity;

   @Column(name = "PRODUCT_NAME")
   String productName;

   @Column(name = "PRODUCT_RATE")
   Float productRate;

   @Column(name = "PRODUCT_ORIGIN")
   String productOrigin;

   @Column(name = "PRODUCT_DESC")
   String productDesc;

   @Column(name = "QUANTITY")
   Integer quantity;

   @Column(name = "PRICE_ORIGIN")
   BigDecimal priceOrigin;

   @Column(name = "PRICE_ORDER")
   BigDecimal priceOrder;

   @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
   @JoinColumn(name = "PRODUCT_ID")
   List<RateCommentEntity> rateCommentEntities;

   @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
   @JoinColumn(name = "PRODUCT_ID")
   Set<ProductTypeEntity> productTypeEntities;
   @ManyToMany(fetch = FetchType.EAGER)
   @JoinTable(name = "categories_products",
           joinColumns = {@JoinColumn(name = "PRODUCT_ID", nullable = false, updatable = false)},
           inverseJoinColumns = {@JoinColumn(name = "PRODUCT_CATEGORY_ID", nullable = false, updatable = false)})
   private Set<ProductCategoryEntity> productCategoryEntities;

   public ProductEntity() {
   }

   public Set<ProductTypeEntity> getProductTypeEntities() {
      return productTypeEntities;
   }

   public void setProductTypeEntities(Set<ProductTypeEntity> productTypeEntities) {
      this.productTypeEntities = productTypeEntities;
   }

   public List<RateCommentEntity> getRateCommentEntities() {
      return rateCommentEntities;
   }

   public void setRateCommentEntities(List<RateCommentEntity> rateCommentEntities) {
      this.rateCommentEntities = rateCommentEntities;
   }

   public Long getProductId() {
      return productId;
   }

   public void setProductId(Long productId) {
      this.productId = productId;
   }

   public BrandEntity getBrandEntity() {
      return brandEntity;
   }

   public void setBrandEntity(BrandEntity brandEntity) {
      this.brandEntity = brandEntity;
   }

   public SellerEntity getSellerEntity() {
      return sellerEntity;
   }

   public void setSellerEntity(SellerEntity sellerEntity) {
      this.sellerEntity = sellerEntity;
   }

   public String getProductName() {
      return productName;
   }

   public void setProductName(String productName) {
      this.productName = productName;
   }

   public Float getProductRate() {
      return productRate;
   }

   public void setProductRate(Float productRate) {
      this.productRate = productRate;
   }

   public String getProductOrigin() {
      return productOrigin;
   }

   public void setProductOrigin(String productOrigin) {
      this.productOrigin = productOrigin;
   }

   public String getProductDesc() {
      return productDesc;
   }

   public void setProductDesc(String productDesc) {
      this.productDesc = productDesc;
   }

   public Integer getQuantity() {
      return quantity;
   }

   public void setQuantity(Integer quantity) {
      this.quantity = quantity;
   }

   public BigDecimal getPriceOrigin() {
      return priceOrigin;
   }

   public void setPriceOrigin(BigDecimal priceOrigin) {
      this.priceOrigin = priceOrigin;
   }

   public BigDecimal getPriceOrder() {
      return priceOrder;
   }

   public void setPriceOrder(BigDecimal priceOrder) {
      this.priceOrder = priceOrder;
   }

   public Set<ProductCategoryEntity> getProductCategoryEntities() {
      return productCategoryEntities;
   }

   public void setProductCategoryEntities(Set<ProductCategoryEntity> productCategoryEntities) {
      this.productCategoryEntities = productCategoryEntities;
   }
}
