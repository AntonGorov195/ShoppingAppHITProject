package com.example.shoppingapphitproject.models;

public class Users {
    String username;
    String password;
    String phone;

    public Users(String username, String password, String phone) {
        this.username = username;
        this.password = password;
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }
}
