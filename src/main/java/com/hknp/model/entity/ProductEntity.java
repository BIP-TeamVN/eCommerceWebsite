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

   @Column(name = "PRICE_ORIGIN")
   BigDecimal priceOrigin;

   @Column(name = "PRICE_ORDER")
   BigDecimal priceOrder;

   @Column(name = "IMAGE_0")
   String image0;

   @Column(name = "IMAGE_1")
   String image1;

   @Column(name = "IMAGE_2")
   String image2;

   @Column(name = "IMAGE_3")
   String image3;

   @Column(name = "IMAGE_4")
   String image4;

   @Column(name = "STATUS")
   Integer status;

   @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
   @JoinColumn(name = "PRODUCT_ID")
   List<RateCommentEntity> rateCommentEntities;

   @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
   @JoinColumn(name = "PRODUCT_ID")
   Set<ProductTypeEntity> productTypeEntities;
   @ManyToMany(fetch = FetchType.EAGER)
   @JoinTable(name = "CATEGORIES_FOR_PRODUCTS",
           joinColumns = {@JoinColumn(name = "PRODUCT_ID", nullable = false, updatable = false)},
           inverseJoinColumns = {@JoinColumn(name = "PRODUCT_CATEGORY_ID", nullable = false, updatable = false)})
   private Set<ProductCategoryEntity> productCategoryEntities;

   public ProductEntity() {
   }

   public Integer getStatus() {
      return status;
   }

   public void setStatus(Integer status) {
      this.status = status;
   }

   public String getImage0() {
      if (image0 == null || image0.isEmpty()) {
         return Cons.Product.DEFAULT_PRODUCT_IMAGE;
      } else {
         return image0;
      }
   }

   public void setImage0(String image0) {
      this.image0 = image0;
   }

   public String getImage1() {
      if (image1 == null || image1.isEmpty()) {
         return Cons.Product.DEFAULT_PRODUCT_IMAGE;
      } else {
         return image1;
      }
   }

   public void setImage1(String image1) {
      this.image1 = image1;
   }

   public String getImage2() {
      if (image2 == null || image2.isEmpty()) {
         return Cons.Product.DEFAULT_PRODUCT_IMAGE;
      } else {
         return image2;
      }
   }

   public void setImage2(String image2) {
      this.image2 = image2;
   }

   public Set<ProductTypeEntity> getProductTypeEntities() {
      return productTypeEntities;
   }

   public void setProductTypeEntities(Set<ProductTypeEntity> productTypeEntities) {
      this.productTypeEntities = productTypeEntities;
   }

   public String getImage3() {
      if (image3 == null || image3.isEmpty()) {
         return Cons.Product.DEFAULT_PRODUCT_IMAGE;
      } else {
         return image3;
      }
   }

   public void setImage3(String image3) {
      this.image3 = image3;
   }

   public String getImage4() {
      if (image4 == null || image4.isEmpty()) {
         return Cons.Product.DEFAULT_PRODUCT_IMAGE;
      } else {
         return image4;
      }
   }

   public void setImage4(String image4) {
      this.image4 = image4;
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

   public String toJson() {
      String desc = "";
      if (productDesc.length() > 30) {
         desc = getProductDesc().substring(0, 30) + "...";
      }
      else {
         desc = getProductDesc();
      }
      return "{" +
              "\"id\":\"" + productId + "\"," +
              "\"productName\":\"" + productName + "\"," +
              "\"brand\":\"" + brandEntity.getBrandName() + "\"," +
              "\"seller\":\"" + sellerEntity.getStoreName() + "\"," +
              "\"productRate\":\"" + productRate + "\"," +
              "\"productOrigin\":\"" + productOrigin + "\"," +
              "\"productDesc\":\"" + desc + "\"," +
              "\"priceOrigin\":\"" + priceOrigin + "\"," +
              "\"priceOrder\":\"" + priceOrder + "\"," +
              "\"image0\":\"" + getImage0() + "\"," +
              "\"image1\":\"" + getImage1() + "\"," +
              "\"image2\":\"" + getImage2() + "\"," +
              "\"image3\":\"" + getImage3() + "\"," +
              "\"image4\":\"" + getImage4() + "\"," +
              "\"status\":\"" + status + "\"" +
              "}";
   }
}
