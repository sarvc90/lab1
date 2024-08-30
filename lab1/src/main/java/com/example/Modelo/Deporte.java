package com.example.Modelo;
import java.util.ArrayList;
import java.util.List;
public class Deporte {
    private String nombre;
    private String descripcion;
    private static Dificultad nivelDificultad;
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
        miembro.setDeporte(this); // Agregamos esta línea para actualizar el deporte del miembro
    }
    public List<Miembro> getMiembros() {
        return miembros;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    // Buscar miembros que practican este deporte
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

    public static Deporte parseDeporte(String cadena) {
        Deporte deporte = new Deporte(cadena, cadena, nivelDificultad);
        return deporte;
    }

    
}