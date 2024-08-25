package com.example.Modelo;

import java.util.List;

public class Administrador extends Persona {
    private Club club;

    public Administrador(String nombre, String email, int id, Club club) {
        super(nombre, email, id);
        this.club = club;
    }

    public void programarSesion(SesionEntrenamiento sesion) {
        club.crearSesionEntrenamiento(sesion);
    }

    //Muestra todas las sesiones que el club tenga 
    public void gestionarSesiones() {
        List<SesionEntrenamiento> sesiones = club.obtenersSesionEntrenamientos();
        for (SesionEntrenamiento sesion : sesiones) {
            System.out.println("Sesión de entrenamiento: " + sesion.getFecha() + " - " + sesion.getDeporte());
        }
    }
}