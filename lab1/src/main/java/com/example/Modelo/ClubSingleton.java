package com.example.Modelo;

/**
 * Singleton para la clase Club. Garantiza que solo haya una instancia de Club en la aplicación.
 */
public class ClubSingleton {
    private static Club club; // Instancia única del club

    // Constructor privado para evitar instanciación externa
    private ClubSingleton() {}

    /**
     * Obtiene la instancia única del Club.
     * Si la instancia aún no ha sido creada, la crea con el nombre "Mi Club".
     * 
     * @return La instancia única de Club
     */
    public static Club getClub() {
        if (club == null) {
            club = new Club("Mi Club");
        }
        return club;
    }
}
