package com.example.Modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SesionEntrenamientoRepository implements Repository<SesionEntrenamiento> {
    private List<SesionEntrenamiento> sesiones;
    private static final String ARCHIVO_SESIONES = "C:\\Users\\Sara\\Downloads\\lab1\\lab1\\sesiones.txt";

    public SesionEntrenamientoRepository() {
        this.sesiones = new ArrayList<SesionEntrenamiento>();
        cargarDesdeArchivo();
    }
    //Verifica que las sesiones no se programen en la misma fecha ni con el mismo entrenador 
    public void crear(SesionEntrenamiento sesion) {
        if (existeSesionConMismoEntrenadorYFecha(sesion)) {
            System.out.println("Ya existe una sesión de entrenamiento con el mismo entrenador en la misma fecha y deporte.");
            return;
        }
        sesiones.add(sesion);
        // Agregar la sesión al entrenador correspondiente
        Entrenador entrenador = sesion.getEntrenador();
        entrenador.addSesion(sesion);
        guardarEnArchivo();
    }
    public List<SesionEntrenamiento> obtenerTodos() {
        return sesiones;
    }

    public SesionEntrenamiento obtenerPorNombre(String fecha) {
        for (SesionEntrenamiento sesion : sesiones) {
            if (sesion.getFecha().equals(fecha)) {
                return sesion;
            }
        }
        return null;
    }

    //Tambien verifica que las sesiones no esten en la misma fecha ni con el mismo entrenador para poder actualizarlas  
    public void actualizar(SesionEntrenamiento sesion) {
        if (existeSesionConMismoEntrenadorYFecha(sesion)) {
            System.out.println("Ya existe una sesión de entrenamiento con el mismo entrenador en la misma fecha y deporte.");
            return;
        }
        for (int i = 0; i < sesiones.size(); i++) {
            if (sesiones.get(i).getFecha().equals(sesion.getFecha())) {
                sesiones.set(i, sesion);
                // Agregar la sesión al entrenador correspondiente
                Entrenador entrenador = sesion.getEntrenador();
                entrenador.addSesion(sesion);
                break;
            }
        }
        guardarEnArchivo();
    }

    public void eliminar(String fecha) {
        for (int i = 0; i < sesiones.size(); i++) {
            if (sesiones.get(i).getFecha().equals(fecha)) {
                sesiones.remove(i);
                break;
            }
        }
        guardarEnArchivo();
    }

    private boolean existeSesionConMismoEntrenadorYFecha(SesionEntrenamiento sesion) {
        for (SesionEntrenamiento s : sesiones) {
            if (s.getEntrenador().equals(sesion.getEntrenador()) && s.getFecha().equals(sesion.getFecha()) && s.getDeporte().equals(sesion.getDeporte())) {
                return true;
            }
        }
        return false;
    }

    public void cargarDesdeArchivo() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO_SESIONES))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                SesionEntrenamiento sesion = new SesionEntrenamiento(partes[0], Integer.parseInt(partes[1]), null, null, null);
                sesiones.add(sesion);
            }
        } catch (IOException e) {
            System.err.println("Error al cargar sesiones desde archivo: " + e.getMessage());
        }
    }

    public void guardarEnArchivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO_SESIONES))) {
            for (SesionEntrenamiento sesion : sesiones) {
                writer.write(sesion.getFecha() + "," + sesion.getDuracion() + "," + sesion.getEstado() + "," + sesion.getDeporte() + "," + sesion.getEntrenador());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al guardar sesiones en archivo: " + e.getMessage());
        }
    }
}