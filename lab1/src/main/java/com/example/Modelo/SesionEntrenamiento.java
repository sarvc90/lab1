package com.example.Modelo;

import java.util.ArrayList;
import java.util.List;

public class SesionEntrenamiento {
    private String fecha;
    private int duracion;
    private Estado estado;
    private Deporte deporte;
    private Entrenador entrenador;
    private List<Miembro> miembros;

    public SesionEntrenamiento(String fecha, int duracion, Estado estado, Deporte deporte, Entrenador entrenador, List<Miembro> miembros) {
        this.fecha = fecha;
        this.duracion = duracion;
        this.estado = estado;
        this.deporte = deporte;
        this.entrenador = entrenador;
        this.miembros = new ArrayList<>();
    }

    public String getFecha() {
        return fecha;
    }

    public int getDuracion() {
        return duracion;
    }

    public Estado getEstado() {
        return estado;
    }

    public Deporte getDeporte() {
        return deporte;
    }

    public Entrenador getEntrenador() {
        return entrenador;
    }

    public List<Miembro> getMiembros(){
        return miembros;
    }

    
}