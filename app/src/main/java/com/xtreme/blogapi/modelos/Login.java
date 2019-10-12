package com.xtreme.blogapi.modelos;

public class Login {
    private String email;
    private String password;
    private String nombre;

    public Login(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Login(String email, String password, String nombre) {
        this.email = email;
        this.password = password;
        this.nombre = nombre;
    }


}
