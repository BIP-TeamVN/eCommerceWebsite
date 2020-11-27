package com.hknp.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BrandModel {
    Long brandId;
    String brandName;
    String brandOrigin;

    public BrandModel (ResultSet resultSet) throws SQLException {
        brandId = resultSet.getLong("BRAND_ID");
        brandName = resultSet.getString("BRAND_NAME");
        brandOrigin = resultSet.getString("BRAND_ORIGIN");
    }
}
