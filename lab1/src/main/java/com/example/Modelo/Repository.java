package com.example.Modelo;

import java.util.List;

public interface Repository<T> {
    void crear(T entity);
    List<T> obtenerTodos();
    T obtenerPorNombre(String nombre);
    void actualizar(T entity);
    void eliminar(String nombre);
    void guardarEnArchivo();
    void cargarDesdeArchivo();
}