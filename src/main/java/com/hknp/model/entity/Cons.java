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

      public static String DEFAULT_USER_IMAGE_MALE_SRC = "../../assets/img/default-image-male.png";
      public static String DEFAULT_USER_IMAGE_FEMALE_SRC = "../../assets/img/default-image-female.png";

   }

   public static class Address {
      public static String DEFAULT_ADDRESS_NAME = "Mặc định";
   }

   public static class ProductCategory {
      public static String DEFAULT_PRODUCT_CATEGORY_IMAGE = "../../assets/img/unknown-category.svg";
   }

   public static class Brand {
      public static String DEFAULT_BRAND_IMAGE = "../../assets/img/unknown-brand.svg";
   }
}
