package com.hknp.utils;

import java.math.BigDecimal;
import java.sql.*;

public class DatabaseUtils {
    private static void setParameters(PreparedStatement statement, Object... parameters) {
        try {
            for (int i = 0; i < parameters.length; i++) {
                if (parameters[i] instanceof Boolean) statement.setBoolean(i + 1, (Boolean) parameters[i]);
                else if (parameters[i] instanceof Byte) statement.setByte(i + 1, (Byte) parameters[i]);
                else if (parameters[i] instanceof Integer) statement.setInt(i + 1, (Integer) parameters[i]);
                else if (parameters[i] instanceof Float) statement.setFloat(i + 1, (Float) parameters[i]);
                else if(parameters[i] instanceof Long) statement.setLong(i +1, (Long) parameters[i]);
                else if (parameters[i] instanceof Double) statement.setDouble(i + 1, (Double) parameters[i]);
                else if (parameters[i] instanceof BigDecimal) statement.setBigDecimal(i +1, (BigDecimal)parameters[i]);
                else if (parameters[i] instanceof String) statement.setString(i + 1, (String) parameters[i]);
            }
        }
        catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public static ResultSet executeQuery(String sqlQuery, Object ... parameters) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseConnection.getConnection();
            if (connection != null) {
                preparedStatement = connection.prepareStatement(sqlQuery);
                setParameters(preparedStatement, parameters);
                resultSet = preparedStatement.executeQuery(sqlQuery);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //closeConnection(connection, preparedStatement);
        }
        return resultSet;
    }

    public static int executeUpdate(String sqlQuery, Object ... parameters) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int executeResult = 0;

        try {
            connection = DatabaseConnection.getConnection();
            if (connection != null) {
                preparedStatement = connection.prepareStatement(sqlQuery);
                setParameters(preparedStatement, parameters);
                executeResult = preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //closeConnection(connection, preparedStatement);
        }
        return executeResult;
    }
}
