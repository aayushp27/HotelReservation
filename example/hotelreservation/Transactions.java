package com.example.hotelreservation;

public class Transactions {

    private String full_name, user_email, client_email, hotel_name, hotel_place, date_1, date_2, hotel_price, num_of_rooms, status, UID;
    private String hotel_image;


    public Transactions() {

    }

    public Transactions(String full_name, String user_email, String client_email, String hotel_name, String hotel_place, String date_1, String date_2, String hotel_price, String num_of_rooms, String status, String hotel_image, String UID) {
        this.full_name = full_name;
        this.user_email = user_email;
        this.client_email = client_email;
        this.hotel_name = hotel_name;
        this.hotel_place = hotel_place;
        this.date_1 = date_1;
        this.date_2 = date_2;
        this.hotel_price = hotel_price;
        this.num_of_rooms = num_of_rooms;
        this.status = status;
        this.hotel_image = hotel_image;
        this.UID = UID;

    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getClient_email() {
        return client_email;
    }

    public void setClient_email(String client_email) {
        this.client_email = client_email;
    }

    public String getHotel_name() {
        return hotel_name;
    }

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    public String getHotel_place() {
        return hotel_place;
    }

    public void setHotel_place(String hotel_place) {
        this.hotel_place = hotel_place;
    }

    public String getDate_1() {
        return date_1;
    }

    public void setDate_1(String date_1) {
        this.date_1 = date_1;
    }

    public String getDate_2() {
        return date_2;
    }

    public void setDate_2(String date_2) {
        this.date_2 = date_2;
    }

    public String getHotel_price() {
        return hotel_price;
    }

    public void setHotel_price(String hotel_price) {
        this.hotel_price = hotel_price;
    }

    public String getNum_of_rooms() {
        return num_of_rooms;
    }

    public void setNum_of_rooms(String num_of_rooms) {
        this.num_of_rooms = num_of_rooms;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getHotel_image() {
        return hotel_image;
    }

    public void setHotel_image(String hotel_image) {
        this.hotel_image = hotel_image;
    }
}
