package com.example.Modelo;

/**
 * Excepción lanzada cuando un miembro menor de edad intenta realizar una acción que requiere ser adulto.
 */
public class MiembroMenorDeEdadException extends Exception {
    
    /**
     * Crea una nueva instancia de la excepción con el mensaje especificado.
     * @param message Mensaje que describe la excepción.
     */
    public MiembroMenorDeEdadException(String message) {
        super(message);
    }
}
