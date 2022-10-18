package com.mycompany.obligatorio1;

import java.util.ArrayList;

/**
 *
 * @author Sebastian
 */
public class Jugador {

    private String nombre;
    private String alias;

    private int edad;
    private int partidasJugadas = 0;
    private int partidasGanadas = 0;

    private static ArrayList<String> jugadores = new ArrayList<>();

    public static ArrayList<Jugador> jugadoresListaObjetos = new ArrayList<>();

    public static void main(String[] args) {
    }

    public void Jugador(String nombre, int edad, String alias, int partidasJugadas, int partidasGanadas) {
        this.nombre = nombre;
        this.edad = edad;
        this.alias = alias;
        this.partidasJugadas = partidasJugadas;
        this.partidasGanadas = partidasGanadas;
    }

    // Setters

    public void setPartidasJugadas(int partida) {
        this.partidasJugadas = partida;
    }

    public void setPartidasGanadas(int partida) {
        this.partidasGanadas = partida;
    }

    public void setPartidasJugadas(String nombre) {
        this.nombre = nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void agregarJugador(String nombre) {
        this.jugadores.add(nombre);
    }

    public void setJugadorObjeto(Jugador jugador) {
        this.jugadoresListaObjetos.add(jugador);
    }

    // Getters

    public int getPartidasJugadas() {
        return partidasJugadas;
    }

    public int getPartidasGanadas() {
        return partidasGanadas;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public String getAlias() {
        return alias;
    }

    // Getters total elementos

    public ArrayList<String> getJugadores() {
        return jugadores;
    }

    public Jugador getJugadorPorAlias(String alias) {
        ArrayList<Jugador> jugadores = jugadoresListaObjetos;

        Jugador jugador = new Jugador();

        for (Jugador jugadorSeleccionado : jugadores) {
            if (jugadorSeleccionado.getAlias().equalsIgnoreCase(alias)) {
                jugador = jugadorSeleccionado;
            }
        }

        return jugador;
    }

    public String getJugador(int index) {
        return jugadores.get(index);
    }

    public Boolean existeAlias(String alias) {
        Boolean existe = false;

        ArrayList<Jugador> jugadores = jugadoresListaObjetos;

        for (Jugador jugadorSeleccionado : jugadores) {
            if (jugadorSeleccionado.getAlias().equalsIgnoreCase(alias)) {
                existe = true;
            }
        }
        return existe;
    }

    @Override
    public String toString() {
        return this.nombre + " " + this.edad + " " + this.alias;
    }

}
