package com.example.Modelo;

public abstract class Persona {
    private String nombre;
    private String email;
    private int id;

    public Persona(String nombre, String email, int id) {
        this.nombre = nombre;
        this.email = email;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }
}