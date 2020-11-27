package com.hknp.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommuneModel {
    String communeId;
    String communeName;
    String communeType;
    String districtId;

    public CommuneModel(ResultSet resultSet) throws SQLException {
        communeId = resultSet.getString("COMMUNE_ID");
        communeName = resultSet.getString("COMMUNE_NAME");
        communeType = resultSet.getString("COMMUNE_TYPE");
        districtId = resultSet.getString("DISTRICT_ID");
    }
}
