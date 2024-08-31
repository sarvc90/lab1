package com.example.Modelo;

import java.util.List;

/**
 * Interfaz genérica para operaciones CRUD en entidades.
 * @param <T> Tipo de entidad que maneja el repositorio.
 */
public interface Repository<T> {
    /**
     * Crea una nueva entidad y la agrega al repositorio.
     * @param entity Entidad a crear.
     */
    void crear(T entity);

    /**
     * Obtiene una lista de todas las entidades en el repositorio.
     * @return Lista de todas las entidades.
     */
    List<T> obtenerTodos();

    /**
     * Obtiene una entidad específica por su nombre.
     * @param nombre Nombre de la entidad a obtener.
     * @return Entidad con el nombre especificado, o null si no se encuentra.
     */
    T obtenerPorNombre(String nombre);

    /**
     * Actualiza una entidad existente en el repositorio.
     * @param entity Entidad a actualizar.
     */
    void actualizar(T entity);

    /**
     * Elimina una entidad del repositorio por su nombre.
     * @param nombre Nombre de la entidad a eliminar.
     */
    void eliminar(String nombre);

    /**
     * Guarda todas las entidades actuales en un archivo.
     */
    void guardarEnArchivo();

    /**
     * Carga todas las entidades desde un archivo al repositorio.
     */
    void cargarDesdeArchivo();
}
