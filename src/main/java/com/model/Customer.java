package com.model;

public class Customer {

    private String customerName;
    private String customerAddress;
    private String customerCheckIn;
    private String customerCheckOut;
    private String roomType;
    private String roomBedType;

    
    private int customerMobileNumber;
    private int roomPrice;
    private int roomNumber;
    private int customerReservationID;
    private int customerNIC;

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public String getCustomerCheckIn() {
        return customerCheckIn;
    }

    public String getCustomerCheckOut() {
        return customerCheckOut;
    }

    public String getRoomType() {
        return roomType;
    }

    public String getRoomBedType() {
        return roomBedType;
    }

    public int getCustomerMobileNumber() {
        return customerMobileNumber;
    }

    public int getRoomPrice() {
        return roomPrice;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public int getCustomerReservationID() {
        return customerReservationID;
    }

    public int getCustomerNIC() {
        return customerNIC;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public void setCustomerCheckIn(String customerCheckIn) {
        this.customerCheckIn = customerCheckIn;
    }

    public void setCustomerCheckOut(String customerCheckOut) {
        this.customerCheckOut = customerCheckOut;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public void setRoomBedType(String roomBedType) {
        this.roomBedType = roomBedType;
    }

    public void setCustomerMobileNumber(int customerMobileNumber) {
        this.customerMobileNumber = customerMobileNumber;
    }

    public void setRoomPrice(int roomPrice) {
        this.roomPrice = roomPrice;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setCustomerReservationID(int customerReservationID) {
        this.customerReservationID = customerReservationID;
    }

    public void setCustomerNIC(int customerNIC) {
        this.customerNIC = customerNIC;
    }
}

