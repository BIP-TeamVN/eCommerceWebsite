package com.hknp.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Config JDBC Connection and get JDBC Connection
 */
public class DatabaseConnection {

   final static String url = "jdbc:mysql://localhost:3306/eCommerceWebsiteDb";
   final static String username = "root";
   final static String password = "0000";

   /**
    * Get MySql JDBC Connection
    *
    * @return Connection to MySql database or null
    */
   public static Connection getConnection() {
      try {
         Class.forName("com.mysql.jdbc.Driver");
         return DriverManager.getConnection(url, username, password);
      } catch (SQLException | ClassNotFoundException e) {
         e.printStackTrace();
      }
      return null;
   }
}
