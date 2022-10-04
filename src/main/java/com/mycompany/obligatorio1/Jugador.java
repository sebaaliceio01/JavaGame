/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.obligatorio1;

/**
 *
 * @author Sebastian
 */
public class Jugador {

    private String nombre;
    private int edad;
    private String alias;

    public static void main(String[] args) {

    }

    public void Jugador(String nombre, int edad, String alias) {
        this.nombre = nombre;
        this.edad = edad;
        this.alias = alias;
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

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public String getAlias() {
        return alias;
    }

    @Override
    public String toString() {
        return this.nombre + " " + this.edad + " " + this.alias;
    }
}
