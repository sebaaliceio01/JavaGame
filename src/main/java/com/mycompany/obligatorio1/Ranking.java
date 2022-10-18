package com.mycompany.obligatorio1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;;

/**
 *
 * @author Sebastian
 */
public class Ranking {

    ArrayList<Jugador> lista = Jugador.jugadoresListaObjetos;

    public static void main(String[] args) {
        // Collection.sort(lista, new ComparatorJugador());
    }

    public static void getRanking(ArrayList<Jugador> lista) {
        System.out.println("======== Ranking ==========");
        System.out.println();

        List<Jugador> list = lista;

        Collections.sort(list, new ComparatorJugador());

        for (Jugador jugador : lista) {
            System.out.println("Jugador : " + jugador.getNombre());
            System.out.println("Alias : " + jugador.getAlias());
            System.out.println("Partidas Jugadas : " + jugador.getPartidasJugadas());
            System.out.println("Partidas Ganadas : " + jugador.getPartidasGanadas());
            System.out.println();
        }
        System.out.println("===========================");
    }

    public static void setVictoria(Jugador jugador) {
        int partidasGanadas = jugador.getPartidasGanadas();
        jugador.setPartidasGanadas(partidasGanadas + 1);
    }

    public static void setJugada(int pos) {
        Jugador jugador = new Jugador();

        String getJugador = jugador.getJugador(pos);

        for (Jugador jugadorSeleccionado : jugador.jugadoresListaObjetos) {
            if (jugadorSeleccionado.getAlias() == getJugador) {
                jugador = jugadorSeleccionado;
            }
        }

        int partidaJugadas = jugador.getPartidasJugadas();
        jugador.setPartidasJugadas(partidaJugadas + 1);
    }
}
