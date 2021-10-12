package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn {

   // oracle
   private  static  String  driver = "oracle.driver.OracleDriver";
   private  static  String  url    = "jdbc:oracle:thin:@localhost:1521:xe";
   
   // mysql
   //private  String  driver = "com.mysql.jdbc.Driver";
   //private  String  url    = "jdbc:mysql://127.0.0.1:3306/dbuser";  // dbuser - 데이터베이스 파일
   
   private  static  String  dbuid  = "test";
   private  static  String  dbpwd  = "1234";
      
   private  static  Connection    conn = null;
   
   // 생성자 : 프로그램에서 한번만 new  할 수 있도록 private 생성자이용
   // singleton 패턴 - getInstance();
   private  DBConn () {      
   }
   
   //public  static  Connection   getConnection() {
   public  static  Connection   getInstance() {
      if( conn != null )
         return conn;
      
      try {
         Class.forName(driver);
         conn  =   DriverManager.getConnection(url, dbuid, dbpwd);
      } catch (ClassNotFoundException e) {
         e.printStackTrace();
      } catch (SQLException e) {
         e.printStackTrace();
      }
      
      return  conn;
   }
   
   
   
}












