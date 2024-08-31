package com.example.Modelo;

import java.util.List;

public class Administrador extends Persona {
    private Club club; // Club al que el administrador tiene acceso

    /**
     * Constructor que inicializa el administrador con el nombre, email, ID y club.
     * 
     * @param nombre Nombre del administrador
     * @param email Email del administrador
     * @param id ID del administrador
     * @param club Club al que el administrador tiene acceso
     */
    public Administrador(String nombre, String email, int id, Club club) {
        super(nombre, email, id);
        this.club = club;
    }

    /**
     * Programa una nueva sesión de entrenamiento en el club.
     * 
     * @param sesion Sesión de entrenamiento a programar
     */
    public void programarSesion(SesionEntrenamiento sesion) {
        club.crearSesionEntrenamiento(sesion);
    }

    /**
     * Muestra todas las sesiones de entrenamiento del club.
     */
    public void gestionarSesiones() {
        List<SesionEntrenamiento> sesiones = club.obtenersSesionEntrenamientos();
        for (SesionEntrenamiento sesion : sesiones) {
            System.out.println("Sesión de entrenamiento: " + sesion.getFecha() + " - " + sesion.getDeporte());
        }
    }
}
