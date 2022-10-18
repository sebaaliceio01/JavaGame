package com.mycompany.obligatorio1;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Sebastian
 */
public class Tablero {

    public static void main(String[] args) {
        getTablero(getStandard());
    }

    public static String[][] getTableroSeleccionado(String tableroSeleccionado) {
        String[][] tablero = new String[6][6];

        switch (tableroSeleccionado) {
            case "Standard":
                tablero = getStandard();
                break;
            case "Precargado 1":
                tablero = getPrecargado1();
                break;
            case "Precargado 2":
                tablero = getPrecargado2();
                break;
        }

        return tablero;
    }

    public static String[][] getStandard() {
        String[][] tablero = {
                { "A", " ", "A", " ", "A", "R" },
                { " ", "A", "R", " ", "A", "A" },
                { "A", " ", " ", " ", "A", "R" },
                { "R", "A", "R", "A", " ", "A" },
                { "A", " ", "A", " ", "A", "A" },
                { "A", "A", "R", "A", "A", "A" },
        };
        return tablero;
    }

    public static String[][] getPrecargado1() {
        String[][] tablero = {
                { "A", "R", " ", " ", " ", " " },
                { " ", " ", " ", " ", " ", " " },
                { " ", " ", " ", " ", " ", " " },
                { " ", " ", " ", " ", " ", " " },
                { " ", " ", " ", " ", " ", " " },
                { " ", " ", " ", " ", " ", " " },
        };
        return tablero;
    }

    public static String[][] getPrecargado2() {
        String[][] tablero = {
                { "R", " ", " ", " ", " ", " " },
                { " ", " ", " ", " ", " ", " " },
                { " ", " ", " ", " ", " ", " " },
                { " ", " ", " ", " ", " ", " " },
                { " ", " ", " ", " ", " ", " " },
                { " ", " ", " ", " ", "A", "A" },
        };
        return tablero;
    }

    private static int[][] getMatrizCentros() {
        int[][] tablero = {
                { 6, 5, 4, 4, 5, 6 },
                { 5, 3, 2, 2, 3, 5 },
                { 4, 2, 1, 1, 2, 4 },
                { 4, 2, 1, 1, 2, 4 },
                { 5, 3, 2, 2, 3, 5 },
                { 6, 5, 4, 4, 5, 6 },
        };
        return tablero;
    }

    public static void getTablero(String[][] tablero) {
        System.out.print("\t");
        for (int j = 0; j < tablero[0].length; j++) {
            System.out.print(" " + (j + 1));
        }

        System.out.println("");
        for (int i = 0; i < tablero.length; i++) {
            System.out.println("\t" + "+-+-+-+-+-+-+");
            System.out.print(opts(i));
            System.out.print("\t");

            for (int j = 0; j < tablero[0].length; j++) {
                if (tablero[i][j] == "R") {
                    System.out.print("|" + "\033[31m" + tablero[i][j] + "" + "\u001B[0m");
                }

                if (tablero[i][j] == "A") {
                    System.out.print("|" + "\033[34m" + tablero[i][j] + "" + "\u001B[0m");
                }

                if (tablero[i][j] == "#" || tablero[i][j] == "*" || tablero[i][j] == "E") {
                    System.out.print("|" + "\033[32m" + tablero[i][j] + "" + "\u001B[0m");
                }

                if (tablero[i][j] == " ") {
                    System.out.print("|" + tablero[i][j] + "");
                }
            }

            System.out.print("|");

            System.out.print("\t");
            System.out.print("   " + opts(i));

            System.out.println("");

        }

        System.out.println("\t" + "+-+-+-+-+-+-+");
        System.out.print("\t");
        for (int j = 0; j < tablero[0].length; j++) {
            System.out.print(" " + (j + 1));
        }

        System.out.println("");
    }

    public static String[][] iniciarJugada(String[][] tablero, String posicion, Boolean jugador) {
        Scanner input = new Scanner(System.in);

        int[] pos = new int[2];

        tablero = validarJugada(tablero, posicion, jugador);

        String oponente = getJugadorDelTurno(!jugador);

        getTablero(tablero);

        String posicionJugada = input.nextLine();

        pos = posicion(posicionJugada);

        int fila = pos[0];
        int columna = pos[1];

        if (((tablero[fila][columna] != "#" && tablero[fila][columna] != "*") && !valePosicion(posicionJugada))
                || tablero[fila][columna] == " " || tablero[fila][columna] == oponente) {
            System.out.println("Jugada invalida, reingrese la jugada");
            iniciarJugada(tablero, posicion, jugador);
        }

        tablero = setJugada(tablero, posicion, posicionJugada, jugador);

        return tablero;
    }

