package com.example.Modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa una sesión de entrenamiento en el club.
 */
public class SesionEntrenamiento {
    private String fecha;
    private int duracion;
    private Estado estado;
    private Deporte deporte;
    private Entrenador entrenador;
    private List<Miembro> miembros;

    /**
     * Constructor para crear una nueva sesión de entrenamiento.
     * @param fecha Fecha de la sesión.
     * @param duracion Duración de la sesión en minutos.
     * @param estado Estado de la sesión (PROGRAMADA o COMPLETADA).
     * @param deporte Deporte asociado con la sesión.
     * @param entrenador Entrenador encargado de la sesión.
     * @param miembros Lista de miembros que participan en la sesión.
     */
    public SesionEntrenamiento(String fecha, int duracion, Estado estado, Deporte deporte, Entrenador entrenador, List<Miembro> miembros) {
        this.fecha = fecha;
        this.duracion = duracion;
        this.estado = estado;
        this.deporte = deporte;
        this.entrenador = entrenador;
        this.miembros = new ArrayList<>(miembros);
    }

    /**
     * Obtiene la fecha de la sesión.
     * @return Fecha de la sesión.
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * Obtiene la duración de la sesión en minutos.
     * @return Duración de la sesión.
     */
    public int getDuracion() {
        return duracion;
    }

    /**
     * Obtiene el estado de la sesión.
     * @return Estado de la sesión.
     */
    public Estado getEstado() {
        return estado;
    }

    /**
     * Obtiene el deporte asociado con la sesión.
     * @return Deporte asociado.
     */
    public Deporte getDeporte() {
        return deporte;
    }

    /**
     * Obtiene el entrenador encargado de la sesión.
     * @return Entrenador a cargo.
     */
    public Entrenador getEntrenador() {
        return entrenador;
    }

    /**
     * Obtiene la lista de miembros que participan en la sesión.
     * @return Lista de miembros.
     */
    public List<Miembro> getMiembros() {
        return miembros;
    }

    /**
     * Agrega un miembro a la lista de participantes de la sesión.
     * @param miembro Miembro a agregar.
     */
    public void setMiembro(Miembro miembro) {
        this.miembros.add(miembro);
    }

    /**
     * Agrega una lista de miembros a la sesión.
     * @param miembros Lista de miembros a agregar.
     */
    public void addMiembros(List<Miembro> miembros) {
        this.miembros.addAll(miembros);
    }

    /**
     * Establece la fecha de la sesión.
     * @param fecha Nueva fecha de la sesión.
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * Establece la duración de la sesión en minutos.
     * @param duracion Nueva duración de la sesión.
     */
    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    /**
     * Establece el estado de la sesión.
     * @param estado Nuevo estado de la sesión.
     */
    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    /**
     * Establece el deporte asociado con la sesión.
     * @param deporte Nuevo deporte asociado.
     */
    public void setDeporte(Deporte deporte) {
        this.deporte = deporte;
    }

    /**
     * Establece el entrenador encargado de la sesión.
     * @param entrenador Nuevo entrenador a cargo.
     */
    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }

    /**
     * Establece la lista de miembros que participan en la sesión.
     * @param miembros Nueva lista de miembros.
     */
    public void setMiembros(List<Miembro> miembros) {
        this.miembros = miembros;
    }

    @Override
    public String toString() {
        return "SesionEntrenamiento{" +
                "fecha='" + fecha + '\'' +
                ", duracion=" + duracion +
                ", estado=" + estado +
                ", deporte=" + deporte +
                ", entrenador=" + entrenador +
                ", miembros=" + miembros +
                '}';
    }
}
