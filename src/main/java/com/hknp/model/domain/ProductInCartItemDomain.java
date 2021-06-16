package com.hknp.model.domain;

import com.hknp.utils.StringUtils;

public class ProductInCartItemDomain {

   private String productId;
   private String productTypeId;
   private String image;
   private String name;
   private String price;
   private String nameDetail;
   private Integer quantity;
   private String shopName;
   private String sellerId;

   public ProductInCartItemDomain() {
   }

   public ProductInCartItemDomain(String productId, String image, String name, String price, String nameDetail, String quantity, String shopName, String sellerId) {
      this.productId = productId;
      this.image = image;
      this.name = name;
      this.price = price;
      this.nameDetail = nameDetail;
      this.quantity = StringUtils.toInt(quantity);
      this.shopName = shopName;
      this.sellerId = sellerId;
   }

   public String getProductTypeId() {
      return productTypeId;
   }

   public void setProductTypeId(String productTypeId) {
      this.productTypeId = productTypeId;
   }

   public String getShopName() {
      return shopName;
   }

   public void setShopName(String shopName) {
      this.shopName = shopName;
   }

   public String getSellerId() {
      return sellerId;
   }

   public void setSellerId(String sellerId) {
      this.sellerId = sellerId;
   }

   public String getProductId() {
      return productId;
   }

   public void setProductId(String productId) {
      this.productId = productId;
   }

   public String getImage() {
      return image;
   }

   public void setImage(String image) {
      this.image = image;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getPrice() {
      return price;
   }

   public void setPrice(String price) {
      this.price = price;
   }

   public String getNameDetail() {
      return nameDetail;
   }

   public void setNameDetail(String nameDetail) {
      this.nameDetail = nameDetail;
   }

   public Integer getQuantity() {
      return quantity;
   }

   public void setQuantity(Integer quantity) {
      this.quantity = quantity;
   }

   public String toJson() {
      return "{" +
              "\"shopName\":\"" + shopName + "\"," +
              "\"sellerId\":\"" + sellerId + "\"," +
              "\"productId\":\"" + productId + "\"," +
              "\"image\":\"" + image + "\"," +
              "\"name\":\"" + name + "\"," +
              "\"price\":\"" + price + "\"," +
              "\"nameDetail\":\"" + nameDetail + "\"," +
              "\"quantity\":\"" + quantity + "\"" +
              "}";
   }

   public String toJsonOne() {
      return "{" +
              "\"productId\":\"" + productId + "\"," +
              "\"productTypeId\":\"" + productTypeId + "\"," +
              "\"name\":\"" + name + "\"," +
              "\"image\":\"" + image + "\"," +
              "\"price\":\"" + price + "\"," +
              "\"nameDetail\":\"" + nameDetail + "\"," +
              "\"quantity\":\"" + quantity + "\"" +
              "}";
   }

   public String toJson(String shopName, Long sellerId) {
      return "{" +
              "\"shopName\":\"" + shopName + "\"," +
              "\"sellerId\":\"" + sellerId + "\"," +
              "\"carts\":" +
              "[{" +
              "\"productId\":\"" + productId + "\"," +
              "\"image\":\"" + image + "\"," +
              "\"name\":\"" + name + "\"," +
              "\"price\":\"" + price + "\"," +
              "\"nameDetail\":\"" + nameDetail + "\"," +
              "\"quantity\":\"" + quantity + "\"," +
              "}]" +
              "}";
   }

   public String toJson(String str) {
      return "{" +
              "\"shopName\":\"" + shopName + "\"," +
              "\"sellerId\":\"" + sellerId + "\"," +
              "\"carts\":" + str + "}";
   }
}
