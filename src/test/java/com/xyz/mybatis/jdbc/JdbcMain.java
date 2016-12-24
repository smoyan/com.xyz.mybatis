/**
 * 
 */

package com.xyz.mybatis.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * @author lee
 *
 */
public class JdbcMain {

  public static void main(String[] args) throws Exception {
    String sql = "select * from t_user";
    ResultSet rs = executeSql(sql);
    System.out.println(rs);
    while (rs.next()) {
      System.out.println(rs.getObject(1) + "," + rs.getObject(2) + "," + rs.getObject(3) + ", " + rs.getObject(4));
    }
  }

  public static ResultSet executeSql(String sql) {
    Connection conn = getConnection();
    PreparedStatement ps = null;
    try {
      ps = conn.prepareStatement(sql);

      ResultSetMetaData metaData = ps.getMetaData();
      for (int i = 0; i < metaData.getColumnCount(); i++) {
        System.out.println(metaData.getColumnClassName(1) + "," + metaData.getColumnLabel(1) + metaData.getColumnTypeName(1));
      }
      return ps.executeQuery();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        // conn.close();
        // ps.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return null;
  }

  private static Connection getConnection() {
    try {
      Class.forName("com.mysql.jdbc.Driver");
      String username = "root";
      String passwd = "kuche123";
      String database = "app";
      String url = "jdbc:mysql://localhost:58885/" + database + "?user=" + username + "&password=" + passwd;
      Connection con = DriverManager.getConnection(url);
      return con;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}
