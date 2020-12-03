package com.hknp.model.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DistrictDTO {
    String districtId;
    String districtName;
    String districtType;
    String provinceId;

    public DistrictDTO(ResultSet resultSet) {
        try {
            districtId = resultSet.getString("DISTRICT_ID");
            districtName = resultSet.getString("DISTRICT_NAME");
            districtType = resultSet.getString("DISTRICT_TYPE");
            provinceId = resultSet.getString("PROVINCE_ID");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public DistrictDTO(String districtId, String districtName, String districtType, String provinceId) {
        this.districtId = districtId;
        this.districtName = districtName;
        this.districtType = districtType;
        this.provinceId = provinceId;
    }

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getDistrictType() {
        return districtType;
    }

    public void setDistrictType(String districtType) {
        this.districtType = districtType;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }
}
