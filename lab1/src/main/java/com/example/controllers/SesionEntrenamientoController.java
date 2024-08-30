package com.example.controllers;

import java.util.List;

import com.example.Modelo.Club;
import com.example.Modelo.ClubSingleton;
import com.example.Modelo.Deporte;
import com.example.Modelo.Entrenador;
import com.example.Modelo.Estado;
import com.example.Modelo.Miembro;
import com.example.Modelo.SesionEntrenamiento;
import com.example.Modelo.SesionEntrenamientoRepository;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class SesionEntrenamientoController {

    @FXML 
    private TextField fechaTextField;
    @FXML 
    private TextField duracionTextField;

    private SesionEntrenamiento sesionEntrenamiento;
    private SesionEntrenamientoRepository sesionRepository;
    private Club club;

    // Constructor que inicializa el repositorio y obtiene la instancia única del club
    public SesionEntrenamientoController(SesionEntrenamientoRepository sesionRepository) {
        this.sesionRepository = sesionRepository;
        this.club = ClubSingleton.getClub(); // Usamos el Singleton para obtener la instancia de Club
        this.sesionEntrenamiento = sesionEntrenamiento;
    }

    public void crearSesion(String fecha, int duracion, Estado estado, Deporte deporte, Entrenador entrenador, List<Miembro> miembros) {
        SesionEntrenamiento sesion = new SesionEntrenamiento(fecha, duracion, estado, deporte, entrenador, miembros);
        club.crearSesionEntrenamiento(sesion);
    }

    public List<SesionEntrenamiento> obtenerTodasSesiones() {
        return club.obtenersSesionEntrenamientos();
    }

    public SesionEntrenamiento obtenerSesionPorFecha(String fecha) {
        return sesionRepository.obtenerPorNombre(fecha);
    }

    public void actualizarSesion(String fecha, int duracion, Estado estado, Deporte deporte, Entrenador entrenador, List<Miembro> miembros) {
        SesionEntrenamiento sesion = new SesionEntrenamiento(fecha, duracion, estado, deporte, entrenador, miembros);
        sesionRepository.actualizar(sesion);
    }

    public void eliminarSesion(String fecha) {
        club.eliminarSesionEntrenamiento(fecha);
    }

    public void actualizarMiembrosEnSesion(String fecha, List<Miembro> miembros) {
        club.actualizarSesionEntrenamiento(sesionEntrenamiento);
    }
}