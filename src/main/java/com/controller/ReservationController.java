package com.controller;

import com.model.Customer;
import com.model.DBConnect;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.time.format.DateTimeFormatter;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

import static java.lang.Integer.parseInt;

public class ReservationController implements Initializable, EventHandler<ActionEvent>{

    @FXML Button btnReservation, btnDeleteReservation, btnUpdateReservation, btnSearch;
    @FXML private TextField txtReservationNo, txtCustomerName, txtCustomerAddress, txtCustomerMobile, txtRoomNo, txtAmount, txtCustomerNIC, txtSearchField;
    @FXML private DatePicker checkIn, checkOut;
    @FXML private ChoiceBox roomType, bedType;
    @FXML private TableView<Customer> tableView;
    @FXML private TableColumn<Customer, String> customerNameColumn;
    @FXML private TableColumn<Customer, Integer> customerNIC;
    @FXML private TableColumn<Customer, Integer> customerReservationIDColumn;
    @FXML private TableColumn<Customer, String> customerAddressColumn;
    @FXML private TableColumn<Customer, Integer> customerMobileColumn;
    @FXML private TableColumn<Customer, Integer> customerRoomNoColumn;
    @FXML private TableColumn<Customer, Integer> customerAmountColumn;
    @FXML private TableColumn<Customer, String> customerRoomTypeColumn;
    @FXML private TableColumn<Customer, String> customerBedTypeColumn;
    @FXML private TableColumn<Customer, String> customerCheckInColumn;
    @FXML private TableColumn<Customer, String> customerCheckOutColumn;

    // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("");
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
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



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        customerNameColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerName"));
        customerReservationIDColumn.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("customerReservationID"));
        customerAddressColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerAddress"));
        customerMobileColumn.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("customerMobileNumber"));
        customerRoomNoColumn.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("roomNumber"));
        customerAmountColumn.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("roomPrice"));
        customerRoomTypeColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("roomType"));
        customerBedTypeColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("roomBedType"));
        customerCheckInColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerCheckIn"));
        customerCheckOutColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerCheckOut"));
        customerNIC.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("customerNIC"));
        tableView.setItems(Hotel.getCustomers());

        tableView.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                Customer customer = tableView.getSelectionModel().getSelectedItem();
                int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
                txtReservationNo.setText(String.valueOf(customer.getCustomerReservationID()));
                txtCustomerNIC.setText(String.valueOf(customer.getCustomerNIC()));
                txtCustomerName.setText(customer.getCustomerName());
                txtCustomerAddress.setText(customer.getCustomerAddress());
                txtCustomerMobile.setText(String.valueOf(customer.getCustomerMobileNumber()));
                txtRoomNo.setText(String.valueOf(customer.getRoomNumber()));
                txtAmount.setText(String.valueOf(customer.getRoomPrice()));
                roomType.setValue(customer.getRoomType());
                bedType.setValue(customer.getRoomBedType());
            }
        });
    }


    @FXML
    public Customer roomReservation() throws SQLException {

        Customer customer = new Customer();

        int ReservationNo = 0;
        try{
            ReservationNo =  parseInt(txtReservationNo.getText());
        }catch (Exception ignored){}
        customer.setCustomerReservationID(ReservationNo);

        String customerName = txtCustomerName.getText();
        customer.setCustomerName(customerName);

        int nic = 0;
        try{
            nic = parseInt(txtCustomerNIC.getText());
        }catch (Exception ignored){};

        customer.setCustomerNIC(nic);

        String customerAddress = txtCustomerAddress.getText();
        customer.setCustomerAddress(customerAddress);

        int customerMobile = 0;
        try {
            customerMobile = parseInt(txtCustomerMobile.getText());
        }catch (Exception ignored){}
        customer.setCustomerMobileNumber(customerMobile);

        int roomNo = 0;
        try{
            roomNo = parseInt(txtRoomNo.getText());
        }catch (Exception ignored){}
        customer.setRoomNumber(roomNo);

        int amount = 0;
        try{
            amount = parseInt(txtAmount.getText());
        } catch (Exception ignored){}
        customer.setRoomPrice(amount);

        String customerCheckIn = null;
        try{customerCheckIn = formatter.format(checkIn.getValue());
        }catch (Exception ignored){}
        customer.setCustomerCheckIn(customerCheckIn);
        System.out.println(checkIn);
        String customerCheckOut = null;
        try{
            customerCheckOut = formatter.format(checkOut.getValue());
        }catch (Exception ignored){}
        customer.setCustomerCheckOut(customerCheckOut);

        String customerRoomType = (String) roomType.getValue();
        customer.setRoomType(customerRoomType);

        String customerBedType = (String) bedType.getValue();
        customer.setRoomBedType(customerBedType);

        ObservableList<Customer> customers = Hotel.getCustomers();
        customers.add(customer);
        Hotel.setCustomers(customers);

        DBConnect.insertCustomerInfo(customer);
        return customer;

    }

    @FXML
    public void deleteReservation() throws SQLException {

            Customer customer = tableView.getSelectionModel().getSelectedItem();

            int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                tableView.getItems().remove(selectedIndex);
                System.out.println(customer.getCustomerReservationID());
                DBConnect.deleteReservationInfo(customer);
            } else {
                // Nothing selected.
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("No Selection");
                alert.setHeaderText("No Reservation Selected");
                alert.setContentText("Please select a Reservation in the table.");
                alert.showAndWait();
            }
    }

    @FXML
    public void updateReservation() throws SQLException {

        Customer customer = new Customer();

        int ReservationNo = 0;
        try{
            ReservationNo =  parseInt(txtReservationNo.getText());
        }catch (Exception ignored){}
        customer.setCustomerReservationID(ReservationNo);

        String customerName = txtCustomerName.getText();
        customer.setCustomerName(customerName);

        int nic = 0;
        try{
            nic = parseInt(txtCustomerNIC.getText());
        }catch (Exception ignored){};

        customer.setCustomerNIC(nic);

        String customerAddress = txtCustomerAddress.getText();
        customer.setCustomerAddress(customerAddress);

        int customerMobile = 0;
        try {
            customerMobile = parseInt(txtCustomerMobile.getText());
        }catch (Exception ignored){}
        customer.setCustomerMobileNumber(customerMobile);

        int roomNo = 0;
        try{
            roomNo = parseInt(txtRoomNo.getText());
        }catch (Exception ignored){}
        customer.setRoomNumber(roomNo);

        int amount = 0;
        try{
            amount = parseInt(txtAmount.getText());
        } catch (Exception ignored){}
        customer.setRoomPrice(amount);

        String customerCheckIn = null;
        try{customerCheckIn = formatter.format(checkIn.getValue());
        }catch (Exception ignored){}
        customer.setCustomerCheckIn(customerCheckIn);

        System.out.println(checkIn);

        String customerCheckOut = null;
        try{
            customerCheckOut = formatter.format(checkOut.getValue());
        }catch (Exception ignored){}
        customer.setCustomerCheckOut(customerCheckOut);

        String customerRoomType = (String) roomType.getValue();
        customer.setRoomType(customerRoomType);

        String customerBedType = (String) bedType.getValue();
        customer.setRoomBedType(customerBedType);

        DBConnect.updateReservationInfo(customer);
        Hotel.getCustomers().clear();
        DBConnect.getCustomerInfo();

//        Hotel.getCustomers().addListener(new ListChangeListener<Customer>() {
//            @Override
//            public void onChanged(Change<? extends Customer> c) {
//                while (c.next()){
//                }
//            }
//        });
//
    }



    @FXML
    public void clear(){

        txtReservationNo.setText("");
        txtCustomerNIC.setText("");
        txtCustomerName.setText("");
        txtCustomerAddress.setText("");
        txtCustomerMobile.setText("");
        txtRoomNo.setText("");
        txtAmount.setText("");
        checkOut.setValue(null);
        checkIn.setValue(null);
    }


    @Override
    public void handle(ActionEvent event) {

    }

    @FXML
    public void searchCustomer(){
        ObservableList customers = Hotel.getCustomers();
        String userInput = txtSearchField.getText();
        final Customer[] foundCustomer = {new Customer()};
        customers.forEach((customer) -> {
            Customer cs = (Customer) customer;
            if(cs.getCustomerName().equals(userInput)){
                foundCustomer[0] = cs;
            }
        });

        if(!foundCustomer[0].getCustomerName().equals(null)){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Customer Information");
            alert.setHeaderText(null);
            alert.setContentText("Customer name: "+foundCustomer[0].getCustomerName()+"\n"+"" +
                    "Customer Address: "+ foundCustomer[0].getCustomerAddress()+"\n"+"" +
                    "Customer Mobile: "+ foundCustomer[0].getCustomerMobileNumber()+"\n"+"");
            alert.showAndWait();
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Customer didn't found");
            alert.setHeaderText(null);
            alert.setContentText("Under the provided name there aren't any customer");

            alert.showAndWait();
        }
    }
}
