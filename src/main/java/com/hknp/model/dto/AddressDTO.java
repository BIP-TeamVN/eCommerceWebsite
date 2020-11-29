package com.hknp.model.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressDTO {
    Long addressId;
    String street;
    String communeId;
    String districtID;
    String provinceId;

    public AddressDTO(ResultSet resultSet) throws SQLException {
        addressId = resultSet.getLong("ADDRESS_ID");
        street = resultSet.getString("STREET");
        communeId = resultSet.getString("COMMUNE_ID");
        districtID = resultSet.getString("DISTRICT_ID");
        provinceId = resultSet.getString("PROVINCE_ID");
    }
}
