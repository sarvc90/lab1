package com.example.Modelo;
import java.util.ArrayList;
import java.util.List;
public class Entrenador extends Persona {
    private Deporte especialidad;
    private List<SesionEntrenamiento> sesiones;

    public Entrenador(String nombre, String email, int id, Deporte especialidad) {
        super(nombre, email, id);
        this.especialidad = especialidad;
        this.sesiones = new ArrayList<>();
    }

    public Deporte getEspecialidad() {
        return especialidad;
    }

    public List<SesionEntrenamiento> getSesiones() {
        return sesiones;
    }

    public void addSesion(SesionEntrenamiento sesion) {
        sesiones.add(sesion);
    }

    //Busca las sesiones de entrenamiento en las que este asignado el entrenador
    public void fetchSesionesEntrenamiento(Club club) {
        List<SesionEntrenamiento> sesiones = club.obtenersSesionEntrenamientos();
        for (SesionEntrenamiento sesion : sesiones) {
            if (sesion.getEntrenador().equals(this)) {
                this.sesiones.add(sesion);
            }
        }
    }

}