package com.example.Modelo;

import java.util.ArrayList;
import java.util.List;

public class Miembro {
    private String nombre;
    private int edad;
    private Deporte deporte;


    public Miembro(String nombre, int edad,Deporte deporte) {
        this.nombre = nombre;
        this.edad = edad;
        this.deporte = deporte;
    }
    public Deporte getDeporte(){
        return deporte;
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

    
    public void setDeporte(Deporte deporte) {
        this.deporte = deporte;
    }
    
    
}