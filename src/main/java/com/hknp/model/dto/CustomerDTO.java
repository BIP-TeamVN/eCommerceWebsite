package com.hknp.model.dto;

import com.hknp.model.dao.UserDAO;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDTO {
    Long userId;
    UserDTO user;
    Date registerDate;

    public CustomerDTO (ResultSet resultSet) {
        try {
            userId = resultSet.getLong("USER_ID");
            user = UserDAO.getInstance().getById(userId);
            registerDate = resultSet.getDate("REGISTER_DATE");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public CustomerDTO(Long userId, UserDTO user, Date registerDate) {
        this.userId = userId;
        this.user = user;
        this.registerDate = registerDate;
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

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }
}
