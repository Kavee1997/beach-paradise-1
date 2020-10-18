package com.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    Stage newStage = new Stage();

    @FXML
    Button btnRestaurant, btnStaffDetails, btnReservation, btnAddRoom, btnLogout;

    @FXML
    private void staff() throws IOException {

        FXMLLoader fxmlLoader =   new FXMLLoader(MenuController.class.getClassLoader().getResource("EmployeePage.fxml"));
        Parent root = fxmlLoader.load();
        Scene scn = new Scene(root, 1343, 874);
        newStage.setScene(scn);
        newStage.show();

        Stage stage = (Stage) btnStaffDetails.getScene().getWindow();
        stage.close();

    }

    @FXML
    private void userLogout() throws IOException {

        FXMLLoader fxmlLoader =   new FXMLLoader(MenuController.class.getClassLoader().getResource("HomePage.fxml"));
        Parent root = fxmlLoader.load();
        Scene scn = new Scene(root, 851, 591);
        newStage.setScene(scn);
        newStage.show();

        Stage stage = (Stage) btnLogout.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void reservation() throws IOException {

        FXMLLoader fxmlLoader =   new FXMLLoader(MenuController.class.getClassLoader().getResource("ReservationPage.fxml"));
        Parent root = fxmlLoader.load();
        Scene scn = new Scene(root, 1343, 874);
        newStage.setScene(scn);
        newStage.show();

        Stage stage = (Stage) btnStaffDetails.getScene().getWindow();
        stage.close();

    }

    @FXML
    private void room() throws IOException {

        FXMLLoader fxmlLoader =   new FXMLLoader(MenuController.class.getClassLoader().getResource("RoomPage.fxml"));
        Parent root = fxmlLoader.load();
        Scene scn = new Scene(root, 729, 603);
        newStage.setScene(scn);
        newStage.show();

        Stage stage = (Stage) btnStaffDetails.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void restaurant() throws IOException {

        FXMLLoader fxmlLoader =   new FXMLLoader(MenuController.class.getClassLoader().getResource("RestaurantPage.fxml"));
        Parent root = fxmlLoader.load();
        Scene scn = new Scene(root, 1239, 891);
        newStage.setScene(scn);
        newStage.show();

        Stage stage = (Stage) btnStaffDetails.getScene().getWindow();
        stage.close();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        String userRole = Hotel.getLoggedUser();
        if(userRole.equals("Receptionist")){
            btnStaffDetails.setVisible(false);
        }
    }
}
