package com.example.shan.firebasetodo;

public class User {
    //private String user_id;
    private String userName;
    private long phoneNumber;

    public User() {
    }

    public String getUserName() {
        return userName;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public User(String userName, long phoneNumber) {
        //this.user_id = user_id;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
    }
}