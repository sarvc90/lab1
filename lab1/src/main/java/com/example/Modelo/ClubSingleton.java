package com.example.Modelo;

public class ClubSingleton {
    private static Club club;

    private ClubSingleton() {}

    public static Club getClub() {
        if (club == null) {
            club = new Club("Mi Club");
        }
        return club;
    }
}