package com.example.hotelreservation;

public class Hotels {
    private String hotel_name, hotel_place, hotel_desc, hotel_street, hotel_city, hotel_pincode, hotel_price, owner_email, hotel_email;
    private String hotel_phone;
    private String hotel_image;


    public Hotels() {

    }

    public Hotels(String hotel_name, String hotel_place, String hotel_desc, String hotel_street, String hotel_city, String hotel_pincode, String hotel_phone, String hotel_image, String hotel_price, String owner_email, String hotel_email) {
        this.hotel_name = hotel_name;
        this.hotel_place = hotel_place;
        this.hotel_desc = hotel_desc;
        this.hotel_street = hotel_street;
        this.hotel_city = hotel_city;
        this.hotel_pincode = hotel_pincode;
        this.hotel_phone = hotel_phone;
        this.hotel_image = hotel_image;
        this.hotel_price = hotel_price;
        this.owner_email = owner_email;
        this.hotel_email = hotel_email;
    }

    public String getHotel_email() {
        return hotel_email;
    }

    public void setHotel_email(String hotel_email) {
        this.hotel_email = hotel_email;
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

    public String getHotel_desc() {
        return hotel_desc;
    }

    public void setHotel_desc(String hotel_desc) {
        this.hotel_desc = hotel_desc;
    }

    public String getHotel_street() {
        return hotel_street;
    }

    public void setHotel_street(String hotel_street) {
        this.hotel_street = hotel_street;
    }

    public String getHotel_city() {
        return hotel_city;
    }

    public void setHotel_city(String hotel_city) {
        this.hotel_city = hotel_city;
    }

    public String getHotel_pincode() {
        return hotel_pincode;
    }

    public void setHotel_pincode(String hotel_pincode) {
        this.hotel_pincode = hotel_pincode;
    }

    public String getHotel_phone() {
        return hotel_phone;
    }

    public void setHotel_phone(String hotel_phone) {
        this.hotel_phone = hotel_phone;
    }

    public String getHotel_image() {
        return hotel_image;
    }

    public void setHotel_image(String hotel_image) {
        this.hotel_image = hotel_image;
    }

    public String getHotel_price() {
        return hotel_price;
    }

    public void setHotel_price(String hotel_price) {
        this.hotel_price = hotel_price;
    }

    public String getOwner_email() {
        return owner_email;
    }

    public void setOwner_email(String owner_email) {
        this.owner_email = owner_email;
    }
}
