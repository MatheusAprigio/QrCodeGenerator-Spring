package com.qrcode.qrcode.models;

public class Contact {
    
    private String name;
    private String address;
    private String phoneNumber;
    private String email;


    public Contact() {
    }

    public Contact(String name, String address, String phoneNumber, String email) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Contact name(String name) {
        this.name = name;
        return this;
    }

    public Contact address(String address) {
        this.address = address;
        return this;
    }

    public Contact phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public Contact email(String email) {
        this.email = email;
        return this;
    }
}