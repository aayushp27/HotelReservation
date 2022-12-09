package com.example.hotelreservation;

public class Users {

    private String full_name, user_email, contact, user_password, UID;

    public int user_type;


    public Users(String UID, String full_name, String user_email, String contact, String user_password, int user_type) {
        this.full_name = full_name;
        this.user_email = user_email;
        this.contact = contact;
        this.user_password = user_password;
        this.UID = UID;
        this.user_type = user_type;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public int getUser_type(){
        return user_type;
    }
    public void setUser_type(int user_type){
        this.user_type = user_type;
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

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }
}
