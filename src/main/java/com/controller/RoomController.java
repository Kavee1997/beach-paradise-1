package com.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class RoomController {

    @FXML
    Button btnHome;


    @FXML
    private void home() throws IOException {

        Stage newStage = new Stage();
        FXMLLoader fxmlLoader =   new FXMLLoader(MenuController.class.getClassLoader().getResource("MenuPage.fxml"));
        Parent rt = fxmlLoader.load();
        Scene scn = new Scene(rt, 932, 708);
        newStage.setScene(scn);
        newStage.show();

        Stage stage = (Stage) btnHome.getScene().getWindow();
        stage.close();
    }


}
