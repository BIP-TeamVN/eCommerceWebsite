package com.hknp.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class UserModel {
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

    public UserModel(ResultSet resultSet) throws SQLException {
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
    }
}
