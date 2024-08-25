package com.example.Modelo;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MiembroRepository implements Repository<Miembro>{
    private List<Miembro> miembros;
    private static final String ARCHIVO_MIEMBROS = "C:\\Users\\Sara\\Downloads\\lab1\\lab1\\miembros.txt";

    public MiembroRepository() {
        this.miembros = new ArrayList<Miembro>();
        cargarDesdeArchivo();
    }

    public void crear(Miembro miembro) {
        miembros.add(miembro);
        guardarEnArchivo();
    }

    public List<Miembro> obtenerTodos() {
        return miembros;
    }

    public Miembro obtenerPorNombre(String nombre) {
        for (Miembro miembro : miembros) {
            if (miembro.getNombre().equals(nombre)) {
                return miembro;
            }
        }
        return null;
    }

    public void actualizar(Miembro miembro) {
        for (int i = 0; i < miembros.size(); i++) {
            if (miembros.get(i).getNombre().equals(miembro.getNombre())) {
                miembros.set(i, miembro);
                break;
            }
        }
        guardarEnArchivo();
    }

    public void eliminar(String nombre) {
        for (int i = 0; i < miembros.size(); i++) {
            if (miembros.get(i).getNombre().equals(nombre)) {
                miembros.remove(i);
                break;
            }
        }
        guardarEnArchivo();
    }

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

    public void guardarEnArchivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO_MIEMBROS))) {
            for (Miembro miembro : miembros) {
                writer.write(miembro.getNombre() + "," + miembro.getEdad());
                for (Deporte deporte : miembro.getDeportes()) {
                    writer.write("," + deporte.getNombre());
                }
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al guardar miembros en archivo: " + e.getMessage());
        }
    }
}