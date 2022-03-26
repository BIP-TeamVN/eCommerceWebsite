package com.hknp.utils;

import com.hknp.model.domain.ProductInCartItemDomain;

public class JsonUtils {

    public String toJson(ProductInCartItemDomain productInCartItemDomain) {
        return "{" +
                "\"shopName\":\"" + productInCartItemDomain.getShopName() + "\"," +
                "\"sellerId\":\"" + productInCartItemDomain.getSellerId() + "\"," +
                "\"productId\":\"" + productInCartItemDomain.getProductId() + "\"," +
                "\"image\":\"" + productInCartItemDomain.getImage() + "\"," +
                "\"name\":\"" + productInCartItemDomain.getName() + "\"," +
                "\"price\":\"" + productInCartItemDomain.getPrice() + "\"," +
                "\"nameDetail\":\"" + productInCartItemDomain.getNameDetail() + "\"," +
                "\"quantity\":\"" + productInCartItemDomain.getQuantity() + "\"" +
                "}";
    }

    public String toJsonOne(ProductInCartItemDomain productInCartItemDomain) {
        return "{" +
                "\"productId\":\"" + productInCartItemDomain.getProductId() + "\"," +
                "\"productTypeId\":\"" + productInCartItemDomain.getProductTypeId() + "\"," +
                "\"name\":\"" + productInCartItemDomain.getName() + "\"," +
                "\"image\":\"" + productInCartItemDomain.getImage() + "\"," +
                "\"price\":\"" + productInCartItemDomain.getPrice() + "\"," +
                "\"nameDetail\":\"" + productInCartItemDomain.getNameDetail() + "\"," +
                "\"quantity\":\"" + productInCartItemDomain.getQuantity() + "\"" +
                "}";
    }

    public String toJson(ProductInCartItemDomain productInCartItemDomain,String shopName, Long sellerId) {
        return "{" +
                "\"shopName\":\"" + shopName + "\"," +
                "\"sellerId\":\"" + sellerId + "\"," +
                "\"carts\":" +
                "[{" +
                "\"productId\":\"" + productInCartItemDomain.getProductId() + "\"," +
                "\"image\":\"" + productInCartItemDomain.getImage() + "\"," +
                "\"name\":\"" + productInCartItemDomain.getName() + "\"," +
                "\"price\":\"" + productInCartItemDomain.getPrice() + "\"," +
                "\"nameDetail\":\"" + productInCartItemDomain.getNameDetail() + "\"," +
                "\"quantity\":\"" + productInCartItemDomain.getQuantity() + "\"," +
                "}]" +
                "}";
    }

    public String toJson(ProductInCartItemDomain productInCartItemDomain,String str) {
        return "{" +
                "\"shopName\":\"" + productInCartItemDomain.getShopName() + "\"," +
                "\"sellerId\":\"" + productInCartItemDomain.getSellerId() + "\"," +
                "\"carts\":" + str + "}";
    }
}
