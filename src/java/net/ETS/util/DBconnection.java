/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ETS.util;

import java.sql.*;

/**
 *
 * @author Nilesh Singh
 */
public class DBconnection implements java.io.Serializable {

//    private static String url = "jdbc:mysql://localhost:3306/ETS";
//    private static String driverName = "com.mysql.jdbc.Driver";
//    private static String usernsme = "root";
//    private static String pasword = "triala";
      private static Connection con;

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try {
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ets", "root","admin");
            } catch (SQLException ex) {
                // log an exception. fro example:
                System.out.println("Failed to create the database connection.");
            }
        } catch (ClassNotFoundException ex) {
            // log an exception. for example:
            System.out.println("Driver not found.");
        }
        System.out.println("connection Created");
        return con;
    }

//public static void main(String ARGS[]){
//  java.sql.Connection con=getConnection();
//  if(con!=null){
//      System.out.println("connection created");
//  }
//  
//}

}