    public static Boolean tieneJugada(String[][] tablero, String posicion, Boolean jugador) {
        Boolean tieneJugada = false;

        int[] pos = posicion(posicion);

        int fila = pos[0];
        int columna = pos[1];

        String jugadorActual = getJugadorDelTurno(jugador);
        String jugadorContrario = getJugadorDelTurno(!jugador);

        tablero = getJugadaValidada(tablero, fila, columna, jugadorContrario, jugadorActual);

        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                if (tablero[i][j] == "*") {
                    tieneJugada = true;
                }

                if (tablero[i][j] == "#") {
                    tieneJugada = true;
                }
            }
        }

        return tieneJugada;
    }

    public static String[][] validarJugada(String[][] tablero, String posicion, Boolean jugador) {
        int[] pos = posicion(posicion);

        int fila = pos[0];
        int columna = pos[1];

        String jugadorActual = getJugadorDelTurno(jugador);
        String jugadorContrario = getJugadorDelTurno(!jugador);

        if (tablero[fila][columna] == jugadorActual) {
            tablero[fila][columna] = "E";
        }

        return getJugadaValidada(tablero, fila, columna, jugadorContrario, jugadorActual);
    }

    private static String[][] getJugadaValidada(String[][] tablero, int fila, int columna, String oponente,
            String jugadorActual) {

        if (tablero[fila][columna == 5 ? columna : columna + 1] == " ") {
            tablero[fila][columna == 5 ? columna : columna + 1] = "*";
        }

        if (tablero[fila][columna == 0 ? columna : columna - 1] == " ") {
            tablero[fila][columna == 0 ? columna : columna - 1] = "*";
        }

        if (tablero[fila == 0 ? fila : fila - 1][columna == 0 ? columna : columna - 1] == " ") {
            tablero[fila == 0 ? fila : fila - 1][columna == 0 ? columna : columna - 1] = "*";
        }

        if (tablero[fila == 0 ? fila : fila - 1][columna == 5 ? columna : columna + 1] == " ") {
            tablero[fila == 0 ? fila : fila - 1][columna == 5 ? columna : columna + 1] = "*";
        }

        if (tablero[fila == 0 ? fila : fila - 1][columna] == " ") {
            tablero[fila == 0 ? fila : fila - 1][columna] = "*";
        }

        if (tablero[fila == 5 ? fila : fila + 1][columna == 0 ? columna : columna - 1] == " ") {
            tablero[fila == 5 ? fila : fila + 1][columna == 0 ? columna : columna - 1] = "*";
        }

        if (tablero[fila == 5 ? fila : fila + 1][columna == 5 ? columna : columna + 1] == " ") {
            tablero[fila == 5 ? fila : fila + 1][columna == 5 ? columna : columna + 1] = "*";
        }

        if (tablero[fila == 5 ? fila : fila + 1][columna] == " ") {
            tablero[fila == 5 ? fila : fila + 1][columna] = "*";
        }

        // Boolean diagonalSD = true;
        // Boolean diagonalSI = true;

        // Boolean diagonalID = true;
        // Boolean diagonalII = true;

        Boolean columnaS = true;
        Boolean columnaI = true;

        Boolean filaD = true;
        Boolean filaI = true;

        // Valido fila hacia la derecha
        for (int j = columna; j < tablero[0].length; j++) {
            if ((tablero[fila][j == 5 ? j : j + 1] == " " || tablero[fila][j == 5 ? j : j + 1] == "*")
                    || tablero[fila][j == 5 ? j : j + 1] == oponente
                            && filaD) {
                if (tablero[fila][j == 5 ? j : j + 1] == oponente) {
                    tablero[fila][j == 5 ? j : j + 1] = "#";
                    filaD = false;
                }
            } else {
                filaD = false;
            }
        }

        // Valido fila hacia la izquierda
        for (int j = columna; j >= 0; j--) {
            if ((tablero[fila][j == 0 ? j : j - 1] == " " || tablero[fila][j == 0 ? j : j - 1] == "*"
                    || tablero[fila][j == 0 ? j : j - 1] == oponente)
                    && filaI) {
                if (tablero[fila][j == 0 ? j : j - 1] == oponente) {
                    tablero[fila][j == 0 ? j : j - 1] = "#";
                    filaI = false;
                }
            } else {
                filaI = false;
            }
        }

        // Valido columna hacia abajo
        for (int i = fila; i < tablero.length; i++) {
            if ((tablero[i == 5 ? i : i + 1][columna] == " " || tablero[i == 5 ? i : i + 1][columna] == "*"
                    || tablero[i == 5 ? i : i + 1][columna] == oponente)
                    && columnaS) {
                if (tablero[i == 5 ? i : i + 1][columna] == oponente) {
                    tablero[i == 5 ? i : i + 1][columna] = "#";
                    columnaS = false;
                }
            } else {
                columnaS = false;
            }
        }

        // Valido columna hacia arriba
        for (int i = fila; i >= 0; i--) {
            if ((tablero[i == 0 ? i : i - 1][columna] == " " || tablero[i == 0 ? i : i - 1][columna] == "*"
                    || tablero[i == 0 ? i : i - 1][columna] == oponente)
                    && columnaI) {
                if (tablero[i == 0 ? i : i - 1][columna] == oponente) {
                    tablero[i == 0 ? i : i - 1][columna] = "#";
                    columnaI = false;
                }
            } else {
                columnaI = false;
            }
        }

        return tablero;
    }

    public static String[][] setJugada(String[][] tablero, String posicionActual, String posicion,
            Boolean jugador) {
        int[] pos = posicion(posicion);
        int[] posActual = posicion(posicionActual);

        int fila = pos[0];
        int columna = pos[1];

        int filaActual = posActual[0];
        int columnaActual = posActual[1];

        String jugadorActual = getJugadorDelTurno(jugador);
        String jugadorContrario = getJugadorDelTurno(!jugador);

        if (tablero[fila][columna] == "*" || tablero[fila][columna] == "#") {
            tablero[fila][columna] = jugadorActual;
        }

        tablero[filaActual][columnaActual] = " ";

        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                if (tablero[i][j] == "*") {
                    tablero[i][j] = " ";
                }

                if (tablero[i][j] == "#") {
                    tablero[i][j] = jugadorContrario;
                }
            }
        }

        return tablero;
    }

    public static Boolean setGanador(String[][] tablero, int jugador, Boolean turno) {
        Boolean ganador = true;

        Jugador nombreJugador = new Jugador();
        Ranking ranking = new Ranking();

        ArrayList<Jugador> listaJugadores = nombreJugador.jugadoresListaObjetos;

        String oponente = getJugadorDelTurno(!turno);

        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                if (tablero[i][j] == oponente) {
                    ganador = false;
                }
            }
        }

        if (ganador) {
            System.out.println("===== Felicitaciones " + (nombreJugador.getJugador(jugador).toUpperCase())
                    + " ganaste el juego! =====");

            for (Jugador jugadorSeleccionado : listaJugadores) {
                if (jugadorSeleccionado.getAlias() == nombreJugador.getJugador(jugador)) {
                    ranking.setVictoria(jugadorSeleccionado);
                }
            }
        }

        return ganador;
    }

    public static String getJugadorDelTurno(Boolean jugador) {
        String jugadorSeleccionado = "";

        if (jugador) {
            jugadorSeleccionado = "R";
        }

        if (!jugador) {
            jugadorSeleccionado = "A";
        }

        return jugadorSeleccionado;
    }

    public static int[] posicion(String pos) {
        int[] posicion = new int[2];

        switch (pos) {
            case "A1":
                posicion[0] = 0;
                posicion[1] = 0;
                break;
            case "A2":
                posicion[0] = 0;
                posicion[1] = 1;
                break;
            case "A3":
                posicion[0] = 0;
                posicion[1] = 2;
                break;
            case "A4":
                posicion[0] = 0;
                posicion[1] = 3;
                break;
            case "A5":
                posicion[0] = 0;
                posicion[1] = 4;
                break;
            case "A6":
                posicion[0] = 0;
                posicion[1] = 5;
                break;
            case "B1":
                posicion[0] = 1;
                posicion[1] = 0;
                break;
            case "B2":
                posicion[0] = 1;
                posicion[1] = 1;
                break;
            case "B3":
                posicion[0] = 1;
                posicion[1] = 2;
                break;
            case "B4":
                posicion[0] = 1;
                posicion[1] = 3;
                break;
            case "B5":
                posicion[0] = 1;
                posicion[1] = 4;
                break;
            case "B6":
                posicion[0] = 1;
                posicion[1] = 5;
                break;
            case "C1":
                posicion[0] = 2;
                posicion[1] = 0;
                break;
            case "C2":
                posicion[0] = 2;
                posicion[1] = 1;
                break;
            case "C3":
                posicion[0] = 2;
                posicion[1] = 2;
                break;
            case "C4":
                posicion[0] = 2;
                posicion[1] = 3;
                break;
            case "C5":
                posicion[0] = 2;
                posicion[1] = 4;
                break;
            case "C6":
                posicion[0] = 2;
                posicion[1] = 5;
                break;
            case "D1":
                posicion[0] = 3;
                posicion[1] = 0;
                break;
            case "D2":
                posicion[0] = 3;
                posicion[1] = 1;
                break;
            case "D3":
                posicion[0] = 3;
                posicion[1] = 2;
                break;
            case "D4":
                posicion[0] = 3;
                posicion[1] = 3;
                break;
            case "D5":
                posicion[0] = 3;
                posicion[1] = 4;
                break;
            case "D6":
                posicion[0] = 3;
                posicion[1] = 5;
                break;
            case "E1":
                posicion[0] = 4;
                posicion[1] = 0;
                break;
            case "E2":
                posicion[0] = 4;
                posicion[1] = 1;
                break;
            case "E3":
                posicion[0] = 4;
                posicion[1] = 2;
                break;
            case "E4":
                posicion[0] = 4;
                posicion[1] = 3;
                break;
            case "E5":
                posicion[0] = 4;
                posicion[1] = 4;
                break;
            case "E6":
                posicion[0] = 4;
                posicion[1] = 5;
                break;
            case "F1":
                posicion[0] = 5;
                posicion[1] = 0;
                break;
            case "F2":
                posicion[0] = 5;
                posicion[1] = 1;
                break;
            case "F3":
                posicion[0] = 5;
                posicion[1] = 2;
                break;
            case "F4":
                posicion[0] = 5;
                posicion[1] = 3;
                break;
            case "F5":
                posicion[0] = 5;
                posicion[1] = 4;
                break;
            case "F6":
                posicion[0] = 5;
                posicion[1] = 5;
                break;
        }
        return posicion;
    }

    public static Boolean valePosicion(String pos) {
        Boolean posicion = false;

        switch (pos) {
            case "A1":
                posicion = true;
                break;
            case "A2":
                posicion = true;
                break;
            case "A3":
                posicion = true;
                break;
            case "A4":
                posicion = true;
                break;
            case "A5":
                posicion = true;
                break;
            case "A6":
                posicion = true;
                break;
            case "B1":
                posicion = true;
                break;
            case "B2":
                posicion = true;
                break;
            case "B3":
                posicion = true;
                break;
            case "B4":
                posicion = true;
                break;
            case "B5":
                posicion = true;
                break;
            case "B6":
                posicion = true;
                break;
            case "C1":
                posicion = true;
                break;
            case "C2":
                posicion = true;
                break;
            case "C3":
                posicion = true;
                break;
            case "C4":
                posicion = true;
                break;
            case "C5":
                posicion = true;
                break;
            case "C6":
                posicion = true;
                break;
            case "D1":
                posicion = true;
                break;
            case "D2":
                posicion = true;
                break;
            case "D3":
                posicion = true;
                break;
            case "D4":
                posicion = true;
                break;
            case "D5":
                posicion = true;
                break;
            case "D6":
                posicion = true;
                break;
            case "E1":
                posicion = true;
                break;
            case "E2":
                posicion = true;
                break;
            case "E3":
                posicion = true;
                break;
            case "E4":
                posicion = true;
                break;
            case "E5":
                posicion = true;
                break;
            case "E6":
                posicion = true;
                break;
            case "F1":
                posicion = true;
                break;
            case "F2":
                posicion = true;
                break;
            case "F3":
                posicion = true;
                break;
            case "F4":
                posicion = true;
                break;
            case "F5":
                posicion = true;
                break;
            case "F6":
                posicion = true;
                break;
        }
        return posicion;
    }

    public static String opts(int i) {
        String option = " ";

        switch (i) {
            case 0:
                option = "A";
                break;
            case 1:
                option = "B";
                break;
            case 2:
                option = "C";
                break;
            case 3:
                option = "D";
                break;
            case 4:
                option = "E";
                break;
            case 5:
                option = "F";
                break;
        }

        return option;
    }
}