package com.example.Modelo;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Repositorio para gestionar la persistencia de los miembros en un archivo.
 */

public class MiembroRepository implements Repository<Miembro>{
    private List<Miembro> miembros;
    private static final String ARCHIVO_MIEMBROS = "C:\\Users\\sccva\\OneDrive\\Documentos\\GitHub\\lab1\\lab1\\miembros.txt";

     /**
     * Constructor que inicializa el repositorio y carga los miembros desde el archivo.
     */

    public MiembroRepository() {
        this.miembros = new ArrayList<Miembro>();
        cargarDesdeArchivo();
    }

    /**
     * Crea un nuevo miembro y lo guarda en la lista y en el archivo.
     * @param miembro Miembro a crear.
     */

    public void crear(Miembro miembro) {
        miembros.add(miembro);
        guardarEnArchivo();
    }

     /**
     * Obtiene todos los miembros del repositorio.
     * @return Lista de todos los miembros.
     */

    public List<Miembro> obtenerTodos() {
        return miembros;
    }

    /**
     * Obtiene un miembro por su nombre.
     * @param nombre Nombre del miembro.
     * @return Miembro correspondiente al nombre, o null si no se encuentra.
     */

    public Miembro obtenerPorNombre(String nombre) {
        for (Miembro miembro : miembros) {
            if (miembro.getNombre().equals(nombre)) {
                return miembro;
            }
        }
        return null;
    }


    /**
     * Actualiza la información de un miembro en la lista y en el archivo.
     * @param miembro Miembro con la información actualizada.
     */

    public void actualizar(Miembro miembro) {
        for (int i = 0; i < miembros.size(); i++) {
            if (miembros.get(i).getNombre().equals(miembro.getNombre())) {
                miembros.set(i, miembro);
                break;
            }
        }
        guardarEnArchivo();
    }

    /**
     * Elimina un miembro de la lista y actualiza el archivo.
     * @param nombre Nombre del miembro a eliminar.
     */

    public void eliminar(String nombre) {
        for (int i = 0; i < miembros.size(); i++) {
            if (miembros.get(i).getNombre().equals(nombre)) {
                miembros.remove(i);
                break;
            }
        }
        guardarEnArchivo();
    }

    /**
     * Carga los miembros desde el archivo al iniciar el repositorio.
     */

    public void cargarDesdeArchivo() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO_MIEMBROS))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                Miembro miembro = new Miembro(partes[0], Integer.parseInt(partes[1]), null );
                miembros.add(miembro);
            }
        } catch (IOException e) {
            System.err.println("Error al cargar miembros desde archivo: " + e.getMessage());
        }
    }

     /**
     * Guarda todos los miembros actuales en el archivo.
     */

    public void guardarEnArchivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO_MIEMBROS))) {
            for (Miembro miembro : miembros) {
                if (miembro.getDeporte() != null) {
                    writer.write(miembro.getNombre() + "," + miembro.getEdad() + "," + miembro.getDeporte().getNombre());
                } else {
                    writer.write(miembro.getNombre() + "," + miembro.getEdad() + ",null");
                }
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al guardar miembros en archivo: " + e.getMessage());
        }
    }
}