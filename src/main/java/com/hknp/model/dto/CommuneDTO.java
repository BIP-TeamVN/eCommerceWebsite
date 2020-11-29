package com.hknp.model.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommuneDTO {
    String communeId;
    String communeName;
    String communeType;
    String districtId;

    public CommuneDTO(ResultSet resultSet) throws SQLException {
        communeId = resultSet.getString("COMMUNE_ID");
        communeName = resultSet.getString("COMMUNE_NAME");
        communeType = resultSet.getString("COMMUNE_TYPE");
        districtId = resultSet.getString("DISTRICT_ID");
    }
}
