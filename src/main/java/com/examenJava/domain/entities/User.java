package com.examenJava.domain.entities;

import java.sql.Timestamp;

public class User {
    private int id;
    private String nombre;
    private String contrasena;
    private String role;
    private boolean active;
    private Timestamp created_at;
    private Timestamp last_login;

    public User() {
    }

    public User(String nombre, String contrasena, String role) {
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.role = role;
        this.active = true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getnombre() {
        return nombre;
    }

    public void setnombre(String nombre) {
        this.nombre = nombre;
    }

    public String getcontrasena() {
        return contrasena;
    }

    public void setcontrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getLast_login() {
        return last_login;
    }

    public void setLast_login(Timestamp last_login) {
        this.last_login = last_login;
    }
}
