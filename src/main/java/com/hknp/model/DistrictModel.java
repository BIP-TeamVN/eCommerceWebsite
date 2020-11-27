package com.hknp.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DistrictModel {
    String districtId;
    String districtName;
    String districtType;
    String provinceId;

    public DistrictModel(ResultSet resultSet) throws SQLException{
        districtId = resultSet.getString("DISTRICT_ID");
        districtName = resultSet.getString("DISTRICT_NAME");
        districtType = resultSet.getString("DISTRICT_TYPE");
        provinceId = resultSet.getString("PROVINCE_ID");
    }
}
