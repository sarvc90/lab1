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
    private static final String ARCHIVO_ENTRENADORES = "C:\\Users\\Sara\\Downloads\\lab1\\lab1\\entrenadores.txt";

    public EntrenadorRepository(){
        this.entrenadores = new ArrayList<Entrenador>();
        cargarDesdeArchivo();
    }

    public void crear(Entrenador entrenador){
        entrenadores.add(entrenador);
        guardarEnArchivo();
    }

    public List<Entrenador> obtenerTodos(){
        return entrenadores;
    }

    public Entrenador obtenerPorNombre(String nombre){
        for (Entrenador entrenador : entrenadores) {
            if (entrenador.getNombre().equals(nombre)) {
                return entrenador;
            }
        }
        return null;
    }

    public void actualizar(Entrenador entrenador) {
        for (int i = 0; i < entrenadores.size(); i++) {
            if (entrenadores.get(i).getNombre().equals(entrenador.getNombre())) {
                entrenadores.set(i, entrenador);
                break;
            }
        }
        guardarEnArchivo();
    }

    public void eliminar(String nombre) {
        for (int i = 0; i < entrenadores.size(); i++) {
            if (entrenadores.get(i).getNombre().equals(nombre)) {
                entrenadores.remove(i);
                break;
            }
        }
        guardarEnArchivo();
    }

    public void cargarDesdeArchivo() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO_ENTRENADORES))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                Entrenador entrenador = new Entrenador(partes[0], partes[1], Integer.parseInt(partes[2]), null);
                entrenadores.add(entrenador);
            }
        } catch (IOException e) {
            System.err.println("Error al cargar entrenadores desde archivo: " + e.getMessage());
        }
    }

    public void guardarEnArchivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO_ENTRENADORES))) {
            for (Entrenador entrenador : entrenadores) {
                writer.write(entrenador.getNombre() + "," + entrenador.getEmail());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al guardar entrenadores en archivo: " + e.getMessage());
        }
    }
}