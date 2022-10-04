/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.obligatorio1;

import java.util.Scanner;

/**
 *
 * @author Sebastian
 */
public class Obligatorio1 {

    public static String tableroSeleccionado = "Standard";

    public static void main(String[] args) {
        mostrarTablero();
    }

    public static void mostrarTablero() {
        Scanner input = new Scanner(System.in);
        Interfaz.mostrarTablero();
        String opcionElegida = input.nextLine();

        if (opcionElegida.equalsIgnoreCase("a")) {
            opcionA();
        }

        if (opcionElegida.equalsIgnoreCase("b")) {
            opcionB();
        }
        
        if (opcionElegida.equalsIgnoreCase("c")) {
            opcionC();
        }

        if (opcionElegida.equalsIgnoreCase("e")) {
        }
    }

    public static void opcionA() {
        Scanner input = new Scanner(System.in);

        System.out.println("Ingrese el nombre del jugador");
        String nombre = input.nextLine();

        System.out.println("Ingrese la edad del jugador");
        int edad = input.nextInt();
        input.nextLine();

        System.out.println("Ingrese el alias del jugador");
        String alias = input.nextLine();

        Jugador nuevo = new Jugador();

        nuevo.Jugador(nombre, edad, alias);

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
            tableroSeleccionado = "Standard";
        }

        if (opcionSeleccionada.equalsIgnoreCase("b")) {
            tableroSeleccionado = "Precargado 1";
        }

        if (opcionSeleccionada.equalsIgnoreCase("c")) {
            tableroSeleccionado = "Precargado 2";
        }

        mostrarTablero();
    }
    
    public static void opcionC() {
        
    }
}
