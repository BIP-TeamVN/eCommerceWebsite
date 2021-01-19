package com.hknp.model.domain;

public class ProductInCartItemDomain {

   private String productId;
   private String image;
   private String name;
   private String price;
   private String nameDetail;

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

   public String toJson() {
      return "{" +
              "\"productId\":\"" + productId + "\"," +
              "\"image\":\"" + image + "\"," +
              "\"name\":\"" + name + "\"," +
              "\"price\":\"" + price + "\"," +
              "\"nameDetail\":\"" + nameDetail + "\"" +
              "}";
   }

   public ProductInCartItemDomain() {
   }

   public ProductInCartItemDomain(String productId, String image, String name, String price, String nameDetail) {
      this.productId = productId;
      this.image = image;
      this.name = name;
      this.price = price;
      this.nameDetail = nameDetail;
   }
}
