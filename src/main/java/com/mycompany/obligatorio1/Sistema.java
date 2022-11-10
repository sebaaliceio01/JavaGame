package com.mycompany.obligatorio1;

import java.util.*;

//Sebastian Aliceio 286406
//Ianco Rodriguez 243406

/**
 *
 * @author Sebastian
 */
public class Sistema {

    public static String tableroFijado = "Standard";

    public static int jugador1 = Integer.MAX_VALUE;
    public static int jugador2 = Integer.MAX_VALUE;

    public static void main(String[] args) {
        mostrarTablero();
    }

    public static void mostrarTablero() {
        Scanner input = new Scanner(System.in);
        Interfaz.mostrarTablero();
        String opcionElegida = input.nextLine();

        if (opcionElegida.equalsIgnoreCase("a")) {
            opcionA();
        } else if (opcionElegida.equalsIgnoreCase("b")) {
            opcionB();
        } else if (opcionElegida.equalsIgnoreCase("c")) {
            opcionC();
        } else if (opcionElegida.equalsIgnoreCase("d")) {
            opcionD();
        } else if (opcionElegida.equalsIgnoreCase("e")) {
            System.out.println("====== Gracias por jugar ======");
        } else {
            System.out.println("Entrada invalida");
            mostrarTablero();
        }
    }

    public static void opcionA() {
        Scanner input = new Scanner(System.in);

        String setNombre = "";
        int setEdad = 0;
        String setAlias = "";

        try {
            System.out.println("Ingrese el nombre del jugador");
            setNombre = input.nextLine();
        } catch (Exception e) {
            System.out.println("Error, por favor, vuelva a introducir los datos");
            opcionA();
            return;
        }

        try {
            System.out.println("Ingrese la edad del jugador");
            setEdad = input.nextInt();
            input.nextLine();
        } catch (Exception e) {
            System.out.println("Error, por favor, vuelva a introducir los datos");
            opcionA();
            return;
        }

        try {
            Jugador jugador = new Jugador();

            System.out.println("Ingrese el alias del jugador");
            setAlias = input.nextLine();

            Boolean repite = jugador.existeAlias(setAlias);

            if (repite) {
                System.out.println("Ya existe un jugador con el alias " + setAlias);
                opcionA();
                return;
            }

        } catch (Exception e) {
            System.out.println("Error, por favor, vuelva a introducir los datos");
            opcionA();
            return;
        }

        Jugador nuevo = new Jugador();

        nuevo.Jugador(setNombre, setEdad, setAlias, 0, 0);

        nuevo.setJugadorObjeto(nuevo);

        nuevo.agregarJugador(setAlias);

        mostrarTablero();
    }

    public static void opcionB() {
        Scanner input = new Scanner(System.in);

        System.out.println("Opciones: ");
        System.out.println("a) Standard ");
        System.out.println("b) Precargado 1");
        System.out.println("c) Precargado 2");

        String opcionSeleccionada = input.nextLine();

        if (opcionSeleccionada.equalsIgnoreCase("a")) {
            tableroFijado = "Standard";
        } else if (opcionSeleccionada.equalsIgnoreCase("b")) {
            tableroFijado = "Precargado 1";
        } else if (opcionSeleccionada.equalsIgnoreCase("c")) {
            tableroFijado = "Precargado 2";
        } else {
            System.out.println("Entrada invalida");
            opcionB();
        }

        mostrarTablero();
    }

