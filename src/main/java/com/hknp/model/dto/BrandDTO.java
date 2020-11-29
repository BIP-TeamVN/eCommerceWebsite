package com.hknp.model.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BrandDTO {
    Long brandId;
    String brandName;
    String brandOrigin;

    public BrandDTO (ResultSet resultSet) throws SQLException {
        brandId = resultSet.getLong("BRAND_ID");
        brandName = resultSet.getString("BRAND_NAME");
        brandOrigin = resultSet.getString("BRAND_ORIGIN");
    }
}
