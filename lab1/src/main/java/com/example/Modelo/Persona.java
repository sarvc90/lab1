package com.example.Modelo;

/**
 * Clase abstracta que representa a una persona con nombre, email e ID.
 */
public abstract class Persona {
    protected String nombre;
    protected String email;
    protected int id;

    /**
     * Constructor que inicializa los atributos de la persona.
     * @param nombre Nombre de la persona.
     * @param email Email de la persona.
     * @param id ID de la persona.
     */
    public Persona(String nombre, String email, int id) {
        this.nombre = nombre;
        this.email = email;
        this.id = id;
    }

    /**
     * Obtiene el nombre de la persona.
     * @return Nombre de la persona.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el email de la persona.
     * @return Email de la persona.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Obtiene el ID de la persona.
     * @return ID de la persona.
     */
    public int getId() {
        return id;
    }
}
