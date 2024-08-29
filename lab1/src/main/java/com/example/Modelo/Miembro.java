package com.example.Modelo;

import java.util.ArrayList;
import java.util.List;

public class Miembro {
    private String nombre;
    private int edad;
    private Deporte deporte;

    public Miembro(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
        this.deporte = deporte;
    }
    public Miembro(String nombre, int edad, Deporte deporte) {
        this.nombre = nombre;
        this.edad = edad;
        this.deporte = deporte;
    }

    public Deporte getDeporte() {
        return deporte;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public boolean siEsAdulto() {
        if (this.edad >= 18) {
            return true;
        } else {
            return false;
        }
    }

    public void setDeporte(Deporte deporte) {
        this.deporte = deporte;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    // Agregamos un método para verificar si el deporte es nulo
    public boolean tieneDeporte() {
        return deporte != null;
    }
}