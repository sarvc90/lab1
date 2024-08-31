package com.example.Modelo;

import java.util.ArrayList;
import java.util.List;

public class Entrenador extends Persona {
    private Deporte especialidad;
    private List<SesionEntrenamiento> sesiones;

    /**
     * Constructor para inicializar un Entrenador con el nombre, email, ID y especialidad.
     * @param nombre Nombre del entrenador.
     * @param email Correo electrónico del entrenador.
     * @param id ID del entrenador.
     * @param especialidad Deporte en el que el entrenador es especializado.
     */
    public Entrenador(String nombre, String email, int id, Deporte especialidad) {
        super(nombre, email, id);
        this.especialidad = especialidad;
        this.sesiones = new ArrayList<>();
    }

    /**
     * Obtiene el deporte de especialidad del entrenador.
     * @return Deporte de especialidad.
     */
    public Deporte getEspecialidad() {
        return especialidad;
    }

    /**
     * Obtiene la lista de sesiones de entrenamiento del entrenador.
     * @return Lista de sesiones de entrenamiento.
     */
    public List<SesionEntrenamiento> getSesiones() {
        return sesiones;
    }

    /**
     * Agrega una sesión de entrenamiento a la lista del entrenador.
     * @param sesion Sesión de entrenamiento a agregar.
     */
    public void addSesion(SesionEntrenamiento sesion) {
        sesiones.add(sesion);
    }

    /**
     * Establece el deporte de especialidad del entrenador.
     * @param especialidad Nuevo deporte de especialidad.
     */
    public void setEspecialidad(Deporte especialidad) {
        this.especialidad = especialidad;
    }

    /**
     * Establece la lista de sesiones de entrenamiento del entrenador.
     * @param sesiones Nueva lista de sesiones de entrenamiento.
     */
    public void setSesiones(List<SesionEntrenamiento> sesiones) {
        this.sesiones = sesiones;
    }

    /**
     * Establece el nombre del entrenador.
     * @param nombre Nuevo nombre del entrenador.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Establece el correo electrónico del entrenador.
     * @param email Nuevo correo electrónico del entrenador.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Establece el ID del entrenador.
     * @param id Nuevo ID del entrenador.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Busca y actualiza la lista de sesiones de entrenamiento en las que está asignado el entrenador.
     * @param club Club del que se obtienen las sesiones de entrenamiento.
     */
    public void fetchSesionesEntrenamiento(Club club) {
        List<SesionEntrenamiento> sesiones = club.obtenersSesionEntrenamientos();
        for (SesionEntrenamiento sesion : sesiones) {
            if (sesion.getEntrenador().equals(this)) {
                this.sesiones.add(sesion);
            }
        }
    }
}
