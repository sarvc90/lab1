package com.example.Modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class AdministradorRepository{
    private static AdministradorRepository instancia;
    private Administrador admin;
    private static final String ADMIN_DEPORTES = "C:\\Users\\Sara\\Downloads\\lab1\\lab1\\admin.txt";


    public AdministradorRepository() {
        cargarDesdeArchivo();
    }

    public static AdministradorRepository getInstancia() {
        if (instancia == null) {
            instancia = new AdministradorRepository();
        }
        return instancia;
    }

    public void crear(Administrador admin) {
        if (this.admin == null || this.admin.getNombre().isEmpty()) {
            this.admin = admin;
            guardarEnArchivo();
        } else {
            System.out.println("Ya existe un administrador, no se puede crear otro.");
        }
    }

    public Administrador obtenerAdmin() {
        return admin;
    }

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

    private void guardarEnArchivo() {
        if (admin != null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(ADMIN_DEPORTES, true))) {
                writer.write(admin.getNombre() + "," + admin.getEmail() + "," + admin.getId());
                writer.newLine(); // add a new line after each admin
            } catch (IOException e) {
                System.err.println("Error al guardar administrador en archivo: " + e.getMessage());
            }
        }
    }
}