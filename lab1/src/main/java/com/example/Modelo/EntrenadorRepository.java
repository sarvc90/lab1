package com.example.Modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EntrenadorRepository implements Repository<Entrenador> {
    private List<Entrenador> entrenadores;
    private static final String ARCHIVO_ENTRENADORES = "C:\\Users\\sccva\\OneDrive\\Documentos\\GitHub\\lab1\\lab1\\entrenadores.txt";

    /**
     * Constructor que inicializa la lista de entrenadores y carga los datos desde el archivo.
     */
    public EntrenadorRepository() {
        this.entrenadores = new ArrayList<Entrenador>();
        cargarDesdeArchivo();
    }

    /**
     * Crea un nuevo entrenador y lo agrega a la lista y al archivo.
     * @param entrenador Entrenador a agregar.
     */
    public void crear(Entrenador entrenador) {
        entrenadores.add(entrenador);
        guardarEnArchivo();
    }

    /**
     * Obtiene la lista de todos los entrenadores.
     * @return Lista de entrenadores.
     */
    public List<Entrenador> obtenerTodos() {
        return entrenadores;
    }

    /**
     * Obtiene un entrenador por su nombre.
     * @param nombre Nombre del entrenador.
     * @return Entrenador con el nombre dado, o null si no se encuentra.
     */
    public Entrenador obtenerPorNombre(String nombre) {
        for (Entrenador entrenador : entrenadores) {
            if (entrenador.getNombre().equals(nombre)) {
                return entrenador;
            }
        }
        return null;
    }

    /**
     * Actualiza la información de un entrenador existente y guarda los cambios en el archivo.
     * @param entrenador Entrenador con la información actualizada.
     */
    public void actualizar(Entrenador entrenador) {
        for (int i = 0; i < entrenadores.size(); i++) {
            if (entrenadores.get(i).getNombre().equals(entrenador.getNombre())) {
                entrenadores.set(i, entrenador);
                break;
            }
        }
        guardarEnArchivo();
    }

    /**
     * Elimina un entrenador por su nombre y actualiza el archivo.
     * @param nombre Nombre del entrenador a eliminar.
     */
    public void eliminar(String nombre) {
        for (int i = 0; i < entrenadores.size(); i++) {
            if (entrenadores.get(i).getNombre().equals(nombre)) {
                entrenadores.remove(i);
                break;
            }
        }
        guardarEnArchivo();
    }

    /**
     * Carga la lista de entrenadores desde el archivo.
     */
    public void cargarDesdeArchivo() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO_ENTRENADORES))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(","); // Divide la línea en partes
                if (partes.length >= 4) { // Verifica si hay al menos 4 elementos
                    Deporte especialidad = new Deporte(partes[3], partes[3], null); // Crea un objeto Deporte con el nombre de la especialidad
                    Entrenador entrenador = new Entrenador(partes[0], partes[1], Integer.parseInt(partes[2]), especialidad);
                    entrenadores.add(entrenador);
                } else {
                    System.err.println("Error: formato de archivo inválido. Saltando línea: " + linea);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al cargar entrenadores desde archivo: " + e.getMessage());
        }
    }

    /**
     * Guarda la lista de entrenadores en el archivo.
     */
    public void guardarEnArchivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO_ENTRENADORES))) {
            for (Entrenador entrenador : entrenadores) {
                writer.write(entrenador.getNombre() + "," + entrenador.getEmail() + "," + entrenador.getId() + "," + entrenador.getEspecialidad().getNombre());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al guardar entrenadores en archivo: " + e.getMessage());
        }
    }
}
