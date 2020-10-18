package com.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HomePageController {

    @FXML Button btnClear, btnSubmit;
    @FXML TextField txtUsername, txtPassword;
    @FXML ChoiceBox userChoice;

    @FXML
    private void clearFields(){
        txtPassword.setText("");
        txtUsername.setText("");
    }

    @FXML
    private void userLogin() throws IOException {

        String userRole = (String) userChoice.getValue();

        Hotel.setLoggedUser(userRole);

        if(userRole.equals("Admin")){
            loadPage("Admin");
        }else{
            loadPage("Recep");
        }


    }

    public void errorMessage(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Invalid Credentials");
        alert.setHeaderText("User name or password entered is incorrect");
        alert.setContentText("Please enter the correct credentials");
        alert.showAndWait();
    }

    public void loadPage(String userType) throws IOException {

        String userName = txtUsername.getText();
        String password = txtPassword.getText();

        if(userName.equals(userType) && password.equals("123")){
            Stage newStage = new Stage();
            FXMLLoader fxmlLoader =   new FXMLLoader(MenuController.class.getClassLoader().getResource("MenuPage.fxml"));
            Parent root = fxmlLoader.load();
            Scene scn = new Scene(root, 932, 708);
            newStage.setScene(scn);
            newStage.show();

            Stage stage = (Stage) btnSubmit.getScene().getWindow();
            stage.close();

        }else {
            errorMessage();
        }
    }


}
