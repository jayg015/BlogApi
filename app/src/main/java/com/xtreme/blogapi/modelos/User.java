package com.xtreme.blogapi.modelos;

public class User {
    private int id;
    private String name;
    private String email;
    private String token;
    private String password;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getToken() {
        return token;
    }

    public String getPassword() {
        return password;
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}

