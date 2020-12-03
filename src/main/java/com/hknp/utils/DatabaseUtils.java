package com.hknp.utils;

import java.math.BigDecimal;
import java.sql.*;
import java.util.List;

public class DatabaseUtils {
   /**
    * Set parameters for PreparedStatement
    *
    * @param prepStmt   PreparedStatement to set parameters
    * @param parameters List&#60;Object&#62;
    */
   private static void setParameters(PreparedStatement prepStmt, List<Object> parameters) {
      if (parameters == null) {
         return;
      }
      try {
         for (int i = 0; i < parameters.size(); i++) {
            Object para = parameters.get(i);

            if (para instanceof Boolean) prepStmt.setBoolean(i + 1, (Boolean) para);
            else if (para instanceof Byte) prepStmt.setByte(i + 1, (Byte) para);
            else if (para instanceof Integer) prepStmt.setInt(i + 1, (Integer) para);
            else if (para instanceof Float) prepStmt.setFloat(i + 1, (Float) para);
            else if (para instanceof Long) prepStmt.setLong(i + 1, (Long) para);
            else if (para instanceof Double) prepStmt.setDouble(i + 1, (Double) para);
            else if (para instanceof BigDecimal) prepStmt.setBigDecimal(i + 1, (BigDecimal) para);
            else if (para instanceof String) prepStmt.setString(i + 1, (String) para);
         }
      } catch (SQLException exception) {
         exception.printStackTrace();
      }
   }

   /**
    * Executes the given SQL statement, which returns a single ResultSet object.
    *
    * @param sqlQuery   an SQL statement to be sent to the database, typically a static SQL SELECT statement
    * @param parameters list parameter value
    * @return a ResultSet object that contains the data produced by the given query
    */
   public static ResultSet executeQuery(String sqlQuery, List<Object> parameters) {
      Connection conn = DatabaseConnection.getConnection();
      PreparedStatement prepStmt;
      ResultSet rs = null;

      if (conn == null) {
         return null;
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

   /**
    * Executes the SQL statement in this PreparedStatement object, which must be an SQL Data Manipulation Language (DML) statement, such as INSERT, UPDATE or DELETE; or an SQL statement that returns nothing, such as a DDL statement.
    *
    * @param sqlQuery   an SQL statement to be sent to the database, typically a static SQL SELECT statement
    * @param parameters list parameter value
    * @return either (1) the row count for SQL Data Manipulation Language (DML) statements or (2) 0 for SQL statements that return nothing
    */
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
      } finally {
         if (prepStmt != null) {
            try {
               prepStmt.close();
            } catch (SQLException ex) {
               // ignore
            }
         }
      }

      return executeResult;
   }

   /**
    * Executes the SQL statement in this PreparedStatement object, which must be an SQL Data Manipulation Language (DML) statement, such as INSERT, UPDATE or DELETE; or an SQL statement that returns nothing, such as a DDL statement.
    *
    * @param sqlQuery   an SQL statement to be sent to the database, typically a static SQL SELECT statement
    * @param parameters list parameter value
    * @return either (1) Auto Increment id or (2) null for SQL statements that return nothing
    */
   public static Object executeUpdateAutoIncrement(String sqlQuery, List<Object> parameters) {
      Connection conn = DatabaseConnection.getConnection();
      PreparedStatement prepStmt = null;
      ResultSet rs = null;
      Object autoIncKeyFromApi = null;

      if (conn == null) {
         return null;   // 0
      }

      try {
         prepStmt = conn.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
         if (parameters != null) {
            setParameters(prepStmt, parameters);
         }
         if (prepStmt.executeUpdate() != 0) {
            rs = prepStmt.getGeneratedKeys();
            if (rs.next()) {
               autoIncKeyFromApi = rs.getObject(1);
            }
         }
      } catch (SQLException exception) {
         exception.printStackTrace();
      } finally {
         if (rs != null) {
            try {
               rs.close();
            } catch (SQLException ex) {
               ex.printStackTrace();
            }
         }

         if (prepStmt != null) {
            try {
               prepStmt.close();
            } catch (SQLException ex) {
               ex.printStackTrace();
            }
         }
      }
      return autoIncKeyFromApi;
   }

   /*under construct*/
   /*public static int executeUpdateTransaction(List<String> listSqlQuery, List<List<Object>> listParameters) {
      int executeResult = 0;
      Connection connection = DatabaseConnection.getConnection();

      if (connection != null) {
         try {
            connection.setAutoCommit(false);

            PreparedStatement statement = null;
            for (int i = 0; i < listSqlQuery.size(); i++) {
               statement = connection.prepareStatement(listSqlQuery.get(i));
               setParameters(statement, listParameters.get(i));
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
   }*/
}
