package com.hknp.model.entity;

public class Cons {
   public static class User {
      public static String USER_GENDER_MALE = "Nam";
      public static String USER_GENDER_FEMALE = "Nữ";
      public static String USER_GENDER_OTHER = "Khác";

      public static final String USER_TYPE_ADMIN = "ADMIN";
      public static final String USER_TYPE_EMPLOYEE = "EMPLOYEE";
      public static final String USER_TYPE_SELLER = "SELLER";
      public static final String USER_TYPE_CUSTOMER = "CUSTOMER";
      public static final String USER_TYPE_DELIVERY = "DELIVERY";

      public static final String DEFAULT_USER_IMAGE_MALE_SRC = "../../assets/img/default-image-male.png";
      public static final String DEFAULT_USER_IMAGE_FEMALE_SRC = "../../assets/img/default-image-female.png";

   }

   public static class Address {
      public static final String DEFAULT_ADDRESS_NAME = "Mặc định";
   }

   public static class Product {
      public static final String DEFAULT_PRODUCT_IMAGE = "../../assets/img/no-image-product.svg";
   }

   public static class ProductCategory {
      public static final String DEFAULT_PRODUCT_CATEGORY_IMAGE = "../../assets/img/unknown-category.svg";
   }

   public static class Brand {
      public static final String DEFAULT_BRAND_IMAGE = "../../assets/img/unknown-brand.svg";
   }

   public static class Bill {
      public static final Integer BILL_STATUS_CREATE = 0;      // mới tạo
      public static final Integer BILL_STATUS_CANCEL = 1;      // hủy
      public static final Integer BILL_STATUS_VERIFIED = 2;    // shop đã xác nhận
      public static final Integer BILL_STATUS_SHIPPING = 3;    // shipper đang giao
      public static final Integer BILL_STATUS_DONE = 4;        // giao xong
      public static final Integer BILL_STATUS_FAILED = 5;      // giao không được
   }
}
