package com.mycompany.obligatorio1;

import java.util.Comparator;

public class ComparatorJugador implements Comparator<Jugador> {
    @Override
    public int compare(Jugador primerJugador, Jugador segundoJugador) {
        int res = 0;
        if (primerJugador.getPartidasGanadas() < segundoJugador.getPartidasGanadas()) {
            res = 1;
        } else if (primerJugador.getPartidasGanadas() > segundoJugador.getPartidasGanadas()) {
            res = -1;
        } else {
            res = 0;
        }
        return res;
    }
}
