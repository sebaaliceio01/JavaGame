/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.obligatorio1;

/**
 *
 * @author Sebastian
 */
public class Tablero {

    private String nombre;

    public static void main(String[] args) {
        generarTableroStandard();
    }

    public static void generarTableroStandard() {
        String[][] tablero = {
            {"A", "R", "A", "R", "A", "R"},
            {"R", "A", "R", "A", "R", "A"},
            {"A", "R", "A", "R", "A", "R"},
            {"R", "A", "R", "A", "R", "A"},
            {"A", "R", "A", "R", "A", "R"},
            {"R", "A", "R", "A", "R", "A"},
        };

        System.out.print("\t");
        for (int j = 0; j < tablero[0].length; j++) {
            System.out.print(" " + (j + 1));
        }
        
        System.out.println("");
        for (int i = 0; i < tablero.length; i++) {
            System.out.println("\t" + "+-+-+-+-+-+-+");
            System.out.print("\t");
           
            for (int j = 0; j < tablero[0].length; j++) {
                System.out.print("|" + tablero[i][j] + "");
            }
            
            System.out.print("|");
            System.out.println("");
        }
    }

    public static void imprimir(int matriz[][]) {

        //imprimo numero de columna
        System.out.print("\t");
        for (int j = 0; j < matriz[0].length; j++) {
            System.out.print((j + 1) + "\t");
        }
        System.out.println("");

        for (int i = 0; i < matriz.length; i++) {

            //imprimo numero de fila
            System.out.print("" + (i + 1) + "\t");
            for (int j = 0; j < matriz[0].length; j++) {
                System.out.print(matriz[i][j] + "\t");
            }
            System.out.println("");
        }
    }
}
