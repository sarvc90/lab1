package com.example.Modelo;

import java.util.ArrayList;
import java.util.List;

public class Miembro {
    private String nombre;
    private int edad;
    private List<Deporte> deportes;


    public Miembro(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
        this.deportes = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public boolean siEsAdulto() {
        return edad >= 18;
    }

    public void addDeporte(Deporte deporte) {
        deportes.add(deporte);
    }

    public void removeDeporte(Deporte deporte) {
        deportes.remove(deporte);
    }
    
    public List<Deporte> getDeportes() {
        return deportes;
    }
}