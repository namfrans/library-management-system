package com.company;

public class User {
    private String name, phoneNumber, address, password;

    public User(){
        //Default
    }

    public User(String name, String phoneNumber, String address, String password) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.password = password;
    }

    public void logIn(){

    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getPassword() {
        return password;
    }

}
