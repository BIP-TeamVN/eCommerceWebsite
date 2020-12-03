package com.hknp.model.dto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class UserDTO {
    Long userId;
    String lastName;
    String firstName;
    String gender;
    Date dateOfBirth;
    String ssn;
    String imagePath;
    String phoneNumber;
    String email;
    String userName;
    String password;
    String userType;
    Boolean status;

    public UserDTO(ResultSet resultSet) {
        try {
            userId = resultSet.getLong("USER_ID");
            lastName = resultSet.getString("LAST_NAME");
            firstName = resultSet.getString("FIRST_NAME");
            gender = resultSet.getString("GENDER");
            dateOfBirth = resultSet.getDate("DATE_OF_BIRTH");
            ssn = resultSet.getString("SSN");
            imagePath = resultSet.getString("IMAGE_PATH");
            phoneNumber = resultSet.getString("PHONE_NUMBER");
            email = resultSet.getString("EMAIL");
            userName = resultSet.getString("USER_NAME");
            password = resultSet.getString("PASSWORD");
            userType = resultSet.getString("USER_TYPE");
            status = resultSet.getBoolean("STATUS");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public UserDTO(Long userId, String lastName, String firstName, String gender, Date dateOfBirth, String ssn, String imagePath, String phoneNumber, String email, String userName, String password, String userType, Boolean status) {
        this.userId = userId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.ssn = ssn;
        this.imagePath = imagePath;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.userType = userType;
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
