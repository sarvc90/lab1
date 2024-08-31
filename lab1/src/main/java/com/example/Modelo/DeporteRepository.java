package com.example.Modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementa la interfaz Repository para manejar operaciones CRUD de objetos Deporte.
 */

public class DeporteRepository implements Repository<Deporte> {
    private List<Deporte> deportes;
    private static final String ARCHIVO_DEPORTES = "C:\\Users\\sccva\\OneDrive\\Documentos\\GitHub\\lab1\\lab1\\deportes.txt";
    
    /**
     * Constructor que inicializa la lista de deportes y carga los datos desde el archivo.
     */
    
    public DeporteRepository() {
        this.deportes = new ArrayList<Deporte>();
        cargarDesdeArchivo();
    }

    /**
     * Crea un nuevo deporte y lo guarda en el archivo.
     * 
     * @param deporte El deporte a crear
     */

    public void crear(Deporte deporte) {
        deportes.add(deporte);
        guardarEnArchivo();
    }

    /**
     * Obtiene todos los deportes.
     * 
     * @return Lista de todos los deportes
     */

    public List<Deporte> obtenerTodos() {
        return deportes;
    }

    /**
     * Obtiene un deporte por su nombre.
     * 
     * @param nombre El nombre del deporte
     * @return El deporte correspondiente, o null si no se encuentra
     */

    public Deporte obtenerPorNombre(String nombre) {
        for (Deporte deporte : deportes) {
            if (deporte.getNombre().equals(nombre)) {
                return deporte;
            }
        }
        return null;
    }

     /**
     * Actualiza un deporte existente y guarda los cambios en el archivo.
     * 
     * @param deporte El deporte a actualizar
     */

    public void actualizar(Deporte deporte) {
        for (int i = 0; i < deportes.size(); i++) {
            if (deportes.get(i).getNombre().equals(deporte.getNombre())) {
                deportes.set(i, deporte);
                break;
            }
        }
        guardarEnArchivo();
    }

    /**
     * Elimina un deporte por su nombre y guarda los cambios en el archivo.
     * 
     * @param nombre El nombre del deporte a eliminar
     */

    public void eliminar(String nombre) {
        for (int i = 0; i < deportes.size(); i++) {
            if (deportes.get(i).getNombre().equals(nombre)) {
                deportes.remove(i);
                break;
            }
        }
        guardarEnArchivo();
    }

     /**
     * Carga los deportes desde un archivo.
     */

    public void cargarDesdeArchivo() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO_DEPORTES))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                Dificultad nivelDificultad = Dificultad.valueOf(partes[2]);
                Deporte deporte = new Deporte(partes[0], partes[1], nivelDificultad); // Agrego null como nivelDificultad
                deportes.add(deporte);
            }
        } catch (IOException e) {
            System.err.println("Error al cargar deportes desde archivo: " + e.getMessage());
        }
    }

     /**
     * Guarda todos los deportes en un archivo.
     */

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