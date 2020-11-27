package com.hknp.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressModel {
    Long addressId;
    String street;
    String communeId;
    String districtID;
    String provinceId;

    public AddressModel(ResultSet resultSet) throws SQLException {
        addressId = resultSet.getLong("ADDRESS_ID");
        street = resultSet.getString("STREET");
        communeId = resultSet.getString("COMMUNE_ID");
        districtID = resultSet.getString("DISTRICT_ID");
        provinceId = resultSet.getString("PROVINCE_ID");
    }
}
