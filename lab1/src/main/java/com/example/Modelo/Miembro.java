package com.example.Modelo;

/**
 * Representa a un miembro del club.
 */
public class Miembro {
    private String nombre;
    private int edad;
    private Deporte deporte;

    /**
     * Crea un nuevo miembro con el nombre y la edad especificados.
     * @param nombre Nombre del miembro.
     * @param edad Edad del miembro.
     */
    public Miembro(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
        this.deporte = null; // Inicializa el deporte como nulo
    }

    /**
     * Crea un nuevo miembro con el nombre, la edad y el deporte especificados.
     * @param nombre Nombre del miembro.
     * @param edad Edad del miembro.
     * @param deporte Deporte asociado al miembro.
     */
    public Miembro(String nombre, int edad, Deporte deporte) {
        this.nombre = nombre;
        this.edad = edad;
        this.deporte = deporte;
    }

    /**
     * Obtiene el deporte asociado a este miembro.
     * @return Deporte asociado.
     */
    public Deporte getDeporte() {
        return deporte;
    }

    /**
     * Obtiene el nombre del miembro.
     * @return Nombre del miembro.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene la edad del miembro.
     * @return Edad del miembro.
     */
    public int getEdad() {
        return edad;
    }

    /**
     * Determina si el miembro es adulto (18 años o más).
     * @return Verdadero si el miembro es adulto, falso en caso contrario.
     */
    public boolean siEsAdulto() {
        return this.edad >= 18;
    }

    /**
     * Establece el deporte asociado a este miembro.
     * @param deporte Deporte a asociar.
     */
    public void setDeporte(Deporte deporte) {
        this.deporte = deporte;
    }

    /**
     * Establece el nombre del miembro.
     * @param nombre Nuevo nombre del miembro.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Establece la edad del miembro.
     * @param edad Nueva edad del miembro.
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }

    /**
     * Verifica si el miembro tiene un deporte asociado.
     * @return Verdadero si el miembro tiene un deporte asociado, falso en caso contrario.
     */
    public boolean tieneDeporte() {
        return deporte != null;
    }
}
