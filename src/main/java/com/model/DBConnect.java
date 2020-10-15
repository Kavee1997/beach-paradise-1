package com.model;

import  java.sql.Connection;
import  java.sql.DriverManager;
import  java.sql.ResultSet;
import java.sql.SQLException;


public class DBConnect{
   
    private static Connection connection;

    // initialize the Database properties    
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/beachparadisebd"; 
    
    //Database credentials
    static final String USER = "root";
    static final String PASS = "helloworld";
    
    private static void setConnection() throws ClassNotFoundException, SQLException {
        
        //Register the JDBC Driver
        Class.forName(JDBC_DRIVER);

        //Open the connection
        connection = DriverManager.getConnection(DB_URL,USER,PASS);   
    }


    public static ResultSet search(String sql) throws Exception {
        
        if (connection == null) {
            setConnection();
        }

        return connection.createStatement().executeQuery(sql);
    }
    
}