    public static void opcionC() {
        Scanner input = new Scanner(System.in);

        boolean finDelJuego = false;
        boolean turnoRojo = true;

        Jugador jugador = new Jugador();
        Tablero tablero = new Tablero();

        String[][] tableroSeleccionado = tablero.getTableroSeleccionado(tableroFijado);

        ArrayList<String> jugadores = jugador.getJugadores();

        if (jugadores.size() < 2) {
            System.out.println("Debe registrar al menos 2 jugadores para poder jugar");
            mostrarTablero();
            return;
        }

        Iterator<String> iter = jugadores.iterator();

        System.out.println("==== LISTA DE JUGADORES ====");
        int index = 0;
        while (iter.hasNext()) {
            System.out.println(index + ") " + iter.next());
            index++;
        }

        System.out.println("=============================");

        try {
            System.out.println("Elija el numero de la opcion del jugador que desea como jugador" + "\033[31m" + " ROJO "
                    + "\u001B[0m" + "Ej: 0, 1, 2...");
            jugador1 = input.nextInt();

            if (jugador1 > jugadores.size() - 1 || jugador1 < 0) {
                System.out.println("Ese jugador no existe, por favor, vuelva a intentarlo");
                opcionC();
            }

        } catch (Exception e) {
            System.out.println("Error, dato invalido, vuelva a ingresar los datos");
            opcionC();
        }

        try {
            System.out.println("Elija el numero de la opcion del jugador que desea como jugador" + "\033[34m" + " AZUL "
                    + "\u001B[0m" + " Ej: 0, 1, 2...");
            jugador2 = input.nextInt();

            if (jugador2 == jugador1) {
                System.out.println("El mismo jugador no puede ser seleccionado dos veces");
                opcionC();
            }

            if (jugador2 > jugadores.size() - 1 || jugador2 < 0) {
                System.out.println("Ese jugador no existe, por favor, vuelva a intentarlo");
                opcionC();
            }

        } catch (Exception e) {
            System.out.println("Error, dato invalido, vuelva a ingresar los datos");
            opcionC();
        }
        input.nextLine();

        System.out.println("Sus datos son correctos?");
        System.out.println("Confirmar: S, No confirmar: N, Salir: X");
        String confirmacion = input.nextLine();

        if (confirmacion.equalsIgnoreCase("N")) {
            opcionC();
            input.close();
            return;
        }

        if (confirmacion.equalsIgnoreCase("X")) {
            mostrarTablero();
            input.close();
            return;
        }

        System.out.println("=======  Juego iniciado =======");
        System.out.println();

        Ranking.setJugada(jugador1);
        Ranking.setJugada(jugador2);

        try {
            tablero.getTablero(tableroSeleccionado);
        } catch (Exception e) {
            System.out.println("Ha ocurrido algun error, por favor, intente otra vez");
        }

        System.out.println();

        try {
            while (!finDelJuego) {
                System.out.println();
                System.out.println("===============================");

                if (turnoRojo) {
                    String player1 = jugador.getJugador(jugador1);
                    System.out.println("Turno de " + player1 + " (" + (turnoRojo ? "Turno del rojo" : "") + ")");
                } else {
                    String player2 = jugador.getJugador(jugador2);
                    System.out.println("Turno de " + player2 + " (" + (!turnoRojo ? "Turno del azul" : "") + ")");
                }

                System.out.println("Elegir posicion, o si desea terminar ingrese x");
                String posicion = input.nextLine();

                String jugadorActual = tablero.getJugadorDelTurno(turnoRojo);

                int[] pos = tablero.posicion(posicion);

                if (posicion.equalsIgnoreCase("x")) {
                    finDelJuego = true;
                    System.out.println();
                    System.out.println("===== Felicitaciones "
                            + (jugador.getJugador(turnoRojo ? jugador2 : jugador1).toUpperCase())
                            + " ganaste el juego! =====");
                    System.out.println();
                    Ranking.setVictoria(
                            jugador.getJugadorPorAlias(turnoRojo ? jugadores.get(jugador2) : jugadores.get(jugador1)));
                    mostrarTablero();
                    input.close();
                }

                if ((tableroSeleccionado[pos[0]][pos[1]] == jugadorActual) && tablero.valePosicion(posicion)) {

                    Boolean juega = tablero.tieneJugada(tableroSeleccionado, posicion, turnoRojo);

                    if (!juega) {
                        System.out.println(
                                "El jugador "
                                        + (turnoRojo ? jugador.getJugador(jugador1) : jugador.getJugador(jugador2))
                                        + " no tiene jugadas para realizar");

                        turnoRojo = !turnoRojo;

                        System.out.println();

                        tablero.getTablero(tableroSeleccionado);

                        System.out.println();
                    } else {
                        tableroSeleccionado = tablero.iniciarJugada(tableroSeleccionado, posicion, turnoRojo);

                        tablero.getTablero(tableroSeleccionado);

                        System.out.println();

                        finDelJuego = tablero.setGanador(tableroSeleccionado, turnoRojo ? jugador1 : jugador2,
                                turnoRojo);

                        System.out.println();

                        turnoRojo = !turnoRojo;
                    }
                } else {
                    System.out.println("Error, vuelva a ingresar la jugada");
                    tablero.getTablero(tableroSeleccionado);
                }

            }
        } catch (Exception e) {
            System.out.println("Ha ocurrido algun error, por favor, intente otra vez");
            mostrarTablero();
            return;
        }

        mostrarTablero();
    }

    public static void opcionD() {
        Ranking ranking = new Ranking();

        ArrayList<Jugador> listaRanking = ranking.lista;

        if (listaRanking.size() < 1) {
            System.out.println("Aun no existen registros");
            mostrarTablero();
            return;
        }

        ranking.getRanking(listaRanking);

        mostrarTablero();
    }
}
