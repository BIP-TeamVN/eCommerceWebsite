package com.hknp.model.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAddressDTO {
    Long userId;
    Long addressId;
    String fullName;
    String phoneNumber;
    String addressName;

    public UserAddressDTO(ResultSet resultSet) {
        try {
            userId = resultSet.getLong("USER_ID");
            addressId = resultSet.getLong("ADDRESS_ID");
            fullName = resultSet.getString("FULL_NAME");
            phoneNumber = resultSet.getString("PHONE_NUMBER");
            addressName = resultSet.getString("ADDRESS_NAME");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public UserAddressDTO(Long userId, Long addressId, String fullName, String phoneNumber, String addressName) {
        this.userId = userId;
        this.addressId = addressId;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.addressName = addressName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }
}
