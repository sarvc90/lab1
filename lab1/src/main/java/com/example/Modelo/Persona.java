package com.example.Modelo;

public abstract class Persona {
    protected String nombre;
    protected String email;
    protected int id;

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