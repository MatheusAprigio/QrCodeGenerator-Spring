package com.qrcode.qrcode.models;

public class NetworkConnection {
    
    private String name;
    private String password;
    private String securityType;

    public NetworkConnection() {
    }

    public NetworkConnection(String name, String password, String securityType) {
        this.name = name;
        this.password = password;
        this.securityType = securityType;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecurityType() {
        return this.securityType;
    }

    public void setSecurityType(String securityType) {
        this.securityType = securityType;
    }

    public NetworkConnection name(String name) {
        this.name = name;
        return this;
    }

    public NetworkConnection password(String password) {
        this.password = password;
        return this;
    }

    public NetworkConnection securityType(String securityType) {
        this.securityType = securityType;
        return this;
    }

}