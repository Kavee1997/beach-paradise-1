package com.controller;
import java.util.ArrayList;

import com.model.Customer;
import com.model.DBConnect;
import com.model.Room;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Objects;

public class Hotel extends Application {

    private static final String HOTEL_NAME = "Beach Paradise";
   
    private static final int NUMBER_OF_ROOMS = 25; 
    private static Room[] rooms = new Room[NUMBER_OF_ROOMS];
    public static String loggedUser;

    private static ObservableList<Customer> customers = FXCollections.observableArrayList();
    private static ArrayList<Customer> employees = new ArrayList<Customer>();

    public static ObservableList<Customer> getCustomers() {
        return customers;
    }

    public static void setCustomers(ObservableList<Customer> customers) {
        Hotel.customers = customers;
    }

    public static ArrayList<Customer> getEmployees() {
        return employees;
    }

    public static void setEmployees(ArrayList<Customer> employees) {
        Hotel.employees = employees;
    }

    public static void setLoggedUser(String loggedUser) {
        Hotel.loggedUser = loggedUser;
    }

    public static String getLoggedUser() {
        return loggedUser;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        DBConnect.setConnection();
        DBConnect.getCustomerInfo();

        Stage stage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(EmployeeController.class.getClassLoader().getResource("HomePage.fxml")));
        Scene scene = new Scene(root, 851, 591);
        stage.setScene(scene);
        stage.showAndWait();

    }

    public static void main(String[] arguments) { launch(arguments); }

}
