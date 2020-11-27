package com.hknp.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductTypeModel {
    Long productTypeId;
    String productTypeName;
    Long productId;

    public ProductTypeModel (ResultSet resultSet) throws SQLException {
        productTypeId = resultSet.getLong("PRODUCT_TYPE_ID");
        productTypeName = resultSet.getString("PRODUCT_TYPE_NAME");
        productId = resultSet.getLong("PRODUCT_ID");
    }
}
