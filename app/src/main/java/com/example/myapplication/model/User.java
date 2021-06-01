package com.example.myapplication.model;

public class User {
    private String userName;
    private String password;
    private String email;

    public User(String username, String password, String email) {
        userName = username;
        this.password = password;
        this.email = email;
    }

    public User() {
    }

    public String getUsername() {
        return userName;
    }

    public void setUsername(String username) {
        userName = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}