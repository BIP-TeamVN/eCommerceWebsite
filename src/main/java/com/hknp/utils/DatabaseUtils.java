package com.hknp.utils;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseUtils {
    private static void setParameters(PreparedStatement statement, List<Object> parameters) {
        if (parameters == null) {
            return;
        }
        try {
            for (int i = 0; i < parameters.size(); i++) {
                Object para = parameters.get(i);

                if (para instanceof Boolean) statement.setBoolean(i + 1, (Boolean) para);
                else if (para instanceof Byte) statement.setByte(i + 1, (Byte) para);
                else if (para instanceof Integer) statement.setInt(i + 1, (Integer) para);
                else if (para instanceof Float) statement.setFloat(i + 1, (Float) para);
                else if (para instanceof Long) statement.setLong(i + 1, (Long) para);
                else if (para instanceof Double) statement.setDouble(i + 1, (Double) para);
                else if (para instanceof BigDecimal) statement.setBigDecimal(i + 1, (BigDecimal) para);
                else if (para instanceof String) statement.setString(i + 1, (String) para);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    private static PreparedStatement getPreparedStatement(Connection connection, String sqlQuery, List<Object> parameters) {
        PreparedStatement preparedStatement = null;
        if (connection != null) {
            try {
                preparedStatement = connection.prepareStatement(sqlQuery);
                setParameters(preparedStatement, parameters);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return preparedStatement;
    }

    public static ResultSet executeQuery(String sqlQuery, List<Object> parameters) {
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement prepStmt = null;
        ResultSet rs = null;

        if (conn == null) {
            return rs;
        }
        try {
            prepStmt = conn.prepareStatement(sqlQuery);
            if (parameters != null) {
                setParameters(prepStmt, parameters);
            }
            rs = prepStmt.executeQuery(sqlQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public static int executeUpdate(String sqlQuery, List<Object> parameters) {
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement prepStmt = null;
        int executeResult = 0;

        if (conn == null) {
            return executeResult;   // 0
        }

        try {
            prepStmt = conn.prepareStatement(sqlQuery);
            if (parameters != null) {
                setParameters(prepStmt, parameters);
            }
            executeResult = prepStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return executeResult;
    }

    /*under construct*/
    public static int executeUpdateTransaction(List<String> listSqlQuery, List<List<Object>> listParameters) {
        int executeResult = 0;
        Connection connection = DatabaseConnection.getConnection();

        if (connection != null) {
            try {
                connection.setAutoCommit(false);

                PreparedStatement statement = null;
                for (int i = 0; i < listSqlQuery.size(); i++) {
                    statement = getPreparedStatement(connection, listSqlQuery.get(i), listParameters.get(i));
                    executeResult += statement.executeUpdate();
                    statement.close();
                }
                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
                executeResult = 0;
            } finally {
                try {
                    connection.close();
                } catch (SQLException troubles) {
                    troubles.printStackTrace();
                }
            }
        }
        return executeResult;
    }
}
