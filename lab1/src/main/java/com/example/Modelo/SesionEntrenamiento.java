package com.example.Modelo;

public class SesionEntrenamiento {
    private String fecha;
    private int duracion;
    private Estado estado;
    private Deporte deporte;
    private Entrenador entrenador;

    public SesionEntrenamiento(String fecha, int duracion, Estado estado, Deporte deporte, Entrenador entrenador) {
        this.fecha = fecha;
        this.duracion = duracion;
        this.estado = estado;
        this.deporte = deporte;
        this.entrenador = entrenador;
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
}