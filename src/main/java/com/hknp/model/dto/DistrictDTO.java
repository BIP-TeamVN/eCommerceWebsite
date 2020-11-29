package com.hknp.model.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DistrictDTO {
    String districtId;
    String districtName;
    String districtType;
    String provinceId;

    public DistrictDTO(ResultSet resultSet) throws SQLException{
        districtId = resultSet.getString("DISTRICT_ID");
        districtName = resultSet.getString("DISTRICT_NAME");
        districtType = resultSet.getString("DISTRICT_TYPE");
        provinceId = resultSet.getString("PROVINCE_ID");
    }
}
