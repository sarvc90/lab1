package com.example.Modelo;
import java.util.ArrayList;
import java.util.List;
public class Deporte {
    private String nombre;
    private String descripcion;
    private Dificultad nivelDificultad;
    private List<Entrenador> entrenadores;
    private List<Miembro> miembros;
    private Club club;

    public Deporte(String nombre, String descripcion, Dificultad nivelDificultad) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.nivelDificultad = nivelDificultad;
        this.entrenadores = new ArrayList<>();
        this.miembros = new ArrayList<>();
        this.club = club;
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

    public void addEntrenador(Entrenador entrenador) {
        entrenadores.add(entrenador);
  
    }
    public void addMiembro(Miembro miembro) {
        miembros.add(miembro);
    }
    public List<Miembro> getMiembros() {
        return miembros;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    // Buscar miembros que practican este deporte
    public void fetchMiembrosAndEntrenadores(Club club) {

        for (Miembro miembro : club.obtenerMiembros()) {
            for (Deporte deporte : miembro.getDeportes()) { 
                if (deporte.equals(this)) {
                    this.miembros.add(miembro);
                    break; 
                }
            }
        }
    
        for (Entrenador entrenador : club.obtenerEntrenadores()) {
            if (entrenador.getEspecialidad().equals(this)) {
                this.entrenadores.add(entrenador);
            }
        }
    }
}