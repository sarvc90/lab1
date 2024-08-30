package com.example.Modelo;

import java.util.ArrayList;
import java.util.List;

public class Club {
    private String nombre;
    private DeporteRepository deporteRepository;
    private MiembroRepository miembroRepository;
    private EntrenadorRepository entrenadorRepository;
    private SesionEntrenamientoRepository sesionEntrenamientoRepository;
    private Administrador administrador;

    public Club(String nombre) {
        this.nombre = nombre;
        this.deporteRepository = new DeporteRepository();
        this.miembroRepository = new MiembroRepository();
        this.entrenadorRepository = new EntrenadorRepository();
        this.sesionEntrenamientoRepository = new SesionEntrenamientoRepository();
        this.administrador = null;
    }

    public String getNombre() {
        return nombre;
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }
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
            // Adult members can register to any sport
            miembro.setDeporte(deporte);
            miembroRepository.actualizar(miembro);
            deporte.addMiembro(miembro);
        } else {
            // Minor members have restrictions based on sport difficulty
            switch (deporte.getNivelDificultad()) {
                case BAJO:
                case MEDIO:
                    // Minor members can register to low or medium difficulty sports
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

    //Métodos de CRUD para Entrenadores
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
    
    
    //Métodos de CRUD para SesionEntrenamiento
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
    
    public List<Estado> getEstados() {
        List<Estado> estados = new ArrayList<>();
        for (Estado estado : Estado.values()) {
            estados.add(estado);
        }
        return estados;
    }

}