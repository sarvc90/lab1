package com.example.Modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa un deporte en el club.
 */
public class Deporte {
    private String nombre;
    private String descripcion;
    private Dificultad nivelDificultad; // No es estático, cada deporte tiene su nivel de dificultad
    private List<Entrenador> entrenadores;
    private List<Miembro> miembros;

    /**
     * Constructor para crear una nueva instancia de Deporte.
     * 
     * @param nombre Nombre del deporte
     * @param descripcion Descripción del deporte
     * @param nivelDificultad Nivel de dificultad del deporte
     */
    public Deporte(String nombre, String descripcion, Dificultad nivelDificultad) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.nivelDificultad = nivelDificultad;
        this.entrenadores = new ArrayList<>();
        this.miembros = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Dificultad getNivelDificultad() {
        return nivelDificultad;
    }

    public List<Entrenador> getEntrenadores() {
        return entrenadores;
    }

    /**
     * Agrega un entrenador al deporte.
     * 
     * @param entrenador El entrenador a agregar
     */
    public void addEntrenador(Entrenador entrenador) {
        entrenadores.add(entrenador);
    }

    public List<Miembro> getMiembros() {
        return miembros;
    }

    /**
     * Agrega un miembro al deporte y actualiza el deporte del miembro.
     * 
     * @param miembro El miembro a agregar
     */
    public void addMiembro(Miembro miembro) {
        miembros.add(miembro);
        miembro.setDeporte(this); // Actualiza el deporte del miembro
    }

    /**
     * Busca los miembros y entrenadores asociados a este deporte en el club.
     * 
     * @param club El club donde se realiza la búsqueda
     * @return Lista de miembros y entrenadores asociados al deporte
     */
    public List<Object> fetchMiembrosAndEntrenadores(Club club) {
        List<Object> resultado = new ArrayList<>();

        for (Miembro miembro : club.obtenerMiembros()) {
            if (miembro.getDeporte() != null && miembro.getDeporte().equals(this)) {
                resultado.add(miembro);
            }
        }

        for (Entrenador entrenador : club.obtenerEntrenadores()) {
            if (entrenador.getEspecialidad().equals(this)) {
                resultado.add(entrenador);
            }
        }

        return resultado;
    }

    @Override
    public String toString() {
        return nombre;
    }

    /**
     * Método para parsear una cadena en un objeto Deporte.
     * 
     * @param cadena La cadena que representa el deporte
     * @return Un objeto Deporte
     */
    public static Deporte parseDeporte(String cadena) {
        // Asumimos que el nivelDificultad debe ser proporcionado de alguna manera, actualmente estático y no válido.
        Dificultad nivelDificultad = Dificultad.BAJO; // Modificar según sea necesario
        return new Deporte(cadena, cadena, nivelDificultad);
    }
}
