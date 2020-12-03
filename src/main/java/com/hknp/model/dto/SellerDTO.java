package com.hknp.model.dto;

import com.hknp.model.dao.SellerCategoryDAO;
import com.hknp.model.dao.UserDAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SellerDTO {
    Long userId;
    UserDTO user;
    String storeName;
    String storeLink;
    String businessLicenseId;
    Long sellerCategoryId;
    SellerCategoryDTO sellerCategory;
    Long bankAccountId;

    public SellerDTO (ResultSet resultSet) {
        try {
            userId = resultSet.getLong("USER_ID");
            user = UserDAO.getInstance().getById(userId);
            storeName = resultSet.getString("STORE_NAME");
            storeLink = resultSet.getString("STORE_LINK");
            businessLicenseId = resultSet.getString("BUSINESS_LICENSE_ID");
            sellerCategoryId = resultSet.getLong("SELLER_CATEGORY_ID");
            sellerCategory = SellerCategoryDAO.getInstance().getById(sellerCategoryId);
            bankAccountId = resultSet.getLong("BANK_ACCOUNT_ID");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public SellerDTO(Long userId, UserDTO user, String storeName, String storeLink, String businessCategoryId, Long sellerCategoryId, SellerCategoryDTO sellerCategory, Long bankAccountId) {
        this.userId = userId;
        this.user = user;
        this.storeName = storeName;
        this.storeLink = storeLink;
        this.businessLicenseId = businessCategoryId;
        this.sellerCategoryId = sellerCategoryId;
        this.sellerCategory = sellerCategory;
        this.bankAccountId = bankAccountId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
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

    public void setBusinessLicenseId(String businessCategoryId) {
        this.businessLicenseId = businessCategoryId;
    }

    public Long getSellerCategoryId() {
        return sellerCategoryId;
    }

    public void setSellerCategoryId(Long sellerCategoryId) {
        this.sellerCategoryId = sellerCategoryId;
    }

    public SellerCategoryDTO getSellerCategory() {
        return sellerCategory;
    }

    public void setSellerCategory(SellerCategoryDTO sellerCategory) {
        this.sellerCategory = sellerCategory;
    }

    public Long getBankAccountId() {
        return bankAccountId;
    }

    public void setBankAccountId(Long bankAccountId) {
        this.bankAccountId = bankAccountId;
    }
}
