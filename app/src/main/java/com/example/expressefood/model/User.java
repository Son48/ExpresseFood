package com.example.expressefood.model;

public class User {
    private String Password;
    private String phonel;
    private String name;

    public User() {
    }

    public User(String name,String password, String phonel) {
        this.name=name;
        this.Password = password;
        this.phonel = phonel;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getPhonel() {
        return phonel;
    }

    public void setPhonel(String phonel) {
        this.phonel = phonel;
    }

}
