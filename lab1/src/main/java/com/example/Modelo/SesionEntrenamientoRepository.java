package com.example.Modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Repositorio para manejar las sesiones de entrenamiento, incluyendo operaciones de creación,
 * actualización, eliminación y almacenamiento en archivo.
 */
public class SesionEntrenamientoRepository implements Repository<SesionEntrenamiento> {
    private List<SesionEntrenamiento> sesiones;
    private static final String ARCHIVO_SESIONES = "C:\\Users\\sccva\\OneDrive\\Documentos\\GitHub\\lab1\\lab1\\sesiones.txt";

    /**
     * Constructor que inicializa la lista de sesiones y carga los datos desde el archivo.
     */
    public SesionEntrenamientoRepository() {
        this.sesiones = new ArrayList<>();
        cargarDesdeArchivo();
    }

    /**
     * Crea una nueva sesión de entrenamiento. Verifica que no exista una sesión con el mismo entrenador y fecha.
     * @param sesion La sesión de entrenamiento a crear.
     */
    @Override
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

    /**
     * Obtiene todas las sesiones de entrenamiento.
     * @return Lista de sesiones de entrenamiento.
     */
    @Override
    public List<SesionEntrenamiento> obtenerTodos() {
        return sesiones;
    }

    /**
     * Obtiene una sesión de entrenamiento por su fecha.
     * @param fecha La fecha de la sesión.
     * @return La sesión de entrenamiento correspondiente a la fecha, o null si no se encuentra.
     */
    @Override
    public SesionEntrenamiento obtenerPorNombre(String fecha) {
        for (SesionEntrenamiento sesion : sesiones) {
            if (sesion.getFecha().equals(fecha)) {
                return sesion;
            }
        }
        return null;
    }

    /**
     * Actualiza una sesión de entrenamiento. Verifica que no exista una sesión con el mismo entrenador y fecha.
     * @param sesion La sesión de entrenamiento a actualizar.
     */
    @Override
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

    /**
     * Elimina una sesión de entrenamiento por su fecha.
     * @param fecha La fecha de la sesión a eliminar.
     */
    @Override
    public void eliminar(String fecha) {
        for (int i = 0; i < sesiones.size(); i++) {
            if (sesiones.get(i).getFecha().equals(fecha)) {
                sesiones.remove(i);
                break;
            }
        }
        guardarEnArchivo();
    }

    /**
     * Verifica si ya existe una sesión con el mismo entrenador y fecha.
     * @param sesion La sesión de entrenamiento a verificar.
     * @return true si existe una sesión con el mismo entrenador y fecha, false en caso contrario.
     */
    private boolean existeSesionConMismoEntrenadorYFecha(SesionEntrenamiento sesion) {
        for (SesionEntrenamiento s : sesiones) {
            if (s.getEntrenador() != null && s.getEntrenador().equals(sesion.getEntrenador()) 
                    && s.getFecha().equals(sesion.getFecha()) && s.getDeporte().equals(sesion.getDeporte())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Carga las sesiones de entrenamiento desde el archivo.
     */
    @Override
    public void cargarDesdeArchivo() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO_SESIONES))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                SesionEntrenamiento sesion = new SesionEntrenamiento(partes[0], Integer.parseInt(partes[1]), Estado.valueOf(partes[2]), null, null, null);
                sesiones.add(sesion);
            }
        } catch (IOException e) {
            System.err.println("Error al cargar sesiones desde archivo: " + e.getMessage());
        }
    }

    /**
     * Guarda las sesiones de entrenamiento en el archivo.
     */
    @Override
    public void guardarEnArchivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO_SESIONES))) {
            for (SesionEntrenamiento sesion : sesiones) {
                writer.write(sesion.getFecha() + "," + sesion.getDuracion() + "," + sesion.getEstado() + "," + sesion.getDeporte().getNombre() + "," + sesion.getEntrenador().getNombre() + "," + sesion.getMiembros());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al guardar sesiones en archivo: " + e.getMessage());
        }
    }

    /**
     * Actualiza la lista de miembros para una sesión existente.
     * @param fecha La fecha de la sesión a actualizar.
     * @param miembros Lista de miembros a agregar.
     */
    public void actualizarMiembrosASesion(String fecha, List<Miembro> miembros) {
        SesionEntrenamiento sesion = obtenerPorNombre(fecha);
        if (sesion != null) {
            sesion.getMiembros().addAll(miembros);
            guardarEnArchivo();
        } else {
            System.out.println("No existe una sesión de entrenamiento con la fecha especificada.");
        }
    }
}
