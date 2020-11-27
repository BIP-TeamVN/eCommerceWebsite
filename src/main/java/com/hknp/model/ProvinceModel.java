package com.hknp.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProvinceModel {
    String provinceId;
    String provinceName;
    String provinceType;

    public ProvinceModel (ResultSet resultSet) throws SQLException {
        provinceId = resultSet.getString("PROVINCE_ID");
        provinceName = resultSet.getString("PROVINCE_NAME");
        provinceType = resultSet.getString("PROVINCE_TYPE");
    }
}
