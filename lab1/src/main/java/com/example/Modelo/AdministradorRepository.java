package com.example.Modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class AdministradorRepository {
    private static AdministradorRepository instancia; // Instancia única del repositorio
    private Administrador admin; // Administrador actual
    private static final String ADMIN_DEPORTES = "C:\\Users\\sccva\\OneDrive\\Documentos\\GitHub\\lab1\\lab1\\admin.txt"; // Ruta del archivo

    /**
     * Constructor que carga el administrador desde el archivo.
     */
    public AdministradorRepository() {
        cargarDesdeArchivo();
    }

    /**
     * Obtiene la instancia única del repositorio.
     * 
     * @return La instancia única del repositorio
     */
    public static AdministradorRepository getInstancia() {
        if (instancia == null) {
            instancia = new AdministradorRepository();
        }
        return instancia;
    }

    /**
     * Crea un nuevo administrador si no existe uno.
     * 
     * @param admin El administrador a crear
     */
    public void crear(Administrador admin) {
        if (this.admin == null || this.admin.getNombre().isEmpty()) {
            this.admin = admin;
            guardarEnArchivo();
        } else {
            System.out.println("Ya existe un administrador, no se puede crear otro.");
        }
    }

    /**
     * Obtiene el administrador actual.
     * 
     * @return El administrador actual
     */
    public Administrador obtenerAdmin() {
        return admin;
    }

    /**
     * Carga el administrador desde el archivo.
     */
    public void cargarDesdeArchivo() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ADMIN_DEPORTES))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                admin = new Administrador(partes[0], partes[1], Integer.parseInt(partes[2]), null);
            }
        } catch (IOException e) {
            System.err.println("Error al cargar administrador desde archivo: " + e.getMessage());
        }
    }

    /**
     * Guarda el administrador en el archivo.
     */
    private void guardarEnArchivo() {
        if (admin != null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(ADMIN_DEPORTES, true))) {
                writer.write(admin.getNombre() + "," + admin.getEmail() + "," + admin.getId());
                writer.newLine(); // Agrega una nueva línea después de cada administrador
            } catch (IOException e) {
                System.err.println("Error al guardar administrador en archivo: " + e.getMessage());
            }
        }
    }
}
