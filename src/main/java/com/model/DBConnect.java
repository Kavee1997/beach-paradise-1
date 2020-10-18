package com.model;

import com.controller.Hotel;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import java.sql.*;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;


public class DBConnect{
   
    private static Connection connection;

    // initialize the Database properties    
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/beachparadisebd"; 
    
    //Database credentials
    static final String USER = "root";
    static final String PASS = "helloworld";

    public static void setConnection() throws ClassNotFoundException, SQLException {
        
        //Register the JDBC Driver
        Class.forName(JDBC_DRIVER);

        //Open the connection
        connection = DriverManager.getConnection(DB_URL,USER,PASS);
        System.out.println("Connected to DB");
    }

    public static void getCustomerInfo() throws SQLException {

        ObservableList<Customer> customers = Hotel.getCustomers();

        String sql;
        Statement statement = connection.createStatement();

        sql = "SELECT * FROM reservation";
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()){
            Customer customer = new Customer();

            customer.setCustomerReservationID(parseInt(resultSet.getString("res_no")));
            customer.setCustomerName(resultSet.getString("name"));
            customer.setCustomerMobileNumber(parseInt(resultSet.getString("mobile")));
            customer.setCustomerAddress(resultSet.getNString("address"));
            customer.setCustomerNIC(parseInt(resultSet.getString("nic")));
            customer.setCustomerCheckIn(resultSet.getString("check_in"));
            customer.setCustomerCheckOut(resultSet.getString("check_out"));
            customer.setRoomNumber(parseInt(resultSet.getString("room_no")));
            customer.setRoomBedType(resultSet.getString("bed_type"));
            customer.setRoomType(resultSet.getString("room_type"));
            customer.setRoomPrice(parseInt(resultSet.getString("amount")));

            customers.add(customer);
        }

        Hotel.setCustomers(customers);
    }

    public static void insertCustomerInfo(Customer customer) throws SQLException {
        String sql;

        sql = "INSERT into reservation (res_no, nic, name, address,mobile, check_in, check_out, room_type, room_no, bed_type, amount) values(" +
                +customer.getCustomerReservationID() +","+customer.getCustomerNIC()+ "," + "\"" +customer.getCustomerName()+ "\"" +","+"\"" +customer.getCustomerAddress()+"\""+"," +
                +customer.getCustomerMobileNumber()+","+"\""+customer.getCustomerCheckIn()+"\""+","+"\""+customer.getCustomerCheckOut()+"\""+","+"\""+customer.getRoomType()+"\""+"," +
                customer.getRoomNumber()+","+"\""+customer.getRoomBedType()+"\""+","+customer.getRoomPrice()+");";

        Statement statement = connection.createStatement();
        int resultSet = statement.executeUpdate(sql);
        System.out.println(resultSet);

    }


    public static void deleteReservationInfo(Customer customer) throws SQLException {
        String sql;
        sql = "DELETE FROM reservation WHERE res_no =" + customer.getCustomerReservationID();

        Statement statement = connection.createStatement();
        int resultSet = statement.executeUpdate(sql);
    }

    public static void updateReservationInfo(Customer customer) throws SQLException {
        String sql;

        sql = "UPDATE reservation SET name="+"\""+customer.getCustomerName()+"\""+",nic="+customer.getCustomerNIC()+",address="+"\""+customer.getCustomerAddress()+"\""+""+
                ",mobile="+customer.getCustomerMobileNumber()+",check_in="+"\""+customer.getCustomerCheckIn()+"\""+",check_out="+"\""+customer.getCustomerCheckOut()+"\""+""+
                ",room_type="+"\""+customer.getRoomType()+"\""+",room_no="+customer.getRoomNumber()+",bed_type="+"\""+customer.getRoomBedType()+"\""+",amount="+customer.getRoomPrice()+" "+
                "WHERE res_no="+customer.getCustomerReservationID()+";";

        System.out.println(sql);
        Statement statement = connection.createStatement();
        int resultSet = statement.executeUpdate(sql);
    }

}