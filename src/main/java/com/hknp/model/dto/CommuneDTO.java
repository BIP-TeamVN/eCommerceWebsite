package com.hknp.model.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommuneDTO {
    String communeId;
    String communeName;
    String communeType;
    String districtId;

    public CommuneDTO(ResultSet resultSet) {
        try {
            communeId = resultSet.getString("COMMUNE_ID");
            communeName = resultSet.getString("COMMUNE_NAME");
            communeType = resultSet.getString("COMMUNE_TYPE");
            districtId = resultSet.getString("DISTRICT_ID");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public CommuneDTO(String communeId, String communeName, String communeType, String districtId) {
        this.communeId = communeId;
        this.communeName = communeName;
        this.communeType = communeType;
        this.districtId = districtId;
    }

    public String getCommuneId() {
        return communeId;
    }

    public void setCommuneId(String communeId) {
        this.communeId = communeId;
    }

    public String getCommuneName() {
        return communeName;
    }

    public void setCommuneName(String communeName) {
        this.communeName = communeName;
    }

    public String getCommuneType() {
        return communeType;
    }

    public void setCommuneType(String communeType) {
        this.communeType = communeType;
    }

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }
}
