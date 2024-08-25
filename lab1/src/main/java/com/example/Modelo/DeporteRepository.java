package com.example.Modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DeporteRepository implements Repository<Deporte> {
    private List<Deporte> deportes;
    private static final String ARCHIVO_DEPORTES = "C:\\Users\\Sara\\Documents\\GitHub\\lab1\\lab1\\deportes.txt";
    public DeporteRepository() {
        this.deportes = new ArrayList<Deporte>();
        cargarDesdeArchivo();
    }

    public void crear(Deporte deporte) {
        deportes.add(deporte);
        guardarEnArchivo();
    }

    public List<Deporte> obtenerTodos() {
        return deportes;
    }

    public Deporte obtenerPorNombre(String nombre) {
        for (Deporte deporte : deportes) {
            if (deporte.getNombre().equals(nombre)) {
                return deporte;
            }
        }
        return null;
    }

    public void actualizar(Deporte deporte) {
        for (int i = 0; i < deportes.size(); i++) {
            if (deportes.get(i).getNombre().equals(deporte.getNombre())) {
                deportes.set(i, deporte);
                break;
            }
        }
        guardarEnArchivo();
    }

    public void eliminar(String nombre) {
        for (int i = 0; i < deportes.size(); i++) {
            if (deportes.get(i).getNombre().equals(nombre)) {
                deportes.remove(i);
                break;
            }
        }
        guardarEnArchivo();
    }

    public void cargarDesdeArchivo() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO_DEPORTES))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                Deporte deporte = new Deporte(partes[0], partes[1], null); // Agrego null como nivelDificultad
                deportes.add(deporte);
            }
        } catch (IOException e) {
            System.err.println("Error al cargar deportes desde archivo: " + e.getMessage());
        }
    }

    public void guardarEnArchivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO_DEPORTES))) {
            for (Deporte deporte : deportes) {
                writer.write(deporte.getNombre() + "," + deporte.getDescripcion() + "," + deporte.getNivelDificultad());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al guardar deportes en archivo: " + e.getMessage());
        }
    }
}