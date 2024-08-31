package com.example.Modelo;

import java.util.ArrayList;
import java.util.List;

public class Club {
    private String nombre; // Nombre del club
    private DeporteRepository deporteRepository; // Repositorio de deportes
    private MiembroRepository miembroRepository; // Repositorio de miembros
    private EntrenadorRepository entrenadorRepository; // Repositorio de entrenadores
    private SesionEntrenamientoRepository sesionEntrenamientoRepository; // Repositorio de sesiones de entrenamiento
    private Administrador administrador; // Administrador del club

    /**
     * Constructor del club.
     * 
     * @param nombre Nombre del club
     */
    public Club(String nombre) {
        this.nombre = nombre;
        this.deporteRepository = new DeporteRepository();
        this.miembroRepository = new MiembroRepository();
        this.entrenadorRepository = new EntrenadorRepository();
        this.sesionEntrenamientoRepository = new SesionEntrenamientoRepository();
        this.administrador = null;
    }

    /**
     * Obtiene el nombre del club.
     * 
     * @return Nombre del club
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el administrador del club.
     * 
     * @return Administrador del club
     */
    public Administrador getAdministrador() {
        return administrador;
    }

    /**
     * Establece el administrador del club.
     * 
     * @param administrador Administrador a establecer
     */
    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

    /**
     * Inscribe un miembro en un deporte, con restricciones según la edad.
     * 
     * @param miembro Miembro a inscribir
     * @param deporte Deporte en el que inscribir al miembro
     * @throws MiembroMenorDeEdadException Si el miembro es menor de edad y el deporte tiene un nivel de dificultad alto
     */
    public void inscribirMiembro(Miembro miembro, Deporte deporte) throws MiembroMenorDeEdadException {
        if (deporte == null) {
            System.out.println("Error: Deporte is null");
            return;
        }

        if (deporte.getNivelDificultad() == null) {
            System.out.println("Error: NivelDificultad is null");
            return;
        }

        if (miembro.siEsAdulto()) {
            miembro.setDeporte(deporte);
            miembroRepository.actualizar(miembro);
            deporte.addMiembro(miembro);
        } else {
            switch (deporte.getNivelDificultad()) {
                case BAJO:
                case MEDIO:
                    miembro.setDeporte(deporte);
                    miembroRepository.actualizar(miembro);
                    deporte.addMiembro(miembro);
                    break;
                case ALTO:
                    throw new MiembroMenorDeEdadException("El miembro es menor de edad y el deporte tiene un nivel de dificultad alto.");
            }
        }
    }

    // Métodos de CRUD para Deportes
    public void crearDeporte(Deporte deporte) {
        deporteRepository.crear(deporte);
    }

    public List<Deporte> obtenerDeportes() {
        return deporteRepository.obtenerTodos();
    }

    public void actualizarDeporte(Deporte deporte) {
        deporteRepository.actualizar(deporte);
    }

    public void eliminarDeporte(String nombre) {
        deporteRepository.eliminar(nombre);
    }

    // Métodos de CRUD para Miembros
    public void crearMiembro(Miembro miembro) {
        miembroRepository.crear(miembro);
    }

    public List<Miembro> obtenerMiembros() {
        return miembroRepository.obtenerTodos();
    }

    public void actualizarMiembro(Miembro miembro) {
        miembroRepository.actualizar(miembro);
    }

    public void eliminarMiembro(String nombre) {
        miembroRepository.eliminar(nombre);
    }

    // Métodos de CRUD para Entrenadores
    public void crearEntrenador(Entrenador entrenador) {
        entrenadorRepository.crear(entrenador);
    }

    public List<Entrenador> obtenerEntrenadores() {
        return entrenadorRepository.obtenerTodos();
    }

    public void actualizarEntrenador(Entrenador entrenador) {
        entrenadorRepository.actualizar(entrenador);
    }

    public void eliminarEntrenador(String nombre) {
        entrenadorRepository.eliminar(nombre);
    }

    // Métodos de CRUD para SesionEntrenamiento
    public void crearSesionEntrenamiento(SesionEntrenamiento sesionEntrenamiento) {
        sesionEntrenamientoRepository.crear(sesionEntrenamiento);
    }

    public List<SesionEntrenamiento> obtenersSesionEntrenamientos() {
        return sesionEntrenamientoRepository.obtenerTodos();
    }

    public void actualizarSesionEntrenamiento(SesionEntrenamiento sesionEntrenamiento) {
        sesionEntrenamientoRepository.actualizar(sesionEntrenamiento);
    }

    public void eliminarSesionEntrenamiento(String fecha) {
        sesionEntrenamientoRepository.eliminar(fecha);
    }

    /**
     * Obtiene todos los estados posibles.
     * 
     * @return Lista de estados
     */
    public List<Estado> getEstados() {
        List<Estado> estados = new ArrayList<>();
        for (Estado estado : Estado.values()) {
            estados.add(estado);
        }
        return estados;
    }
}
