package com.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static java.lang.Integer.parseInt;

public class RestaurantController {

    @FXML TextArea txtArea;
    @FXML Button btnHome, btnClear, btnTotal, btnReceipt;
    @FXML TextField txtRice, txtNoodles, txtBurger, txtFrenchFries, txtPasta, txtCoffee, txtMilkShake, txtHotChoco, txtIcedTea, txtFaluda;
    @FXML TextField txtCostOfMeals, txtCostOfDrinks, txtTotalCost, txtTax, txtSubTotal, txtTotal;

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


    @FXML
    public void clear(){
        txtRice.setText(null);
        txtNoodles.setText(null);
        txtBurger.setText(null);
        txtFrenchFries.setText(null);
        txtPasta.setText(null);
        txtCoffee.setText(null);
        txtMilkShake.setText(null);
        txtHotChoco.setText(null);
        txtIcedTea.setText(null);
        txtFaluda.setText(null);
        txtCostOfMeals.setText(null);
        txtCostOfDrinks.setText(null);
        txtTotalCost.setText(null);
        txtTax.setText(null);
        txtSubTotal.setText(null);
        txtTotal.setText(null);
        txtArea.setText("");
    }

    @FXML
    public int[] totalBill(){

        int[] billSum = new int[5];

        int totalAmount = 0;
        int tax = 0;
        int finalAmount = 0;
        int rice = 0;
        int noodles = 0;
        int burger = 0;
        int frenchFries = 0;
        int pasta = 0;
        int coffee = 0;
        int milkShake = 0;
        int hotChoco = 0;
        int icedTea = 0;
        int faluda = 0;

            try {
                rice = parseInt(txtRice.getText());
            }catch (Exception ignored){ }

            try {
                noodles = parseInt(txtNoodles.getText());
            }catch (Exception ignored){ }

            try {
                burger = parseInt(txtBurger.getText());
            }catch (Exception ignored){ }

            try {
                frenchFries = parseInt(txtFrenchFries.getText());
            }catch (Exception ignored){ }

            try {
                pasta = parseInt(txtPasta.getText());
            }catch (Exception ignored){ }

            try {
                coffee = parseInt(txtCoffee.getText());
            }catch (Exception ignored){ }

            try {
                milkShake = parseInt(txtMilkShake.getText());
            }catch (Exception ignored){ }

            try {
                hotChoco = parseInt(txtHotChoco.getText());
            }catch (Exception ignored){ }

            try {
                icedTea = parseInt(txtIcedTea.getText());
            }catch (Exception ignored){ }

            try {
                faluda = parseInt(txtFaluda.getText());
            }catch (Exception ignored){ }


        totalAmount = rice + noodles + burger + pasta + frenchFries + coffee + milkShake + hotChoco + icedTea + faluda;
        billSum[2] = totalAmount;

        txtTotalCost.setText(String.valueOf(totalAmount));
        txtSubTotal.setText(String.valueOf(totalAmount));

        txtCostOfMeals.setText(String.valueOf(rice + noodles + burger + pasta + frenchFries));
        billSum[0] = rice + noodles + burger + pasta + frenchFries;

        txtCostOfDrinks.setText(String.valueOf(coffee + milkShake + hotChoco + icedTea + faluda));
        billSum[1] = coffee + milkShake + hotChoco + icedTea + faluda;

        try {
            tax = parseInt(txtTax.getText());
            billSum[3] = tax;
        }catch (Exception ignored){
            billSum[3] = 0;
        }

        if(tax == 0){
            finalAmount = totalAmount;
            billSum[4] = finalAmount;
        }else {
            finalAmount = totalAmount + (totalAmount * tax/100);
            billSum[4] = finalAmount;
        }

        txtTotal.setText(String.valueOf(finalAmount));

        return billSum;
    }

    @FXML
    public void receipt(){

        int[] billSum = totalBill();

        txtArea.setText("Beach Paradise Restaurant\n" +
                "____________________________\n\n" +
                "Receipt for the purchasing\n\n" +
                "Total cost for Meals: " + billSum[0] + " \n\n" +
                "Total cost for Drinks: " + billSum[1] + " \n\n" +
                "Sub Total bill value : " + billSum[2] + " \n\n" +
                "Tax amount percentage: " + billSum[3]+"% \n\n" +
                "Total bill value: " + billSum[4]
                );
    }
}

